package infra.adapters.in.dto.consult;

import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PixRequestConsultDTOTest {

    @Test
    void deveCriarComConstrutorCompletoEGetters() {
        UUID id = UUID.randomUUID();
        String keyType = "EMAIL";
        String keyValue = "usuario@dominio.com";
        String accountType = "CORRENTE";
        Integer accountNumber = 12345;
        Integer agencyNumber = 6789;
        String accountHolderName = "João";
        String accountHolderSurname = "Silva";
        String createdTime = "2024-06-01T10:00:00";
        String inactiveTime = "2024-06-02T10:00:00";

        PixRequestConsultDTO dto = new PixRequestConsultDTO(
                id, keyType, keyValue, accountType, accountNumber, agencyNumber,
                accountHolderName, accountHolderSurname, createdTime, inactiveTime
        );

        assertEquals(id, dto.getId());
        assertEquals(keyType, dto.getKeyType());
        assertEquals(keyValue, dto.getKeyValue());
        assertEquals(accountType, dto.getAccountType());
        assertEquals(accountNumber, dto.getAccountNumber());
        assertEquals(agencyNumber, dto.getAgencyNumber());
        assertEquals(accountHolderName, dto.getAccountHolderName());
        assertEquals(accountHolderSurname, dto.getAccountHolderSurname());
        assertEquals(createdTime, dto.getCreatedTime());
        assertEquals(inactiveTime, dto.getInactiveTime());
    }

    @Test
    void deveCriarComBuilder() {
        UUID id = UUID.randomUUID();

        PixRequestConsultDTO dto = PixRequestConsultDTO.builder()
                .id(id)
                .keyType("CPF")
                .keyValue("12345678901")
                .accountType("POUPANCA")
                .accountNumber(54321)
                .agencyNumber(9876)
                .accountHolderName("Maria")
                .accountHolderSurname("Oliveira")
                .createdTime("2024-06-03T10:00:00")
                .inactiveTime("2024-06-04T10:00:00")
                .build();

        assertEquals(id, dto.getId());
        assertEquals("CPF", dto.getKeyType());
        assertEquals("12345678901", dto.getKeyValue());
        assertEquals("POUPANCA", dto.getAccountType());
        assertEquals(54321, dto.getAccountNumber());
        assertEquals(9876, dto.getAgencyNumber());
        assertEquals("Maria", dto.getAccountHolderName());
        assertEquals("Oliveira", dto.getAccountHolderSurname());
        assertEquals("2024-06-03T10:00:00", dto.getCreatedTime());
        assertEquals("2024-06-04T10:00:00", dto.getInactiveTime());
    }

    @Test
    void devePermitirSetters() {
        PixRequestConsultDTO dto = new PixRequestConsultDTO();
        UUID id = UUID.randomUUID();

        dto.setId(id);
        dto.setKeyType("CNPJ");
        dto.setKeyValue("12345678000199");
        dto.setAccountType("CORRENTE");
        dto.setAccountNumber(11111);
        dto.setAgencyNumber(2222);
        dto.setAccountHolderName("Empresa");
        dto.setAccountHolderSurname("LTDA");
        dto.setCreatedTime("2024-06-05T10:00:00");
        dto.setInactiveTime("2024-06-06T10:00:00");

        assertEquals(id, dto.getId());
        assertEquals("CNPJ", dto.getKeyType());
        assertEquals("12345678000199", dto.getKeyValue());
        assertEquals("CORRENTE", dto.getAccountType());
        assertEquals(11111, dto.getAccountNumber());
        assertEquals(2222, dto.getAgencyNumber());
        assertEquals("Empresa", dto.getAccountHolderName());
        assertEquals("LTDA", dto.getAccountHolderSurname());
        assertEquals("2024-06-05T10:00:00", dto.getCreatedTime());
        assertEquals("2024-06-06T10:00:00", dto.getInactiveTime());
    }
}