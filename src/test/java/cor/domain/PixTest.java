package cor.domain;

import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.domain.enuns.ClientType;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PixTest {

    @Test
    void deveCriarPixComConstrutorCompleto() {
        KeyPix keyPix = mock(KeyPix.class);
        Pix pix = new Pix(
                KeyType.CPF,
                keyPix,
                AccountType.CORRENTE,
                1234,
                56789,
                "João",
                "Silva",
                ClientType.PF
        );

        assertNotNull(pix.getId());
        assertEquals(KeyType.CPF, pix.getKeyType());
        assertEquals(keyPix, pix.getKeyPix());
        assertEquals(AccountType.CORRENTE, pix.getAccountType());
        assertEquals(1234, pix.getAgencyNumber());
        assertEquals(56789, pix.getAccountNumber());
        assertEquals("João", pix.getAccountHolderName());
        assertEquals("Silva", pix.getAccountHolderSurname());
        assertEquals(ClientType.PF, pix.getClientType());
        assertEquals("A", pix.getKeyStatus());
        assertNotNull(pix.getCreatedTime());
        assertNull(pix.getInactiveTime());
    }

    @Test
    void deveCriarPixComConstrutorParcial() {
        UUID id = UUID.randomUUID();
        Pix pix = new Pix(
                id,
                AccountType.POUPANCA,
                98765,
                4321,
                "Maria",
                "Oliveira"
        );

        assertEquals(id, pix.getId());
        assertEquals(AccountType.POUPANCA, pix.getAccountType());
        assertEquals(98765, pix.getAccountNumber());
        assertEquals(4321, pix.getAgencyNumber());
        assertEquals("Maria", pix.getAccountHolderName());
        assertEquals("Oliveira", pix.getAccountHolderSurname());
        assertNull(pix.getKeyType());
        assertNull(pix.getKeyPix());
        assertNull(pix.getClientType());
        assertNull(pix.getCreatedTime());
        assertNull(pix.getInactiveTime());
        assertNull(pix.getKeyStatus());
    }

    @Test
    void devePermitirSettersEGets() {
        Pix pix = new Pix(
                KeyType.EMAIL,
                mock(KeyPix.class),
                AccountType.CORRENTE,
                1,
                2,
                "A",
                "B",
                ClientType.PJ
        );

        UUID novoId = UUID.randomUUID();
        pix.setId(novoId);
        assertEquals(novoId, pix.getId());

        pix.setKeyType(KeyType.CNPJ);
        assertEquals(KeyType.CNPJ, pix.getKeyType());

        KeyPix novoKeyPix = mock(KeyPix.class);
        pix.setKeyPix(novoKeyPix);
        assertEquals(novoKeyPix, pix.getKeyPix());

        pix.setAccountType(AccountType.POUPANCA);
        assertEquals(AccountType.POUPANCA, pix.getAccountType());

        pix.setAgencyNumber(99);
        assertEquals(99, pix.getAgencyNumber());

        pix.setAccountNumber(88);
        assertEquals(88, pix.getAccountNumber());

        pix.setAccountHolderName("NovoNome");
        assertEquals("NovoNome", pix.getAccountHolderName());

        pix.setAccountHolderSurname("NovoSobrenome");
        assertEquals("NovoSobrenome", pix.getAccountHolderSurname());

        pix.setClientType(ClientType.PF);
        assertEquals(ClientType.PF, pix.getClientType());

        LocalDateTime now = LocalDateTime.now();
        pix.setCreatedTime(now);
        assertEquals(now, pix.getCreatedTime());

        pix.setInactiveTime(now.plusDays(1));
        assertEquals(now.plusDays(1), pix.getInactiveTime());

        pix.setKeyStatus("I");
        assertEquals("I", pix.getKeyStatus());
    }
}