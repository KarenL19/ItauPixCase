package cor.domain.valueObject;


import com.store.itaupixcase.cor.domain.valueObject.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void deveCriarEmailValido() {
        Email email = new Email("usuario@dominio.com");
        assertEquals("usuario@dominio.com", email.getValue());
    }

    @Test
    void deveLancarExcecaoSeEmailNulo() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Email(null));
        assertTrue(ex.getMessage().contains("Formato de email inválido"));
    }

    @Test
    void deveLancarExcecaoSeEmailSemArroba() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Email("usuariodominio.com"));
        assertTrue(ex.getMessage().contains("Formato de email inválido"));
    }

    @Test
    void deveLancarExcecaoSeEmailMuitoLongo() {
        String emailLongo = "a".repeat(70) + "@dominio.com";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new Email(emailLongo));
        assertTrue(ex.getMessage().contains("no máximo 77 caracteres"));
    }
}