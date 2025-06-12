package cor.domain.valueObject;

import com.store.itaupixcase.cor.domain.valueObject.Cnpj;
import com.store.itaupixcase.cor.ports.out.ValidDocumentNumberOutPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CnpjTest {

    @Test
    void deveCriarCnpjValido() {
        ValidDocumentNumberOutPort validator = mock(ValidDocumentNumberOutPort.class);
        when(validator.isCnpjValid("12345678000199")).thenReturn(true);

        Cnpj cnpj = new Cnpj("12345678000199", validator);

        assertEquals("12345678000199", cnpj.getValue());
    }

    @Test
    void deveLancarExcecaoSeFormatoInvalido() {
        ValidDocumentNumberOutPort validator = mock(ValidDocumentNumberOutPort.class);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Cnpj("1234", validator));
        assertTrue(ex.getMessage().contains("exatamente 14 dígitos"));
    }

    @Test
    void deveLancarExcecaoSeCnpjInvalido() {
        ValidDocumentNumberOutPort validator = mock(ValidDocumentNumberOutPort.class);
        when(validator.isCnpjValid("12345678000199")).thenReturn(false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Cnpj("12345678000199", validator));
        assertTrue(ex.getMessage().contains("CNPJ inválido"));
    }
}