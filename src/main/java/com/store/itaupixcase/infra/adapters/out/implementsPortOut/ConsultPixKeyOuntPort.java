package com.store.itaupixcase.infra.adapters.out.implementsPortOut;

import com.store.itaupixcase.cor.ports.out.ConsultPixOutPort;
import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.infra.adapters.out.mapper.PixMapper;
import com.store.itaupixcase.infra.adapters.out.repository.PixConsultRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsultPixKeyOuntPort implements ConsultPixOutPort {

    private final PixConsultRepository pixConsultRepository;
    private final PixMapper pixMapper;

    public ConsultPixKeyOuntPort(PixConsultRepository pixConsultRepository, PixMapper pixMapper) {
        this.pixConsultRepository = pixConsultRepository;
        this.pixMapper = pixMapper;
    }

    @Override
    public List<ConsultPixKeyCommand> consultKeyPix(FiltersCommand command) {
        return pixMapper.toConsultPixKeyCommandList(pixConsultRepository.findByFilters(command));
    }
}
