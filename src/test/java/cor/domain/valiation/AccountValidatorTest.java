package cor.domain.valiation;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.domain.validation.AccountValidator;
import com.store.itaupixcase.cor.ports.out.ValidateIdPixOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountValidatorTest {

    private ValidateIdPixOutPort validateIdPixOutPort;
    private AccountValidator validator;

    @BeforeEach
    void setUp() {
        validateIdPixOutPort = mock(ValidateIdPixOutPort.class);
        validator = new AccountValidator(validateIdPixOutPort);
    }

    @Test
    void validateAccountNumberShouldThrowWhenNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateAccountNumber(null));
        assertTrue(ex.getMessage().contains("não pode ser vazio"));
    }

    @Test
    void validateAccountNumberShouldThrowWhenMoreThan8Digits() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateAccountNumber(123456789));
        assertTrue(ex.getMessage().contains("não pode exceder 8 digitos"));
    }

    @Test
    void validateAccountNumberShouldPassForValidNumber() {
        assertDoesNotThrow(() -> validator.validateAccountNumber(12345678));
    }

    @Test
    void validateAccountTypeShouldReturnCorrente() {
        assertEquals(AccountType.CORRENTE, validator.validateAccountType("corrente"));
    }

    @Test
    void validateAccountTypeShouldReturnPoupanca() {
        assertEquals(AccountType.POUPANCA, validator.validateAccountType("poupanca"));
        assertEquals(AccountType.POUPANCA, validator.validateAccountType("poupança"));
    }

    @Test
    void validateAccountTypeShouldThrowForInvalidType() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateAccountType("salario"));
        assertTrue(ex.getMessage().contains("Tipo de conta inválido"));
    }

    @Test
    void validateAccountTypeShouldThrowWhenLengthExceeds() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateAccountType("muitolongotipo"));
        assertTrue(ex.getMessage().contains("não deve exceder 10 caracteres"));
    }

    @Test
    void validateAgencyNumberShouldThrowWhenNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateAgencyNumber(null));
        assertTrue(ex.getMessage().contains("não pode ser vazio"));
    }

    @Test
    void validateAgencyNumberShouldThrowWhenMoreThan4Digits() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateAgencyNumber(12345));
        assertTrue(ex.getMessage().contains("não pode exceder 4 dígitos"));
    }

    @Test
    void validateAgencyNumberShouldPassForValidNumber() {
        assertDoesNotThrow(() -> validator.validateAgencyNumber(1234));
    }

    @Test
    void validateHolderNameShouldThrowWhenNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> validator.validateHolderName(null));
        assertThrows(IllegalArgumentException.class, () -> validator.validateHolderName(""));
    }

    @Test
    void validateHolderNameShouldThrowWhenExceeds30Chars() {
        String longName = "A".repeat(31);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateHolderName(longName));
        assertTrue(ex.getMessage().contains("não pode exceder 30 caracteres"));
    }

    @Test
    void validateHolderNameShouldPassForValidName() {
        assertDoesNotThrow(() -> validator.validateHolderName("Nome Titular"));
    }

    @Test
    void validateHolderSurnameShouldThrowWhenExceeds45Chars() {
        String longSurname = "A".repeat(46);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> validator.validateHolderSurname(longSurname));
        assertTrue(ex.getMessage().contains("não pode exceder 45 caracteres"));
    }

    @Test
    void validateHolderSurnameShouldPassForValidSurname() {
        assertDoesNotThrow(() -> validator.validateHolderSurname("Sobrenome Titular"));
    }

    @Test
    void validateIdPixShouldThrowWhenNotExists() {
        UUID id = UUID.randomUUID();
        when(validateIdPixOutPort.validateIdPix(id)).thenReturn(false);
        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> validator.validateIdPix(id));
        assertTrue(ex.getMessage().contains("ID não existe"));
    }

    @Test
    void validateIdPixShouldPassWhenExists() {
        UUID id = UUID.randomUUID();
        when(validateIdPixOutPort.validateIdPix(id)).thenReturn(true);
        assertDoesNotThrow(() -> validator.validateIdPix(id));
    }

    @Test
    void validateIdActiveShouldThrowWhenNotExists() {
        UUID id = UUID.randomUUID();
        when(validateIdPixOutPort.validateIdActive(id)).thenReturn(false);
        NoSuchElementException ex = assertThrows(NoSuchElementException.class,
                () -> validator.validateIdActive(id));
        assertTrue(ex.getMessage().contains("ID ativo não encontrado"));
    }

    @Test
    void validateIdActiveShouldPassWhenExists() {
        UUID id = UUID.randomUUID();
        when(validateIdPixOutPort.validateIdActive(id)).thenReturn(true);
        assertDoesNotThrow(() -> validator.validateIdActive(id));
    }
}