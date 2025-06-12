package infra.adapters.in.dto.consult;

import com.store.itaupixcase.infra.adapters.in.dto.consult.FiltersDto;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FiltersDtoTest {

    @Test
    void deveCriarComConstrutorCompletoEGetters() {
        UUID id = UUID.randomUUID();
        String keyType = "CPF";
        String keyValue = "12345678901";
        String accountType = "CORRENTE";
        Integer accountNumber = 12345;
        Integer agencyNumber = 6789;
        String accountHolderName = "João";
        String createdTime = "2024-06-01T10:00:00";
        String inactiveTime = "2024-06-02T10:00:00";

        FiltersDto dto = new FiltersDto(id, keyType, keyValue, accountType, accountNumber, agencyNumber, accountHolderName, createdTime, inactiveTime);

        assertEquals(id, dto.getId());
        assertEquals(keyType, dto.getKeyType());
        assertEquals(keyValue, dto.getKeyValue());
        assertEquals(accountType, dto.getAccountType());
        assertEquals(accountNumber, dto.getAccountNumber());
        assertEquals(agencyNumber, dto.getAgencyNumber());
        assertEquals(accountHolderName, dto.getAccountHolderName());
        assertEquals(createdTime, dto.getCreatedTime());
        assertEquals(inactiveTime, dto.getInactiveTime());
    }

    @Test
    void deveCriarComBuilder() {
        UUID id = UUID.randomUUID();

        FiltersDto dto = FiltersDto.builder()
                .id(id)
                .keyType("EMAIL")
                .keyValue("usuario@dominio.com")
                .accountType("POUPANCA")
                .accountNumber(54321)
                .agencyNumber(9876)
                .accountHolderName("Maria")
                .createdTime("2024-06-03T10:00:00")
                .inactiveTime("2024-06-04T10:00:00")
                .build();

        assertEquals(id, dto.getId());
        assertEquals("EMAIL", dto.getKeyType());
        assertEquals("usuario@dominio.com", dto.getKeyValue());
        assertEquals("POUPANCA", dto.getAccountType());
        assertEquals(54321, dto.getAccountNumber());
        assertEquals(9876, dto.getAgencyNumber());
        assertEquals("Maria", dto.getAccountHolderName());
        assertEquals("2024-06-03T10:00:00", dto.getCreatedTime());
        assertEquals("2024-06-04T10:00:00", dto.getInactiveTime());
    }

    @Test
    void devePermitirSetters() {
        FiltersDto dto = new FiltersDto();
        UUID id = UUID.randomUUID();

        dto.setId(id);
        dto.setKeyType("CNPJ");
        dto.setKeyValue("12345678000199");
        dto.setAccountType("CORRENTE");
        dto.setAccountNumber(11111);
        dto.setAgencyNumber(2222);
        dto.setAccountHolderName("Empresa");
        dto.setCreatedTime("2024-06-05T10:00:00");
        dto.setInactiveTime("2024-06-06T10:00:00");

        assertEquals(id, dto.getId());
        assertEquals("CNPJ", dto.getKeyType());
        assertEquals("12345678000199", dto.getKeyValue());
        assertEquals("CORRENTE", dto.getAccountType());
        assertEquals(11111, dto.getAccountNumber());
        assertEquals(2222, dto.getAgencyNumber());
        assertEquals("Empresa", dto.getAccountHolderName());
        assertEquals("2024-06-05T10:00:00", dto.getCreatedTime());
        assertEquals("2024-06-06T10:00:00", dto.getInactiveTime());
    }
}