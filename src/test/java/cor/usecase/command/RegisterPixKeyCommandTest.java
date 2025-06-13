package cor.usecase.command;

import com.store.itaupixcase.cor.usecase.command.RegisterPixKeyCommand;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RegisterPixKeyCommandTest {

    @Test
    void deveCriarComConstrutorCompletoEGetters() {
        String keyType = "EMAIL";
        String keyValue = "usuario@dominio.com";
        String accountType = "CORRENTE";
        Integer accountNumber = 12345;
        Integer agencyNumber = 6789;
        String accountHolderName = "João";
        String accountHolderSurname = "Silva";
        String clientType = "PF";

        RegisterPixKeyCommand command = new RegisterPixKeyCommand(
                keyType, keyValue, accountType, accountNumber, agencyNumber, accountHolderName, accountHolderSurname, clientType
        );

        assertNull(command.getId());
        assertEquals(keyType, command.getKeyType());
        assertEquals(keyValue, command.getKeyValue());
        assertEquals(accountType, command.getAccountType());
        assertEquals(accountNumber, command.getAccountNumber());
        assertEquals(agencyNumber, command.getAgencyNumber());
        assertEquals(accountHolderName, command.getAccountHolderName());
        assertEquals(accountHolderSurname, command.getAccountHolderSurname());
        assertEquals(clientType, command.getClientType());
    }

    @Test
    void deveCriarComConstrutorParcialEGetters() {
        UUID id = UUID.randomUUID();
        String accountType = "POUPANCA";
        Integer accountNumber = 54321;
        Integer agencyNumber = 9876;
        String accountHolderName = "Maria";
        String accountHolderSurname = "Oliveira";

        RegisterPixKeyCommand command = new RegisterPixKeyCommand(
                id, accountType, accountNumber, agencyNumber, accountHolderName, accountHolderSurname
        );

        assertEquals(id, command.getId());
        assertEquals(accountType, command.getAccountType());
        assertEquals(accountNumber, command.getAccountNumber());
        assertEquals(agencyNumber, command.getAgencyNumber());
        assertEquals(accountHolderName, command.getAccountHolderName());
        assertEquals(accountHolderSurname, command.getAccountHolderSurname());
        assertNull(command.getKeyType());
        assertNull(command.getKeyValue());
        assertNull(command.getClientType());
    }

    @Test
    void devePermitirSetId() {
        RegisterPixKeyCommand command = new RegisterPixKeyCommand(
                "EMAIL", "usuario@dominio.com", "CORRENTE", 12345, 6789, "João", "Silva", "PF"
        );
        UUID novoId = UUID.randomUUID();
        command.setId(novoId);
        assertEquals(novoId, command.getId());
    }
}
