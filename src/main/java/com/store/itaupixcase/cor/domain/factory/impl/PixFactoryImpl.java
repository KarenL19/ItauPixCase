package com.store.itaupixcase.cor.domain.factory.impl;

import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import com.store.itaupixcase.cor.domain.factory.PixFactory;
import com.store.itaupixcase.cor.domain.valueObject.*;
import com.store.itaupixcase.cor.ports.out.ValidDocumentNumberOutPort;
import org.springframework.stereotype.Component;

@Component
public class PixFactoryImpl implements PixFactory {
    private final ValidDocumentNumberOutPort validator;


    public PixFactoryImpl(ValidDocumentNumberOutPort validator) {
        this.validator = validator;
    }

    @Override
    public KeyPix create(String keyValue, KeyType keyType) {
        return switch (keyType) {
            case CPF -> new Cpf(keyValue, validator);
            case CNPJ -> new Cnpj(keyValue, validator);
            case EMAIL -> new Email(keyValue);
        };
    }
}
