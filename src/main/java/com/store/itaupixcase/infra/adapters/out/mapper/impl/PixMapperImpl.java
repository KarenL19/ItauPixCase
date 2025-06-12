package com.store.itaupixcase.infra.adapters.out.mapper.impl;

import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import com.store.itaupixcase.infra.adapters.out.mapper.PixMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PixMapperImpl implements PixMapper {

    @Override
    public PixEntity toEntityInsert(Pix pix) {
        return PixEntity.builder()
                .id(pix.getId())
                .keyType(pix.getKeyType().name())
                .accountType(pix.getAccountType().name())
                .keyValue(pix.getKeyPix().getValue())
                .agencyNumber(pix.getAgencyNumber())
                .accountNumber(pix.getAccountNumber())
                .holderName(pix.getAccountHolderName())
                .holderSurname(pix.getAccountHolderSurname())
                .createdTime(pix.getCreatedTime())
                .clientType(pix.getClientType().name())
                .keyStatus(pix.getKeyStatus())
                .build();
    }

    @Override
    public PixEntity toEntityUpdate(Pix pix) {
        return new PixEntity(pix.getId(),
                pix.getAccountType(),
                pix.getAgencyNumber(),
                pix.getAccountNumber(),
                pix.getAccountHolderName(),
                pix.getAccountHolderSurname());
    }

    @Override
    public PixResponseUpdateDTO toResponseUpdateDTO(PixEntity pix) {
        return PixResponseUpdateDTO.builder()
                .id(pix.getId())
                .keyType(pix.getKeyType())
                .keyValue(pix.getKeyValue())
                .accountType(pix.getAccountType())
                .accountNumber(pix.getAccountNumber())
                .agencyNumber(pix.getAgencyNumber())
                .accountHolderName(pix.getHolderName())
                .accountHolderSurname(pix.getHolderSurname())
                .createdTime(pix.getCreatedTime())
                .build();
    }

    @Override
    public List<PixRequestConsultDTO> toResponseConsultDTOList(List<PixEntity> pixEntities) {
        return pixEntities.stream()
                .map(this::toResponseConsultDTO)
                .toList();
    }

    private PixRequestConsultDTO toResponseConsultDTO(PixEntity pix) {
        return PixRequestConsultDTO.builder()
                .id(pix.getId())
                .keyType(pix.getKeyType())
                .keyValue(pix.getKeyValue())
                .accountType(pix.getAccountType())
                .accountNumber(pix.getAccountNumber())
                .agencyNumber(pix.getAgencyNumber())
                .accountHolderName(pix.getHolderName())
                .accountHolderSurname(pix.getHolderSurname())
                .inactiveTime(pix.getInactiveTime() == null ? "" : String.valueOf(pix.getInactiveTime()))
                .createdTime(String.valueOf(pix.getCreatedTime()))
                .build();
    }


}
