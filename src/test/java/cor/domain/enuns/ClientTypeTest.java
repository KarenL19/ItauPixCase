package cor.domain.enuns;

import com.store.itaupixcase.cor.domain.enuns.ClientType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClientTypeTest {

    @Test
    void shouldContainPFAndPJ() {
        assertNotNull(ClientType.valueOf("PF"));
        assertNotNull(ClientType.valueOf("PJ"));
    }

    @Test
    void valuesShouldReturnAllTypes() {
        ClientType[] values = ClientType.values();
        assertEquals(2, values.length);
        assertTrue(java.util.Arrays.asList(values).contains(ClientType.PF));
        assertTrue(java.util.Arrays.asList(values).contains(ClientType.PJ));
    }
}
