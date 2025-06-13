package com.store.itaupixcase.infra.adapters.out.mapper.impl;

import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
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
    public List<ConsultPixKeyCommand> toConsultPixKeyCommandList(List<PixEntity> pixEntities) {
        return pixEntities.stream()
                .map(pix -> new ConsultPixKeyCommand(
                        pix.getId(),
                        pix.getKeyType(),
                        pix.getKeyValue(),
                        pix.getAccountType(),
                        pix.getAccountNumber(),
                        pix.getAgencyNumber(),
                        pix.getHolderName(),
                        pix.getHolderSurname(),
                        String.valueOf(pix.getCreatedTime()),
                        String.valueOf(pix.getInactiveTime())
                        ))
                .toList();
    }

    @Override
   public List<PixRequestConsultDTO> toPixRequestConsultDTOList(List<ConsultPixKeyCommand> commands) {
       return commands.stream()
               .map(cmd -> PixRequestConsultDTO.builder()
                       .id(cmd.getId())
                       .keyType(cmd.getKeyType())
                       .keyValue(cmd.getKeyValue())
                       .accountType(cmd.getAccountType())
                       .accountNumber(cmd.getAccountNumber())
                       .agencyNumber(cmd.getAgencyNumber())
                       .accountHolderName(cmd.getAccountHolderName())
                       .accountHolderSurname(cmd.getAccountHolderSurname())
                       .inactiveTime(cmd.getInactiveTime())
                       .createdTime(cmd.getCreatedTime())
                       .build())
               .toList();
   }


}
