package com.store.itaupixcase.cor.usecase;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.domain.enuns.ClientType;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.domain.factory.PixFactory;
import com.store.itaupixcase.cor.domain.validation.*;
import com.store.itaupixcase.cor.ports.in.InsertKeyPixInPort;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.cor.usecase.command.RegisterKeyPixCommand;
import org.springframework.stereotype.Component;

@Component
public class InsertKeyPixUseCaseImpl implements InsertKeyPixInPort {

    private final PixFactory pixFactory;
    private final PixKeyValidator pixKeyValidator;
    private final AccountValidator accountValidator;
    private final FilterValidator filterValidator;

    public InsertKeyPixUseCaseImpl(PixFactory pixFactory, PixKeyValidator pixKeyValidator,
                                   AccountValidator accountValidator, FilterValidator filterValidator) {
        this.pixFactory = pixFactory;
        this.pixKeyValidator = pixKeyValidator;
        this.accountValidator = accountValidator;
        this.filterValidator = filterValidator;
    }

    @Override
    public Pix insertKeyPix(RegisterKeyPixCommand command) {
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
    public Pix updateKeyPix(RegisterKeyPixCommand command) {
        accountValidator.validateIdActive(command.getId());
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
    public FiltersCommand consultKeyPix(FiltersCommand command) {
        filterValidator.validateFilters(command);
        return command;
    }

    private void validateKeys(RegisterKeyPixCommand command, ClientType clientType) {
        pixKeyValidator.existsPixKey(command.getKeyValue());
        pixKeyValidator.validateKeysByCLient(
                command.getAgencyNumber(),
                command.getAccountNumber(),
                clientType.name()
        );
        pixKeyValidator.validateTypePerson(command.getClientType(), command.getKeyType());
    }

    private void validateData(RegisterKeyPixCommand command) {
        accountValidator.validateAccountNumber(command.getAccountNumber());
        accountValidator.validateAgencyNumber(command.getAgencyNumber());
        accountValidator.validateHolderName(command.getAccountHolderName());
        accountValidator.validateHolderSurname(command.getAccountHolderSurname());
    }
}