package com.store.itaupixcase.cor.usecase;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.domain.enuns.ClientType;
import com.store.itaupixcase.cor.domain.enuns.GenericsRules;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.domain.factory.PixFactory;
import com.store.itaupixcase.cor.domain.validation.*;
import com.store.itaupixcase.cor.ports.in.InsertKeyPixInPort;
import com.store.itaupixcase.cor.ports.out.ConsultPixOutPort;
import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.cor.usecase.command.RegisterPixKeyCommand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsertKeyPixUseCaseImpl implements InsertKeyPixInPort {

    private final PixFactory pixFactory;
    private final PixKeyValidator pixKeyValidator;
    private final AccountValidator accountValidator;
    private final FilterValidator filterValidator;
    private final ConsultPixOutPort consultPixOutPort;

    public InsertKeyPixUseCaseImpl(PixFactory pixFactory, PixKeyValidator pixKeyValidator,
                                   AccountValidator accountValidator, FilterValidator filterValidator, ConsultPixOutPort consultPixOutPort) {
        this.pixFactory = pixFactory;
        this.pixKeyValidator = pixKeyValidator;
        this.accountValidator = accountValidator;
        this.filterValidator = filterValidator;
        this.consultPixOutPort = consultPixOutPort;
    }

    @Override
    public Pix insertKeyPix(RegisterPixKeyCommand command) {
        KeyType keyType = KeyType.valueOf(command.getKeyType().toUpperCase());
        ClientType clientType = pixKeyValidator.validateType(command.getClientType());
        AccountType accountType = accountValidator.validateAccountType(command.getAccountType());
        KeyPix keyPix = pixFactory.create(command.getKeyValue(), keyType);

        validateKeys(command, clientType);

        return new Pix(
                keyType,
                keyPix,
                accountType,
                command.getAgencyNumber(),
                command.getAccountNumber(),
                command.getAccountHolderName(),
                command.getAccountHolderSurname(),
                clientType
        );
    }

    @Override
    public Pix updateKeyPix(RegisterPixKeyCommand command) {
        accountValidator.validateIdActive(command.getId(), GenericsRules.ACTIVE.getValue());
        accountValidator.validateIdPix(command.getId());
        validateData(command);

        AccountType accountType = accountValidator.validateAccountType(command.getAccountType());

        return new Pix(
                command.getId(),
                accountType,
                command.getAccountNumber(),
                command.getAgencyNumber(),
                command.getAccountHolderName(),
                command.getAccountHolderSurname()
        );
    }

    @Override
    public List<ConsultPixKeyCommand> consultKeyPix(FiltersCommand command) {
        filterValidator.validateFilters(command);
        return ConsultValidator.validateConsult(consultPixOutPort.consultKeyPix(command));
    }

    private void validateKeys(RegisterPixKeyCommand command, ClientType clientType) {
        pixKeyValidator.existsPixKey(command.getKeyValue(),GenericsRules.ACTIVE.getValue());
        pixKeyValidator.validateKeysByCLient(
                command.getAgencyNumber(),
                command.getAccountNumber(),
                clientType.name(),GenericsRules.ACTIVE.getValue()
        );
        pixKeyValidator.validateTypePerson(command.getClientType(), command.getKeyType());
    }

    private void validateData(RegisterPixKeyCommand command) {
        accountValidator.validateAccountNumber(command.getAccountNumber());
        accountValidator.validateAgencyNumber(command.getAgencyNumber());
        accountValidator.validateHolderName(command.getAccountHolderName());
        accountValidator.validateHolderSurname(command.getAccountHolderSurname());
    }
}