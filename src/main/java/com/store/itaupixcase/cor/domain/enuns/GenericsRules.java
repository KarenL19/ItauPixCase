package com.store.itaupixcase.cor.domain.enuns;

public enum GenericsRules {
    ACTIVE("A");

    private String value;

     GenericsRules(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
