package app;

import com.store.itaupixcase.app.impl.PixServiceImpl;
import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.ports.in.InsertKeyPixInPort;
import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.cor.usecase.command.RegisterPixKeyCommand;
import com.store.itaupixcase.infra.adapters.in.dto.consult.FiltersDto;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixRequestDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixResponseDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixRequestUpdateDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import com.store.itaupixcase.infra.adapters.out.mapper.PixMapper;
import com.store.itaupixcase.infra.adapters.out.repository.PixRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PixServiceImplTest {

    @Mock
    private InsertKeyPixInPort insertKeyPixInPort;
    @Mock
    private PixMapper pixMapper;
    @Mock
    private PixRepository pixRepository;
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private PixServiceImpl pixService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerPixKey_deveRetornarPixResponseDTO() {
        PixRequestDTO dto = mock(PixRequestDTO.class);
        RegisterPixKeyCommand command = mock(RegisterPixKeyCommand.class);
        PixEntity entity = mock(PixEntity.class);

        when(dto.getKeyType()).thenReturn("CPF");
        when(dto.getKeyValue()).thenReturn("123");
        when(dto.getAccountType()).thenReturn("corrente");
        when(dto.getAccountNumber()).thenReturn(1);
        when(dto.getAgencyNumber()).thenReturn(1);
        when(dto.getAccountHolderName()).thenReturn("Nome");
        when(dto.getAccountHolderSurname()).thenReturn("Sobrenome");
        when(dto.getClientType()).thenReturn("PF");

        when(insertKeyPixInPort.insertKeyPix(any())).thenReturn(mock(Pix.class));
        when(pixMapper.toEntityInsert(any())).thenReturn(entity);
        UUID id = UUID.randomUUID();
        when(entity.getId()).thenReturn(id);

        PixResponseDTO response = pixService.registerPixKey(dto);

        assertNotNull(response);
        assertEquals(id, response.getId());
        verify(pixRepository).save(entity);
    }

    @Test
    void registerPixKey_deveLancarExcecaoAoSalvar() {
        PixRequestDTO dto = mock(PixRequestDTO.class);
        PixEntity entity = mock(PixEntity.class);

        when(dto.getKeyType()).thenReturn("CPF");
        when(dto.getKeyValue()).thenReturn("123");
        when(dto.getAccountType()).thenReturn("corrente");
        when(dto.getAccountNumber()).thenReturn(1);
        when(dto.getAgencyNumber()).thenReturn(1);
        when(dto.getAccountHolderName()).thenReturn("Nome");
        when(dto.getAccountHolderSurname()).thenReturn("Sobrenome");
        when(dto.getClientType()).thenReturn("PF");

        when(insertKeyPixInPort.insertKeyPix(any())).thenReturn(mock(Pix.class));
        when(pixMapper.toEntityInsert(any())).thenReturn(entity);
        doThrow(new RuntimeException("erro")).when(pixRepository).save(entity);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> pixService.registerPixKey(dto));
        assertTrue(ex.getMessage().contains("Erro ao salvar"));
    }

    @Test
    void updatePixKey_deveRetornarPixResponseUpdateDTO() {
        PixRequestUpdateDTO dto = mock(PixRequestUpdateDTO.class);
        PixEntity entity = mock(PixEntity.class);
        PixResponseUpdateDTO responseUpdateDTO = mock(PixResponseUpdateDTO.class);

        UUID id = UUID.randomUUID();
        when(dto.getId()).thenReturn(id);
        when(dto.getAccountType()).thenReturn("corrente");
        when(dto.getAccountNumber()).thenReturn(1);
        when(dto.getAgencyNumber()).thenReturn(1);
        when(dto.getAccountHolderName()).thenReturn("Nome");
        when(dto.getAccountHolderSurname()).thenReturn("Sobrenome");

        when(insertKeyPixInPort.updateKeyPix(any())).thenReturn(mock(Pix.class));
        when(pixMapper.toEntityUpdate(any())).thenReturn(entity);
        when(entity.getId()).thenReturn(id);
        when(entity.getAccountType()).thenReturn("corrente");
        when(entity.getAccountNumber()).thenReturn(1);
        when(entity.getAgencyNumber()).thenReturn(1);
        when(entity.getHolderName()).thenReturn("Nome");
        when(entity.getHolderSurname()).thenReturn("Sobrenome");
        when(pixRepository.findById(id)).thenReturn(Optional.of(entity));
        when(pixMapper.toResponseUpdateDTO(entity)).thenReturn(responseUpdateDTO);

        PixResponseUpdateDTO result = pixService.updatePixKey(dto);

        assertNotNull(result);
        verify(pixRepository).updatePixFields(any(), any(), any(), any(), any(), any());
        verify(entityManager).clear();
    }

    @Test
    void updatePixKey_deveLancarExcecaoAoAtualizar() {
        PixRequestUpdateDTO dto = mock(PixRequestUpdateDTO.class);
        PixEntity entity = mock(PixEntity.class);

        UUID id = UUID.randomUUID();
        when(dto.getId()).thenReturn(id);
        when(dto.getAccountType()).thenReturn("corrente");
        when(dto.getAccountNumber()).thenReturn(1);
        when(dto.getAgencyNumber()).thenReturn(1);
        when(dto.getAccountHolderName()).thenReturn("Nome");
        when(dto.getAccountHolderSurname()).thenReturn("Sobrenome");

        when(insertKeyPixInPort.updateKeyPix(any())).thenReturn(mock(Pix.class));
        when(pixMapper.toEntityUpdate(any())).thenReturn(entity);
        when(entity.getId()).thenReturn(id);
        doThrow(new RuntimeException("erro")).when(pixRepository).updatePixFields(any(), any(), any(), any(), any(), any());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> pixService.updatePixKey(dto));
        assertTrue(ex.getMessage().contains("Erro ao atualizar"));
    }

    @Test
    void getPixKey_deveRetornarLista() {
        FiltersDto filtersDto = mock(FiltersDto.class);
        FiltersCommand command = mock(FiltersCommand.class);
        PixRequestConsultDTO consultDTO = mock(PixRequestConsultDTO.class);

        when(filtersDto.getId()).thenReturn(UUID.randomUUID());
        when(filtersDto.getKeyType()).thenReturn("CPF");
        when(filtersDto.getAccountNumber()).thenReturn(1);
        when(filtersDto.getKeyValue()).thenReturn("123");
        when(filtersDto.getAgencyNumber()).thenReturn(1);
        when(filtersDto.getAccountHolderName()).thenReturn("Nome");
        when(filtersDto.getCreatedTime()).thenReturn(null);
        when(filtersDto.getInactiveTime()).thenReturn(null);
        when(filtersDto.getAccountType()).thenReturn("corrente");

        List<ConsultPixKeyCommand> consultList = List.of(mock(ConsultPixKeyCommand.class));
        when(insertKeyPixInPort.consultKeyPix(any())).thenReturn(consultList);
        when(pixMapper.toPixRequestConsultDTOList(consultList)).thenReturn(List.of(consultDTO));

        List<PixRequestConsultDTO> result = pixService.getPixKey(filtersDto);

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}