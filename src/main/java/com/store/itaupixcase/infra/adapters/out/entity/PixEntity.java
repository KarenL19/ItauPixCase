package com.store.itaupixcase.infra.adapters.out.entity;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "chave_pix")
public class PixEntity {


        @Id
        @Column(name = "id", nullable = false)
        private UUID id;

        @Column(name = "tipo_chave", length = 9, nullable = false)
        private String keyType;

        @Column(name = "valor_chave", length = 77, nullable = false, unique = true)
        private String keyValue;

        @Column(name = "tipo_conta", length = 10, nullable = false)
        private String accountType;

        @Column(name = "numero_agencia", nullable = false)
        private Integer agencyNumber;

        @Column(name = "numero_conta", nullable = false)
        private Integer accountNumber;

        @Column(name = "nome_cliente", length = 30, nullable = false)
        private String holderName;

        @Column(name = "tp_cliente", length = 11, nullable = false)
        private String clientType;

        @Column(name = "st_chave", length = 1, nullable = false)
        private String keyStatus;

        @Column(name = "sobrenome_cliente", length = 45)
        private String holderSurname;

        @Column(name = "data_criacao", nullable = false)
        private LocalDateTime createdTime;

        @Column(name = "data_inativacao")
        private LocalDateTime inactiveTime;


        public PixEntity(UUID id, AccountType accountType, Integer agencyNumber, Integer accountNumber, String accountHolderName, String accountHolderSurname) {
            this.id = id;
            this.accountType = accountType.toValue();
            this.agencyNumber = agencyNumber;
            this.accountNumber = accountNumber;
            this.holderName = accountHolderName;
            this.holderSurname = accountHolderSurname;
        }

}
