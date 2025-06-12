package infra.adapters.out.implementsPortOut;

import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import com.store.itaupixcase.infra.adapters.out.implementsPortOut.ValidPixKeyOutPortImpl;
import com.store.itaupixcase.infra.adapters.out.repository.PixRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidPixKeyOutPortImplTest {

    private PixRepository pixRepository;
    private ValidPixKeyOutPortImpl validPixKeyOutPort;

    @BeforeEach
    void setUp() {
        pixRepository = mock(PixRepository.class);
        validPixKeyOutPort = new ValidPixKeyOutPortImpl(pixRepository);
    }

    @Test
    void deveRetornarQuantidadeDeChavesPix() {
        Integer agencyNumber = 1234;
        Integer accountNumber = 56789;
        Long expectedCount = 2L;

        when(pixRepository.countByKeyTypeAndKeyValue(agencyNumber, accountNumber)).thenReturn(expectedCount);

        Long result = validPixKeyOutPort.countPixKey(agencyNumber, accountNumber);

        assertEquals(expectedCount, result);
        verify(pixRepository).countByKeyTypeAndKeyValue(agencyNumber, accountNumber);
    }

    @Test
    void deveRetornarTrueQuandoPixKeyExiste() {
        String pixKey = "chave@pix.com";
        PixEntity entity = new PixEntity();
        when(pixRepository.findFirstByKeyValue(pixKey)).thenReturn(Optional.of(entity));

        boolean exists = validPixKeyOutPort.existsPixKey(pixKey);

        assertTrue(exists);
        verify(pixRepository).findFirstByKeyValue(pixKey);
    }

    @Test
    void deveRetornarFalseQuandoPixKeyNaoExiste() {
        String pixKey = "naoexiste@pix.com";
        when(pixRepository.findFirstByKeyValue(pixKey)).thenReturn(Optional.empty());

        boolean exists = validPixKeyOutPort.existsPixKey(pixKey);

        assertFalse(exists);
        verify(pixRepository).findFirstByKeyValue(pixKey);
    }
}