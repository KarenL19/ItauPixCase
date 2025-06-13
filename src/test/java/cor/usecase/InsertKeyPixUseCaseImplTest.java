package cor.usecase;

import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.domain.enuns.ClientType;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import com.store.itaupixcase.cor.domain.factory.PixFactory;
import com.store.itaupixcase.cor.domain.validation.AccountValidator;
import com.store.itaupixcase.cor.domain.validation.FilterValidator;
import com.store.itaupixcase.cor.domain.validation.PixKeyValidator;
import com.store.itaupixcase.cor.usecase.InsertKeyPixUseCaseImpl;
import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.cor.usecase.command.RegisterPixKeyCommand;
import com.store.itaupixcase.infra.adapters.out.implementsPortOut.ConsultPixKeyOuntPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InsertKeyPixUseCaseImplTest {

    private PixFactory pixFactory;
    private PixKeyValidator pixKeyValidator;
    private AccountValidator accountValidator;
    private FilterValidator filterValidator;
    private ConsultPixKeyOuntPort consultPixKeyOuntPort;
    private InsertKeyPixUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        pixFactory = mock(PixFactory.class);
        pixKeyValidator = mock(PixKeyValidator.class);
        accountValidator = mock(AccountValidator.class);
        filterValidator = mock(FilterValidator.class);
        consultPixKeyOuntPort = mock(ConsultPixKeyOuntPort.class);
        useCase = new InsertKeyPixUseCaseImpl(pixFactory, pixKeyValidator, accountValidator, filterValidator, consultPixKeyOuntPort);
    }

    @Test
    void insertKeyPix_deveRetornarPixValido() {
        RegisterPixKeyCommand command = mock(RegisterPixKeyCommand.class);
        when(command.getKeyType()).thenReturn("CPF");
        when(command.getClientType()).thenReturn("PF");
        when(command.getAccountType()).thenReturn("CORRENTE");
        when(command.getKeyValue()).thenReturn("12345678901");
        when(command.getAgencyNumber()).thenReturn(1234);
        when(command.getAccountNumber()).thenReturn(56789);
        when(command.getAccountHolderName()).thenReturn("João");
        when(command.getAccountHolderSurname()).thenReturn("Silva");

        KeyPix keyPix = mock(KeyPix.class);

        when(pixKeyValidator.validateType("PF")).thenReturn(ClientType.PF);
        when(accountValidator.validateAccountType("CORRENTE")).thenReturn(AccountType.CORRENTE);
        when(pixFactory.create("12345678901", KeyType.CPF)).thenReturn(keyPix);

        Pix pix = useCase.insertKeyPix(command);

        assertNotNull(pix);
        assertEquals(KeyType.CPF, pix.getKeyType());
        assertEquals(keyPix, pix.getKeyPix());
        assertEquals(AccountType.CORRENTE, pix.getAccountType());
        assertEquals(1234, pix.getAgencyNumber());
        assertEquals(56789, pix.getAccountNumber());
        assertEquals("João", pix.getAccountHolderName());
        assertEquals("Silva", pix.getAccountHolderSurname());
        assertEquals(ClientType.PF, pix.getClientType());
    }

    @Test
    void updateKeyPix_deveRetornarPixAtualizado() {
        RegisterPixKeyCommand command = mock(RegisterPixKeyCommand.class);
        UUID id = UUID.randomUUID();
        when(command.getId()).thenReturn(id);
        when(command.getAccountType()).thenReturn("POUPANCA");
        when(command.getAccountNumber()).thenReturn(1111);
        when(command.getAgencyNumber()).thenReturn(2222);
        when(command.getAccountHolderName()).thenReturn("Maria");
        when(command.getAccountHolderSurname()).thenReturn("Oliveira");

        when(accountValidator.validateAccountType("POUPANCA")).thenReturn(AccountType.POUPANCA);

        Pix pix = useCase.updateKeyPix(command);

        assertNotNull(pix);
        assertEquals(id, pix.getId());
        assertEquals(AccountType.POUPANCA, pix.getAccountType());
        assertEquals(1111, pix.getAccountNumber());
        assertEquals(2222, pix.getAgencyNumber());
        assertEquals("Maria", pix.getAccountHolderName());
        assertEquals("Oliveira", pix.getAccountHolderSurname());
    }

    @Test
    void consultKeyPix_deveValidarEChamarPort() {
        FiltersCommand filtersCommand = mock(FiltersCommand.class);
        ConsultPixKeyCommand consultPixKeyCommand = mock(ConsultPixKeyCommand.class);
        when(consultPixKeyCommand.getInactiveTime()).thenReturn("");
        List<ConsultPixKeyCommand> consultList = List.of(consultPixKeyCommand);
        when(consultPixKeyOuntPort.consultKeyPix(filtersCommand)).thenReturn(consultList);

        List<ConsultPixKeyCommand> result = useCase.consultKeyPix(filtersCommand);

        assertNotNull(result);
        assertEquals(consultList, result);
        verify(filterValidator).validateFilters(filtersCommand);
        verify(consultPixKeyOuntPort).consultKeyPix(filtersCommand);
    }
}