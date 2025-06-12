package infra.adapters.in.controller;

import com.store.itaupixcase.app.PixService;
import com.store.itaupixcase.infra.adapters.in.controller.PixController;
import com.store.itaupixcase.infra.adapters.in.dto.consult.FiltersDto;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixRequestDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixResponseDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixRequestUpdateDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PixControllerTest {

    private PixService pixService;
    private PixController controller;

    @BeforeEach
    void setUp() {
        pixService = mock(PixService.class);
        controller = new PixController(pixService);
    }

    @Test
    void deveRegistrarPixKey() {
        PixRequestDTO requestDTO = mock(PixRequestDTO.class);
        PixResponseDTO responseDTO = mock(PixResponseDTO.class);

        when(pixService.registerPixKey(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<PixResponseDTO> response = controller.registerPixKey(requestDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
        verify(pixService).registerPixKey(requestDTO);
    }

    @Test
    void deveAtualizarPixKey() {
        PixRequestUpdateDTO requestDTO = mock(PixRequestUpdateDTO.class);
        PixResponseUpdateDTO responseDTO = mock(PixResponseUpdateDTO.class);

        when(pixService.updatePixKey(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<PixResponseUpdateDTO> response = controller.updatePixKey(requestDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDTO, response.getBody());
        verify(pixService).updatePixKey(requestDTO);
    }

    @Test
    void deveConsultarPixKey() {
        UUID id = UUID.randomUUID();
        String keyType = "EMAIL";
        String keyValue = "usuario@dominio.com";
        String accountType = "CORRENTE";
        Integer accountNumber = 12345;
        Integer agencyNumber = 6789;
        String accountHolderName = "João";
        String createdTime = "2024-06-01T10:00:00";
        String inactiveTime = "2024-06-02T10:00:00";

        PixRequestConsultDTO consultDTO = mock(PixRequestConsultDTO.class);
        List<PixRequestConsultDTO> consultList = Collections.singletonList(consultDTO);

        when(pixService.getPixKey(any(FiltersDto.class))).thenReturn(consultList);

        ResponseEntity<List<PixRequestConsultDTO>> response = controller.getPixKey(
                id, keyType, keyValue, accountType, accountNumber, agencyNumber, accountHolderName, createdTime, inactiveTime
        );

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(consultList, response.getBody());

        ArgumentCaptor<FiltersDto> captor = ArgumentCaptor.forClass(FiltersDto.class);
        verify(pixService).getPixKey(captor.capture());
        FiltersDto filters = captor.getValue();
        assertEquals(id, filters.getId());
        assertEquals(keyType, filters.getKeyType());
        assertEquals(keyValue, filters.getKeyValue());
        assertEquals(accountType, filters.getAccountType());
        assertEquals(accountNumber, filters.getAccountNumber());
        assertEquals(agencyNumber, filters.getAgencyNumber());
        assertEquals(accountHolderName, filters.getAccountHolderName());
        assertEquals(createdTime, filters.getCreatedTime());
        assertEquals(inactiveTime, filters.getInactiveTime());
    }
}