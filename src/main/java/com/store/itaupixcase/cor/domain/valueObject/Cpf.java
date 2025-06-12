package com.store.itaupixcase.cor.domain.valueObject;

import com.store.itaupixcase.cor.domain.KeyPix;
import com.store.itaupixcase.cor.ports.out.ValidDocumentNumberOutPort;

public class Cpf implements KeyPix {

    private final String value;

    public Cpf(String value, ValidDocumentNumberOutPort validator) {
        if (!isFormatoValido(value)) {
            throw new IllegalArgumentException("O CPF deve ter exatamente 11 dígitos numéricos..");
        }
        if (!validator.isCpfValid(value)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.value = value;
    }

    private boolean isFormatoValido(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }


    @Override
    public String getValue() {
        return value;
    }
}