package infra.adapters.out.entity;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PixEntityTest {

    @Test
    void deveCriarComConstrutorCompletoEGetters() {
        UUID id = UUID.randomUUID();
        String keyType = "EMAIL";
        String keyValue = "usuario@dominio.com";
        String accountType = "CORRENTE";
        Integer agencyNumber = 1234;
        Integer accountNumber = 56789;
        String holderName = "João";
        String clientType = "PF";
        String keyStatus = "A";
        String holderSurname = "Silva";
        LocalDateTime createdTime = LocalDateTime.now();
        LocalDateTime inactiveTime = LocalDateTime.now().plusDays(1);

        PixEntity entity = new PixEntity(
                id, keyType, keyValue, accountType, agencyNumber, accountNumber,
                holderName, clientType, keyStatus, holderSurname, createdTime, inactiveTime
        );

        assertEquals(id, entity.getId());
        assertEquals(keyType, entity.getKeyType());
        assertEquals(keyValue, entity.getKeyValue());
        assertEquals(accountType, entity.getAccountType());
        assertEquals(agencyNumber, entity.getAgencyNumber());
        assertEquals(accountNumber, entity.getAccountNumber());
        assertEquals(holderName, entity.getHolderName());
        assertEquals(clientType, entity.getClientType());
        assertEquals(keyStatus, entity.getKeyStatus());
        assertEquals(holderSurname, entity.getHolderSurname());
        assertEquals(createdTime, entity.getCreatedTime());
        assertEquals(inactiveTime, entity.getInactiveTime());
    }

    @Test
    void deveCriarComBuilder() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdTime = LocalDateTime.now();

        PixEntity entity = PixEntity.builder()
                .id(id)
                .keyType("CPF")
                .keyValue("12345678901")
                .accountType("POUPANCA")
                .agencyNumber(4321)
                .accountNumber(98765)
                .holderName("Maria")
                .clientType("PJ")
                .keyStatus("I")
                .holderSurname("Oliveira")
                .createdTime(createdTime)
                .inactiveTime(null)
                .build();

        assertEquals(id, entity.getId());
        assertEquals("CPF", entity.getKeyType());
        assertEquals("12345678901", entity.getKeyValue());
        assertEquals("POUPANCA", entity.getAccountType());
        assertEquals(4321, entity.getAgencyNumber());
        assertEquals(98765, entity.getAccountNumber());
        assertEquals("Maria", entity.getHolderName());
        assertEquals("PJ", entity.getClientType());
        assertEquals("I", entity.getKeyStatus());
        assertEquals("Oliveira", entity.getHolderSurname());
        assertEquals(createdTime, entity.getCreatedTime());
        assertNull(entity.getInactiveTime());
    }

    @Test
    void devePermitirSetters() {
        PixEntity entity = new PixEntity();
        UUID id = UUID.randomUUID();
        LocalDateTime createdTime = LocalDateTime.now();

        entity.setId(id);
        entity.setKeyType("CNPJ");
        entity.setKeyValue("12345678000199");
        entity.setAccountType("SALARIO");
        entity.setAgencyNumber(1111);
        entity.setAccountNumber(22222);
        entity.setHolderName("Empresa");
        entity.setClientType("PJ");
        entity.setKeyStatus("A");
        entity.setHolderSurname("LTDA");
        entity.setCreatedTime(createdTime);
        entity.setInactiveTime(null);

        assertEquals(id, entity.getId());
        assertEquals("CNPJ", entity.getKeyType());
        assertEquals("12345678000199", entity.getKeyValue());
        assertEquals("SALARIO", entity.getAccountType());
        assertEquals(1111, entity.getAgencyNumber());
        assertEquals(22222, entity.getAccountNumber());
        assertEquals("Empresa", entity.getHolderName());
        assertEquals("PJ", entity.getClientType());
        assertEquals("A", entity.getKeyStatus());
        assertEquals("LTDA", entity.getHolderSurname());
        assertEquals(createdTime, entity.getCreatedTime());
        assertNull(entity.getInactiveTime());
    }

    @Test
    void deveCriarComConstrutorPersonalizado() {
        UUID id = UUID.randomUUID();
        AccountType accountType = AccountType.CORRENTE;
        Integer agencyNumber = 123;
        Integer accountNumber = 456;
        String holderName = "Fulano";
        String holderSurname = "de Tal";

        PixEntity entity = new PixEntity(id, accountType, agencyNumber, accountNumber, holderName, holderSurname);

        assertEquals(id, entity.getId());
        assertEquals(accountType.toValue(), entity.getAccountType());
        assertEquals(agencyNumber, entity.getAgencyNumber());
        assertEquals(accountNumber, entity.getAccountNumber());
        assertEquals(holderName, entity.getHolderName());
        assertEquals(holderSurname, entity.getHolderSurname());
    }
}