package com.store.itaupixcase.infra.adapters.in.dto.create;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PixResponseDTO {
    private UUID id;
}
