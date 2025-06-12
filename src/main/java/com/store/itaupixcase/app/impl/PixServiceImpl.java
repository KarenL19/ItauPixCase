package com.store.itaupixcase.app.impl;

import com.store.itaupixcase.app.PixService;
import com.store.itaupixcase.cor.ports.in.InsertKeyPixInPort;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.cor.usecase.command.RegisterKeyPixCommand;
import com.store.itaupixcase.infra.adapters.in.dto.consult.FiltersDto;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixRequestDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixResponseDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixRequestUpdateDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import com.store.itaupixcase.infra.adapters.out.mapper.PixMapper;
import com.store.itaupixcase.infra.adapters.out.repository.PixConsultRepository;
import com.store.itaupixcase.infra.adapters.out.repository.PixRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PixServiceImpl implements PixService {

    private final InsertKeyPixInPort insertKeyPixInPort;
    private final PixMapper pixMapper;
    private  final PixRepository pixRepository;
    private final EntityManager entityManager;
    private final PixConsultRepository pixConsultRepository;

    public PixServiceImpl(InsertKeyPixInPort insertKeyPixInPort, PixMapper pixMapper, PixRepository pixRepository, EntityManager entityManager, PixConsultRepository pixConsultRepository) {
        this.insertKeyPixInPort = insertKeyPixInPort;
        this.pixMapper = pixMapper;
        this.pixRepository = pixRepository;
        this.entityManager = entityManager;
        this.pixConsultRepository = pixConsultRepository;
    }

    public PixResponseDTO registerPixKey(PixRequestDTO pixRequestDTO) {
        RegisterKeyPixCommand command = new RegisterKeyPixCommand(
                pixRequestDTO.getKeyType(),
                pixRequestDTO.getKeyValue(),
                pixRequestDTO.getAccountType(),
                pixRequestDTO.getAccountNumber(),
                pixRequestDTO.getAgencyNumber(),
                pixRequestDTO.getAccountHolderName(),
                pixRequestDTO.getAccountHolderSurname(),
                pixRequestDTO.getClientType()
        );
        PixEntity entity = pixMapper.toEntityInsert(insertKeyPixInPort.insertKeyPix(command));
        try {
            pixRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar: " + e.getMessage(), e);
        }
        return PixResponseDTO.builder().id(entity.getId()).build();
    }


    @Override
    public PixResponseUpdateDTO updatePixKey(PixRequestUpdateDTO pixRequestDTO) {
        RegisterKeyPixCommand command = new RegisterKeyPixCommand(
                pixRequestDTO.getId(),
                pixRequestDTO.getAccountType(),
                pixRequestDTO.getAccountNumber(),
                pixRequestDTO.getAgencyNumber(),
                pixRequestDTO.getAccountHolderName(),
                pixRequestDTO.getAccountHolderSurname()
        );

        PixEntity entity = pixMapper.toEntityUpdate(insertKeyPixInPort.updateKeyPix(command));
        try {
            pixRepository.updatePixFields(entity.getId(),
                    entity.getAccountType(),
                    entity.getAccountNumber(),
                    entity.getAgencyNumber(),
                    entity.getHolderName(),
                    entity.getHolderSurname());
            entityManager.clear();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar: " + e.getMessage(), e);
        }
        return pixMapper.toResponseUpdateDTO(pixRepository.findById(entity.getId()).get());
    }

    @Override
    public List<PixRequestConsultDTO> getPixKey(FiltersDto filtersDto) {
        FiltersCommand command = new FiltersCommand(
                filtersDto.getId(),
                filtersDto.getKeyType(),
                filtersDto.getAccountNumber(),
                filtersDto.getKeyValue(),
                filtersDto.getAgencyNumber(),
                filtersDto.getAccountHolderName(),
                filtersDto.getCreatedTime(),
                filtersDto.getInactiveTime(),
                filtersDto.getAccountType()
        );
         command = insertKeyPixInPort.consultKeyPix(command);
        try {
          return pixMapper.toResponseConsultDTOList(pixConsultRepository.findByFilters(command));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao consultar: " + e.getMessage(), e);
        }

    }

}
