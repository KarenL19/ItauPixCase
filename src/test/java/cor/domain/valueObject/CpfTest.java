package cor.domain.valueObject;

import com.store.itaupixcase.cor.domain.valueObject.Cpf;
import com.store.itaupixcase.cor.ports.out.ValidDocumentNumberOutPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CpfTest {

    @Test
    void deveCriarCpfValido() {
        ValidDocumentNumberOutPort validator = mock(ValidDocumentNumberOutPort.class);
        when(validator.isCpfValid("12345678901")).thenReturn(true);

        Cpf cpf = new Cpf("12345678901", validator);

        assertEquals("12345678901", cpf.getValue());
    }

    @Test
    void deveLancarExcecaoSeFormatoInvalido() {
        ValidDocumentNumberOutPort validator = mock(ValidDocumentNumberOutPort.class);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Cpf("1234", validator));
        assertTrue(ex.getMessage().contains("exatamente 11 dígitos"));
    }

    @Test
    void deveLancarExcecaoSeCpfInvalido() {
        ValidDocumentNumberOutPort validator = mock(ValidDocumentNumberOutPort.class);
        when(validator.isCpfValid("12345678901")).thenReturn(false);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Cpf("12345678901", validator));
        assertTrue(ex.getMessage().contains("CPF inválido"));
    }
}