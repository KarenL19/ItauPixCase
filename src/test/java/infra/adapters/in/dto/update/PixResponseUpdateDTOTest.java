package infra.adapters.in.dto.update;

import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PixResponseUpdateDTOTest {

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
        LocalDateTime createdTime = LocalDateTime.now();

        PixResponseUpdateDTO dto = new PixResponseUpdateDTO(
                id, keyType, keyValue, accountType, accountNumber, agencyNumber,
                accountHolderName, accountHolderSurname, createdTime
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
    }

    @Test
    void deveCriarComBuilder() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdTime = LocalDateTime.now();

        PixResponseUpdateDTO dto = PixResponseUpdateDTO.builder()
                .id(id)
                .keyType("CPF")
                .keyValue("12345678901")
                .accountType("POUPANCA")
                .accountNumber(54321)
                .agencyNumber(9876)
                .accountHolderName("Maria")
                .accountHolderSurname("Oliveira")
                .createdTime(createdTime)
                .build();

        assertEquals(id, dto.getId());
        assertEquals("CPF", dto.getKeyType());
        assertEquals("12345678901", dto.getKeyValue());
        assertEquals("POUPANCA", dto.getAccountType());
        assertEquals(54321, dto.getAccountNumber());
        assertEquals(9876, dto.getAgencyNumber());
        assertEquals("Maria", dto.getAccountHolderName());
        assertEquals("Oliveira", dto.getAccountHolderSurname());
        assertEquals(createdTime, dto.getCreatedTime());
    }

    @Test
    void devePermitirSetters() {
        PixResponseUpdateDTO dto = new PixResponseUpdateDTO();
        UUID id = UUID.randomUUID();
        LocalDateTime createdTime = LocalDateTime.now();

        dto.setId(id);
        dto.setKeyType("CNPJ");
        dto.setKeyValue("12345678000199");
        dto.setAccountType("SALARIO");
        dto.setAccountNumber(11111);
        dto.setAgencyNumber(2222);
        dto.setAccountHolderName("Empresa");
        dto.setAccountHolderSurname("LTDA");
        dto.setCreatedTime(createdTime);

        assertEquals(id, dto.getId());
        assertEquals("CNPJ", dto.getKeyType());
        assertEquals("12345678000199", dto.getKeyValue());
        assertEquals("SALARIO", dto.getAccountType());
        assertEquals(11111, dto.getAccountNumber());
        assertEquals(2222, dto.getAgencyNumber());
        assertEquals("Empresa", dto.getAccountHolderName());
        assertEquals("LTDA", dto.getAccountHolderSurname());
        assertEquals(createdTime, dto.getCreatedTime());
    }
}