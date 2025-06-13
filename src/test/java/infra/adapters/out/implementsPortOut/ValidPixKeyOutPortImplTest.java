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
    void countPixKey_deveRetornarQuantidadeCorreta() {
        Integer agencyNumber = 123;
        Integer accountNumber = 456;
        String keyStatus = "A";
        Long expected = 5L;

        when(pixRepository.countByKeyTypeAndKeyValue(agencyNumber, accountNumber, keyStatus)).thenReturn(expected);

        Long result = validPixKeyOutPort.countPixKey(agencyNumber, accountNumber, keyStatus);

        assertEquals(expected, result);
        verify(pixRepository).countByKeyTypeAndKeyValue(agencyNumber, accountNumber, keyStatus);
    }

    @Test
    void existsPixKey_deveRetornarTrueQuandoExiste() {
        String pixKey = "chave@pix.com";
        String keyStatus = "A";
        when(pixRepository.findFirstByKeyValueAndKeyStatus(pixKey, keyStatus))
                .thenReturn(Optional.of(new PixEntity()));

        boolean exists = validPixKeyOutPort.existsPixKey(pixKey, keyStatus);

        assertTrue(exists);
        verify(pixRepository).findFirstByKeyValueAndKeyStatus(pixKey, keyStatus);
    }

    @Test
    void existsPixKey_deveRetornarFalseQuandoNaoExiste() {
        String pixKey = "naoexiste@pix.com";
        String keyStatus = "A";
        when(pixRepository.findFirstByKeyValueAndKeyStatus(pixKey, keyStatus))
                .thenReturn(Optional.empty());

        boolean exists = validPixKeyOutPort.existsPixKey(pixKey, keyStatus);

        assertFalse(exists);
        verify(pixRepository).findFirstByKeyValueAndKeyStatus(pixKey, keyStatus);
    }
}