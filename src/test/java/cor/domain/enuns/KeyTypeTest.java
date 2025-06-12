package cor.domain.enuns;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KeyTypeTest {

    @Test
    void shouldContainAllKeyTypes() {
        assertNotNull(KeyType.valueOf("CPF"));
        assertNotNull(KeyType.valueOf("CNPJ"));
        assertNotNull(KeyType.valueOf("EMAIL"));
    }

    @Test
    void valuesShouldReturnAllTypes() {
        KeyType[] values = KeyType.values();
        assertEquals(3, values.length);
        assertTrue(java.util.Arrays.asList(values).contains(KeyType.CPF));
        assertTrue(java.util.Arrays.asList(values).contains(KeyType.CNPJ));
        assertTrue(java.util.Arrays.asList(values).contains(KeyType.EMAIL));
    }
}