package infra.adapters.out.implementsPortOut;


import com.store.itaupixcase.infra.adapters.out.implementsPortOut.ValidDocumentNumberImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidDocumentNumberImplTest {

    private ValidDocumentNumberImpl validator;

    @BeforeEach
    void setUp() {
        validator = new ValidDocumentNumberImpl();
    }

    @Test
    void deveRetornarTrueParaCpfValido() {
        String cpfValido = "39053344705"; // CPF válido
        assertTrue(validator.isCpfValid(cpfValido));
    }

    @Test
    void deveRetornarFalseParaCpfInvalido() {
        String cpfInvalido = "12345678900"; // CPF inválido
        assertFalse(validator.isCpfValid(cpfInvalido));
    }

    @Test
    void deveRetornarTrueParaCnpjValido() {
        String cnpjValido = "11444777000161"; // CNPJ válido
        assertTrue(validator.isCnpjValid(cnpjValido));
    }

    @Test
    void deveRetornarFalseParaCnpjInvalido() {
        String cnpjInvalido = "12345678000100"; // CNPJ inválido
        assertFalse(validator.isCnpjValid(cnpjInvalido));
    }
}