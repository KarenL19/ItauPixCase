package com.store.itaupixcase.cor.ports.out;

import java.util.UUID;

public interface ValidateIdPixOutPort {
    boolean validateIdPix(UUID idPix);
    boolean validateIdActive(UUID idActive,String keyStatus);
}
