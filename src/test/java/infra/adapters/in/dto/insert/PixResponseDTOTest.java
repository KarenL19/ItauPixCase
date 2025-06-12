package infra.adapters.in.dto.insert;

import com.store.itaupixcase.infra.adapters.in.dto.insert.PixResponseDTO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PixResponseDTOTest {

    @Test
    void deveCriarComConstrutorCompletoEGetters() {
        UUID id = UUID.randomUUID();
        PixResponseDTO dto = new PixResponseDTO(id);

        assertEquals(id, dto.getId());
    }

    @Test
    void deveCriarComBuilder() {
        UUID id = UUID.randomUUID();
        PixResponseDTO dto = PixResponseDTO.builder()
                .id(id)
                .build();

        assertEquals(id, dto.getId());
    }

    @Test
    void devePermitirSetters() {
        PixResponseDTO dto = new PixResponseDTO();
        UUID id = UUID.randomUUID();
        dto.setId(id);

        assertEquals(id, dto.getId());
    }
}
