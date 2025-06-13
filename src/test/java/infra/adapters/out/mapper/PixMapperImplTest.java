package infra.adapters.out.mapper;

import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.out.mapper.impl.PixMapperImpl;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PixMapperImplTest {

    @Test
    void toPixRequestConsultDTOList_deveConverterCorretamente() {
        PixMapperImpl mapper = new PixMapperImpl();
        UUID id = UUID.randomUUID();

        ConsultPixKeyCommand command = new ConsultPixKeyCommand(
                id,
                "EMAIL",
                "a@b.com",
                "CORRENTE",
                12345,
                678,
                "João",
                "Silva",
                "2024-06-01T10:00:00",
                null
        );

        List<PixRequestConsultDTO> dtos = mapper.toPixRequestConsultDTOList(List.of(command));

        assertEquals(1, dtos.size());
        PixRequestConsultDTO dto = dtos.get(0);
        assertEquals(id, dto.getId());
        assertEquals("EMAIL", dto.getKeyType());
        assertEquals("a@b.com", dto.getKeyValue());
        assertEquals("CORRENTE", dto.getAccountType());
        assertEquals(12345, dto.getAccountNumber());
        assertEquals(678, dto.getAgencyNumber());
        assertEquals("João", dto.getAccountHolderName());
        assertEquals("Silva", dto.getAccountHolderSurname());
        assertEquals(null, dto.getInactiveTime());
        assertEquals("2024-06-01T10:00:00", dto.getCreatedTime());
    }
}