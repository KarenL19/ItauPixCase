package com.store.itaupixcase.cor.ports.out;

public interface ValidatePixKeyOutPort {
    Long countPixKey(Integer agencyNumber, Integer accountNumber);
    boolean existsPixKey(String pixKey);
}
