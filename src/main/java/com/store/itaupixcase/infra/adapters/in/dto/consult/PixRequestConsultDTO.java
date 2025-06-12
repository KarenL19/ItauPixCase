package com.store.itaupixcase.infra.adapters.in.dto.consult;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PixRequestConsultDTO {
    private UUID id;
    private String keyType;
    private String keyValue;
    private String accountType;
    private Integer accountNumber;
    private Integer agencyNumber;
    private String accountHolderName;
    private String accountHolderSurname;
    private String createdTime;
    private String inactiveTime;
}
