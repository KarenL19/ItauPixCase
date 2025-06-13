package com.store.itaupixcase.infra.adapters.out.mapper;

import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PixMapper {

    PixEntity toEntityInsert(Pix pix);
    PixEntity toEntityUpdate(Pix pix);
    PixResponseUpdateDTO toResponseUpdateDTO(PixEntity pix);
    List<ConsultPixKeyCommand> toConsultPixKeyCommandList(List<PixEntity> pixEntities);
    List<PixRequestConsultDTO> toPixRequestConsultDTOList(List<ConsultPixKeyCommand> commands);
}
