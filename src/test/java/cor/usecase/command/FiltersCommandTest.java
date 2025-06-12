package cor.usecase.command;

import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FiltersCommandTest {

    @Test
    void deveCriarEFazerGettersSetters() {
        UUID id = UUID.randomUUID();
        String keyType = "CPF";
        String keyValue = "12345678901";
        String accountType = "CORRENTE";
        Integer accountNumber = 12345;
        Integer agencyNumber = 6789;
        String accountHolderName = "João";
        String createdTime = "2024-06-01T10:00:00";
        String inactiveTime = "2024-06-02T10:00:00";

        FiltersCommand command = new FiltersCommand(
                id, keyType, accountNumber, keyValue, agencyNumber, accountHolderName, createdTime, inactiveTime, accountType
        );

        assertEquals(id, command.getId());
        assertEquals(keyType, command.getKeyType());
        assertEquals(keyValue, command.getKeyValue());
        assertEquals(accountType, command.getAccountType());
        assertEquals(accountNumber, command.getAccountNumber());
        assertEquals(agencyNumber, command.getAgencyNumber());
        assertEquals(accountHolderName, command.getAccountHolderName());
        assertEquals(createdTime, command.getCreatedTime());
        assertEquals(inactiveTime, command.getInactiveTime());

        // Testando setters
        UUID newId = UUID.randomUUID();
        command.setId(newId);
        assertEquals(newId, command.getId());

        command.setKeyType("EMAIL");
        assertEquals("EMAIL", command.getKeyType());

        command.setKeyValue("usuario@dominio.com");
        assertEquals("usuario@dominio.com", command.getKeyValue());

        command.setAccountType("POUPANCA");
        assertEquals("POUPANCA", command.getAccountType());

        command.setAccountNumber(54321);
        assertEquals(54321, command.getAccountNumber());

        command.setAgencyNumber(9876);
        assertEquals(9876, command.getAgencyNumber());

        command.setAccountHolderName("Maria");
        assertEquals("Maria", command.getAccountHolderName());

        command.setCreatedTime("2024-06-03T10:00:00");
        assertEquals("2024-06-03T10:00:00", command.getCreatedTime());

        command.setInactiveTime("2024-06-04T10:00:00");
        assertEquals("2024-06-04T10:00:00", command.getInactiveTime());
    }
}