package com.store.itaupixcase.infra.adapters.out.implementsPortOut;

import com.store.itaupixcase.cor.ports.out.ValidateIdPixOutPort;
import com.store.itaupixcase.infra.adapters.out.repository.PixRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidateIdPixOutPortImpl implements ValidateIdPixOutPort {
    private final PixRepository pixRepository;

    public ValidateIdPixOutPortImpl(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    @Override
    public boolean validateIdPix(UUID idPix) {
        if (pixRepository.existsById(idPix)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validateIdActive(UUID idActive,String keyStatus) {
    if (pixRepository.findByIdAndKeyStatusActive(idActive,keyStatus).isPresent()) {
            return true;
        }
        return false;
    }
}
