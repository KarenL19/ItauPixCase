package com.store.itaupixcase.cor.domain.factory;

import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.domain.enuns.KeyType;

public interface PixFactory {
    KeyPix create(String keyValue, KeyType keyType);
}
