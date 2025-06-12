package com.store.itaupixcase.infra.adapters.in.dto.create;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixRequestDTO {
    private String keyType;
    private String keyValue;
    private String accountType;
    private Integer accountNumber;
    private Integer agencyNumber;
    private String accountHolderName;
    private String accountHolderSurname;
    private String clientType;
}
