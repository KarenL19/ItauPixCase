package com.store.itaupixcase.app;

import com.store.itaupixcase.infra.adapters.in.dto.consult.FiltersDto;
import com.store.itaupixcase.infra.adapters.in.dto.consult.PixRequestConsultDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixRequestDTO;
import com.store.itaupixcase.infra.adapters.in.dto.insert.PixResponseDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixRequestUpdateDTO;
import com.store.itaupixcase.infra.adapters.in.dto.update.PixResponseUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PixService {
    PixResponseDTO registerPixKey(PixRequestDTO pixRequestDTO);
    PixResponseUpdateDTO updatePixKey(PixRequestUpdateDTO pixRequestDTO);

    List<PixRequestConsultDTO> getPixKey(FiltersDto filtersDto);
}
