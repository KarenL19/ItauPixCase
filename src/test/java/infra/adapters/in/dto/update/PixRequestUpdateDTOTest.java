package infra.adapters.in.dto.update;

import com.store.itaupixcase.infra.adapters.in.dto.update.PixRequestUpdateDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PixRequestUpdateDTOTest {

    @Test
    void deveCriarComConstrutorCompletoEGetters() {
        UUID id = UUID.randomUUID();
        String accountType = "CORRENTE";
        Integer accountNumber = 12345;
        Integer agencyNumber = 6789;
        String accountHolderName = "João";
        String accountHolderSurname = "Silva";

        PixRequestUpdateDTO dto = new PixRequestUpdateDTO(
                id, accountType, accountNumber, agencyNumber, accountHolderName, accountHolderSurname
        );

        assertEquals(id, dto.getId());
        assertEquals(accountType, dto.getAccountType());
        assertEquals(accountNumber, dto.getAccountNumber());
        assertEquals(agencyNumber, dto.getAgencyNumber());
        assertEquals(accountHolderName, dto.getAccountHolderName());
        assertEquals(accountHolderSurname, dto.getAccountHolderSurname());
    }

    @Test
    void deveCriarComBuilder() {
        UUID id = UUID.randomUUID();

        PixRequestUpdateDTO dto = PixRequestUpdateDTO.builder()
                .id(id)
                .accountType("POUPANCA")
                .accountNumber(54321)
                .agencyNumber(9876)
                .accountHolderName("Maria")
                .accountHolderSurname("Oliveira")
                .build();

        assertEquals(id, dto.getId());
        assertEquals("POUPANCA", dto.getAccountType());
        assertEquals(54321, dto.getAccountNumber());
        assertEquals(9876, dto.getAgencyNumber());
        assertEquals("Maria", dto.getAccountHolderName());
        assertEquals("Oliveira", dto.getAccountHolderSurname());
    }

    @Test
    void devePermitirSetters() {
        PixRequestUpdateDTO dto = new PixRequestUpdateDTO();
        UUID id = UUID.randomUUID();

        dto.setId(id);
        dto.setAccountType("SALARIO");
        dto.setAccountNumber(11111);
        dto.setAgencyNumber(2222);
        dto.setAccountHolderName("Empresa");
        dto.setAccountHolderSurname("LTDA");

        assertEquals(id, dto.getId());
        assertEquals("SALARIO", dto.getAccountType());
        assertEquals(11111, dto.getAccountNumber());
        assertEquals(2222, dto.getAgencyNumber());
        assertEquals("Empresa", dto.getAccountHolderName());
        assertEquals("LTDA", dto.getAccountHolderSurname());
    }
}