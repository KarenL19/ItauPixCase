package infra.adapters.out.mapper;

import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.domain.enuns.ClientType;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import com.store.itaupixcase.cor.domain.valueObject.Email;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import com.store.itaupixcase.infra.adapters.out.mapper.impl.PixMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PixMapperImplTest {

    private PixMapperImpl mapper;

    @BeforeEach
    void setUp() {
        mapper = new PixMapperImpl();
    }

    @Test
    void toEntityInsert_deveMapearCorretamente() {
        Email email = new Email("email@teste.com");
        UUID id = UUID.randomUUID();
        Pix pix = new Pix();

        pix.setId(id);
        pix.setKeyType(KeyType.EMAIL);
        pix.setAccountType(AccountType.CORRENTE);
        pix.setKeyPix(email);
        pix.setAgencyNumber(1234);
        pix.setAccountNumber(56789);
        pix.setAccountHolderName("João");
        pix.setAccountHolderSurname("Silva");
        pix.setCreatedTime(LocalDateTime.now());
        pix.setClientType(ClientType.PF);
        pix.setKeyStatus("A");

        PixEntity entity = mapper.toEntityInsert(pix);

        assertEquals(id, entity.getId());
        assertEquals("EMAIL", entity.getKeyType());
        assertEquals("CORRENTE", entity.getAccountType());
        assertEquals("email@teste.com", entity.getKeyValue());
        assertEquals(1234, entity.getAgencyNumber());
        assertEquals(56789, entity.getAccountNumber());
        assertEquals("João", entity.getHolderName());
        assertEquals("Silva", entity.getHolderSurname());
        assertEquals("PF", entity.getClientType());
        assertEquals("A", entity.getKeyStatus());
    }

    @Test
    void toEntityUpdate_deveMapearCorretamente() {
        UUID id = UUID.randomUUID();
        Pix pix = new Pix();
        pix.setId(id);
        pix.setAccountType(AccountType.POUPANCA);
        pix.setAgencyNumber(1111);
        pix.setAccountNumber(22222);
        pix.setAccountHolderName("Maria");
        pix.setAccountHolderSurname("Oliveira");

        PixEntity entity = mapper.toEntityUpdate(pix);

        assertEquals(id, entity.getId());
        assertEquals(AccountType.POUPANCA.toValue(), entity.getAccountType());
        assertEquals(1111, entity.getAgencyNumber());
        assertEquals(22222, entity.getAccountNumber());
        assertEquals("Maria", entity.getHolderName());
        assertEquals("Oliveira", entity.getHolderSurname());
    }

    @Test
    void toResponseUpdateDTO_deveMapearCorretamente() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdTime = LocalDateTime.now();
        PixEntity entity = PixEntity.builder()
                .id(id)
                .keyType("CPF")
                .keyValue("12345678901")
                .accountType("SALARIO")
                .accountNumber(33333)
                .agencyNumber(4444)
                .holderName("Empresa")
                .holderSurname("LTDA")
                .createdTime(createdTime)
                .build();

        PixResponseUpdateDTO dto = mapper.toResponseUpdateDTO(entity);

        assertEquals(id, dto.getId());
        assertEquals("CPF", dto.getKeyType());
        assertEquals("12345678901", dto.getKeyValue());
        assertEquals("SALARIO", dto.getAccountType());
        assertEquals(33333, dto.getAccountNumber());
        assertEquals(4444, dto.getAgencyNumber());
        assertEquals("Empresa", dto.getAccountHolderName());
        assertEquals("LTDA", dto.getAccountHolderSurname());
        assertEquals(createdTime, dto.getCreatedTime());
    }

    @Test
    void toResponseConsultDTOList_deveMapearListaCorretamente() {
        PixEntity entity1 = PixEntity.builder()
                .id(UUID.randomUUID())
                .keyType("EMAIL")
                .keyValue("a@b.com")
                .accountType("CORRENTE")
                .accountNumber(1)
                .agencyNumber(2)
                .holderName("A")
                .holderSurname("B")
                .createdTime(LocalDateTime.now())
                .inactiveTime(null)
                .build();

        PixEntity entity2 = PixEntity.builder()
                .id(UUID.randomUUID())
                .keyType("CPF")
                .keyValue("12345678901")
                .accountType("POUPANCA")
                .accountNumber(3)
                .agencyNumber(4)
                .holderName("C")
                .holderSurname("D")
                .createdTime(LocalDateTime.now())
                .inactiveTime(LocalDateTime.now())
                .build();

        List<PixRequestConsultDTO> dtos = mapper.toResponseConsultDTOList(List.of(entity1, entity2));

        assertEquals(2, dtos.size());
        assertEquals(entity1.getId(), dtos.get(0).getId());
        assertEquals(entity2.getId(), dtos.get(1).getId());
        assertEquals("EMAIL", dtos.get(0).getKeyType());
        assertEquals("CPF", dtos.get(1).getKeyType());
    }
}