package cor.domain.valiation;

import com.store.itaupixcase.cor.domain.enuns.ClientType;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import com.store.itaupixcase.cor.domain.validation.PixKeyValidator;
import com.store.itaupixcase.cor.ports.out.ValidatePixKeyOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PixKeyValidatorTest {

    private ValidatePixKeyOutPort validatePixKeyOutPort;
    private PixKeyValidator validator;

    @BeforeEach
    void setUp() {
        validatePixKeyOutPort = mock(ValidatePixKeyOutPort.class);
        validator = new PixKeyValidator(validatePixKeyOutPort);
    }


    @Test
    void validateTypeDeveRetornarPF() {
        assertEquals(ClientType.PF, validator.validateType("pf"));
        assertEquals(ClientType.PF, validator.validateType("PF"));
        assertEquals(ClientType.PF, validator.validateType(" Pf "));
    }

    @Test
    void validateTypeDeveRetornarPJ() {
        assertEquals(ClientType.PJ, validator.validateType("pj"));
        assertEquals(ClientType.PJ, validator.validateType("PJ"));
    }

    @Test
    void validateTypeDeveLancarExcecaoParaNullOuInvalido() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateType(null));
        assertThrows(IllegalArgumentException.class, () -> validator.validateType("abc"));
    }

    // validateKeysByCLient
    @Test
    void validateKeysByCLientDeveLancarExcecaoParaAgenciaOuContaNulaOuZero() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateKeysByCLient(null, 1, "PF"));
        assertThrows(IllegalArgumentException.class, () -> validator.validateKeysByCLient(1, null, "PF"));
        assertThrows(IllegalArgumentException.class, () -> validator.validateKeysByCLient(0, 1, "PF"));
        assertThrows(IllegalArgumentException.class, () -> validator.validateKeysByCLient(1, 0, "PF"));
    }

    @Test
    void validateKeysByCLientDeveLancarExcecaoSeLimitePFExcedido() {
        when(validatePixKeyOutPort.countPixKey(1, 2)).thenReturn(5L);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateKeysByCLient(1, 2, "PF"));
        assertTrue(ex.getMessage().contains("pessoa física"));
    }

    @Test
    void validateKeysByCLientDeveLancarExcecaoSeLimitePJExcedido() {
        when(validatePixKeyOutPort.countPixKey(1, 2)).thenReturn(20L);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateKeysByCLient(1, 2, "PJ"));
        assertTrue(ex.getMessage().contains("pessoa jurídica"));
    }

    @Test
    void validateKeysByCLientDevePermitirAbaixoDoLimite() {
        when(validatePixKeyOutPort.countPixKey(1, 2)).thenReturn(4L);
        assertDoesNotThrow(() -> validator.validateKeysByCLient(1, 2, "PF"));
        when(validatePixKeyOutPort.countPixKey(1, 2)).thenReturn(19L);
        assertDoesNotThrow(() -> validator.validateKeysByCLient(1, 2, "PJ"));
    }

    // existsPixKey
    @Test
    void existsPixKeyDeveLancarExcecaoParaNullOuVazia() {
        assertThrows(IllegalArgumentException.class, () -> validator.existsPixKey(null));
        assertThrows(IllegalArgumentException.class, () -> validator.existsPixKey(""));
    }

    @Test
    void existsPixKeyDeveLancarExcecaoSeChaveJaExiste() {
        when(validatePixKeyOutPort.existsPixKey("chave")).thenReturn(true);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.existsPixKey("chave"));
        assertTrue(ex.getMessage().contains("Chave Pix já existe"));
    }

    @Test
    void existsPixKeyDevePermitirSeNaoExiste() {
        when(validatePixKeyOutPort.existsPixKey("novaChave")).thenReturn(false);
        assertDoesNotThrow(() -> validator.existsPixKey("novaChave"));
    }

    @Test
    void validateTypePersonDeveLancarExcecaoParaTipoPessoaVazio() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateTypePerson(null, KeyType.CPF.name()));
        assertThrows(IllegalArgumentException.class, () -> validator.validateTypePerson("", KeyType.CPF.name()));
    }

    @Test
    void validateTypePersonDeveLancarExcecaoParaCPFComPJ() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateTypePerson("PJ", KeyType.CPF.name()));
    }

    @Test
    void validateTypePersonDeveLancarExcecaoParaCNPJComPF() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateTypePerson("PF", KeyType.CNPJ.name()));
    }

    @Test
    void validateTypePersonDevePermitirCombinacoesValidas() {
        assertDoesNotThrow(() -> validator.validateTypePerson("PF", KeyType.CPF.name()));
        assertDoesNotThrow(() -> validator.validateTypePerson("PJ", KeyType.CNPJ.name()));
    }
}