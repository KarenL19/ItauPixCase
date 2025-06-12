package com.store.itaupixcase.cor.ports.out;

public interface ValidDocumentNumberOutPort {
    boolean isCpfValid(String cpf);
    boolean isCnpjValid(String cnpj);
}
