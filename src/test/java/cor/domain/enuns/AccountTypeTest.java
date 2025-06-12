package cor.domain.enuns;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTypeTest {

    @Test
    void toValueShouldReturnCorrectString() {
        assertEquals("CORRENTE", AccountType.CORRENTE.toValue());
        assertEquals("POUPANCA", AccountType.POUPANCA.toValue());
    }
}