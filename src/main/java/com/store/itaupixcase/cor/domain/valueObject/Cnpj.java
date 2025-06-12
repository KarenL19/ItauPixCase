package com.store.itaupixcase.cor.domain.valueObject;

import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.ports.out.ValidDocumentNumberOutPort;

public class Cnpj implements KeyPix {
    private final String value;

    public Cnpj(String value, ValidDocumentNumberOutPort validator) {
        if (!isFormatValid(value)) {
            throw new IllegalArgumentException("O CNPJ deve ter exatamente 14 dígitos numéricos..");
        }
        if (!validator.isCnpjValid(value)) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
        this.value = value;
    }

    private boolean isFormatValid(String cnpj) {
        return cnpj != null && cnpj.matches("\\d{14}");
    }



    @Override
    public String getValue() {
        return value;
    }
}
