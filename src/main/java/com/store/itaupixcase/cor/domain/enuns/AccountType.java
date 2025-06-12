package com.store.itaupixcase.cor.domain.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {
    CORRENTE,
    POUPANCA;

    @JsonValue
    public String toValue() {
        return this == POUPANCA ? "POUPANCA" : "CORRENTE";
    }
}