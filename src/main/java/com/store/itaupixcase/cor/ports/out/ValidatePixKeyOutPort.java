package com.store.itaupixcase.cor.ports.out;

public interface ValidatePixKeyOutPort {
    Long countPixKey(Integer agencyNumber, Integer accountNumber, String keyStatus);
    boolean existsPixKey(String pixKey, String activeKey);
}
