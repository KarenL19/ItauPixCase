package infra.adapters.in.dto.insert;

import com.store.itaupixcase.infra.adapters.in.dto.insert.PixRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PixRequestDTOTest {

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

        PixRequestDTO dto = new PixRequestDTO(
                keyType, keyValue, accountType, accountNumber, agencyNumber,
                accountHolderName, accountHolderSurname, clientType
        );

        assertEquals(keyType, dto.getKeyType());
        assertEquals(keyValue, dto.getKeyValue());
        assertEquals(accountType, dto.getAccountType());
        assertEquals(accountNumber, dto.getAccountNumber());
        assertEquals(agencyNumber, dto.getAgencyNumber());
        assertEquals(accountHolderName, dto.getAccountHolderName());
        assertEquals(accountHolderSurname, dto.getAccountHolderSurname());
        assertEquals(clientType, dto.getClientType());
    }

    @Test
    void deveCriarComBuilder() {
        PixRequestDTO dto = PixRequestDTO.builder()
                .keyType("CPF")
                .keyValue("12345678901")
                .accountType("POUPANCA")
                .accountNumber(54321)
                .agencyNumber(9876)
                .accountHolderName("Maria")
                .accountHolderSurname("Oliveira")
                .clientType("PJ")
                .build();

        assertEquals("CPF", dto.getKeyType());
        assertEquals("12345678901", dto.getKeyValue());
        assertEquals("POUPANCA", dto.getAccountType());
        assertEquals(54321, dto.getAccountNumber());
        assertEquals(9876, dto.getAgencyNumber());
        assertEquals("Maria", dto.getAccountHolderName());
        assertEquals("Oliveira", dto.getAccountHolderSurname());
        assertEquals("PJ", dto.getClientType());
    }

    @Test
    void devePermitirSetters() {
        PixRequestDTO dto = new PixRequestDTO();
        dto.setKeyType("CNPJ");
        dto.setKeyValue("12345678000199");
        dto.setAccountType("CORRENTE");
        dto.setAccountNumber(11111);
        dto.setAgencyNumber(2222);
        dto.setAccountHolderName("Empresa");
        dto.setAccountHolderSurname("LTDA");
        dto.setClientType("PJ");

        assertEquals("CNPJ", dto.getKeyType());
        assertEquals("12345678000199", dto.getKeyValue());
        assertEquals("CORRENTE", dto.getAccountType());
        assertEquals(11111, dto.getAccountNumber());
        assertEquals(2222, dto.getAgencyNumber());
        assertEquals("Empresa", dto.getAccountHolderName());
        assertEquals("LTDA", dto.getAccountHolderSurname());
        assertEquals("PJ", dto.getClientType());
    }
}
