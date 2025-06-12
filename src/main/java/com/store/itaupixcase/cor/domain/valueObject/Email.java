package com.store.itaupixcase.cor.domain.valueObject;

import com.store.itaupixcase.cor.domain.KeyPix;

public class Email implements KeyPix {
    private final String value;

    public Email(String value) {
        if (value == null || !value.contains("@")) {
            throw new IllegalArgumentException("Formato de email inválido. Deve conter '@'.");
        }
        if (value.length() > 77) {
            throw new IllegalArgumentException("O email deve ter no máximo 77 caracteres.");
        }
        this.value = value;
    }


    @Override
    public String getValue() {
        return value;
    }
}
