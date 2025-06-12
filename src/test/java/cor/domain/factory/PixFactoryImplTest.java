package cor.domain.factory;

import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import com.store.itaupixcase.cor.domain.factory.impl.PixFactoryImpl;
import com.store.itaupixcase.cor.domain.valueObject.Cpf;
import com.store.itaupixcase.cor.domain.valueObject.Cnpj;
import com.store.itaupixcase.cor.domain.valueObject.Email;
import com.store.itaupixcase.cor.ports.out.ValidDocumentNumberOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PixFactoryImplTest {

    private ValidDocumentNumberOutPort validator;
    private PixFactoryImpl pixFactory;

    @BeforeEach
    void setUp() {
        validator = mock(ValidDocumentNumberOutPort.class);
        when(validator.isCpfValid(anyString())).thenReturn(true);
        when(validator.isCnpjValid(anyString())).thenReturn(true);
        pixFactory = new PixFactoryImpl(validator);
    }

    @Test
    void createShouldReturnCpfInstanceWhenKeyTypeIsCPF() {
        KeyPix keyPix = pixFactory.create("43764669802", KeyType.CPF);
        assertNotNull(keyPix);
        assertTrue(keyPix instanceof Cpf);
    }

    @Test
    void createShouldReturnCnpjInstanceWhenKeyTypeIsCNPJ() {
        KeyPix keyPix = pixFactory.create("89117043000165", KeyType.CNPJ);
        assertNotNull(keyPix);
        assertTrue(keyPix instanceof Cnpj);
    }

    @Test
    void createShouldReturnEmailInstanceWhenKeyTypeIsEMAIL() {
        KeyPix keyPix = pixFactory.create("email@dominio.com", KeyType.EMAIL);
        assertNotNull(keyPix);
        assertTrue(keyPix instanceof Email);
    }
}