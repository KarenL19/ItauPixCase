package com.store.itaupixcase.infra.adapters.out.implementsPortOut;

import com.store.itaupixcase.cor.ports.out.ValidatePixKeyOutPort;
import com.store.itaupixcase.infra.adapters.out.entity.PixEntity;
import com.store.itaupixcase.infra.adapters.out.repository.PixRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidPixKeyOutPortImpl implements ValidatePixKeyOutPort {

    public final PixRepository pixRepository;
    public ValidPixKeyOutPortImpl(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    @Override
    public Long countPixKey(Integer agencyNumber, Integer accountNumber) {
        return pixRepository.countByKeyTypeAndKeyValue(agencyNumber,accountNumber);
    }

    @Override
    public boolean existsPixKey(String pixKey) {
        Optional<PixEntity> pixEntity = pixRepository.findFirstByKeyValue(pixKey);
        if (pixEntity.isPresent()) {
            return true;
        }
        return false;
    }

}
