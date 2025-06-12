package com.store.itaupixcase.infra.adapters.in.dto.update;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PixRequestUpdateDTO {
    private UUID id;
    private String accountType;
    private Integer accountNumber;
    private Integer agencyNumber;
    private String accountHolderName;
    private String accountHolderSurname;


}
