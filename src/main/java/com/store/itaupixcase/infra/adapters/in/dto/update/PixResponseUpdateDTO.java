package com.store.itaupixcase.infra.adapters.in.dto.update;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PixResponseUpdateDTO {
    private UUID id;
    private String keyType;
    private String keyValue;
    private String accountType;
    private Integer accountNumber;
    private Integer agencyNumber;
    private String accountHolderName;
    private String accountHolderSurname;
    private LocalDateTime createdTime;

}
