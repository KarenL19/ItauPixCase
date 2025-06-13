package infra.adapters.out.implementsPortOut;

import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import com.store.itaupixcase.infra.adapters.out.implementsPortOut.ValidateIdPixOutPortImpl;
import com.store.itaupixcase.infra.adapters.out.repository.PixRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidateIdPixOutPortImplTest {

    private PixRepository pixRepository;
    private ValidateIdPixOutPortImpl validateIdPixOutPort;

    @BeforeEach
    void setUp() {
        pixRepository = mock(PixRepository.class);
        validateIdPixOutPort = new ValidateIdPixOutPortImpl(pixRepository);
    }

    @Test
    void deveRetornarTrueQuandoIdPixExiste() {
        UUID id = UUID.randomUUID();
        when(pixRepository.existsById(id)).thenReturn(true);

        assertTrue(validateIdPixOutPort.validateIdPix(id));
        verify(pixRepository).existsById(id);
    }

    @Test
    void deveRetornarFalseQuandoIdPixNaoExiste() {
        UUID id = UUID.randomUUID();
        when(pixRepository.existsById(id)).thenReturn(false);

        assertFalse(validateIdPixOutPort.validateIdPix(id));
        verify(pixRepository).existsById(id);
    }


    @Test
    void deveRetornarFalseQuandoIdActiveNaoExiste() {
        UUID id = UUID.randomUUID();
        String keyStatus = "A";
        when(pixRepository.findByIdAndKeyStatusActive(id, keyStatus)).thenReturn(Optional.empty());
        when(pixRepository.findByIdAndKeyStatusActive(id,keyStatus)).thenReturn(Optional.empty());

        assertFalse(validateIdPixOutPort.validateIdActive(id, keyStatus));
        verify(pixRepository).findByIdAndKeyStatusActive(id,keyStatus);
    }
}
