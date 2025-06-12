package com.store.itaupixcase.infra.adapters.out.implementsPortOut;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.store.itaupixcase.cor.ports.out.ValidDocumentNumberOutPort;
import org.springframework.stereotype.Component;

@Component
public class ValidDocumentNumberImpl implements ValidDocumentNumberOutPort {
    private final CPFValidator cpfValidator;
    private final CNPJValidator cnpjValidator;

    public ValidDocumentNumberImpl() {
        this.cpfValidator = new CPFValidator();
        this.cnpjValidator = new CNPJValidator();
    }

    @Override
    public boolean isCpfValid(String cpf) {
        try {
            cpfValidator.assertValid(cpf);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }

    @Override
    public boolean isCnpjValid(String cnpj) {
        try {
            cnpjValidator.assertValid(cnpj);
            return true;
        } catch (InvalidStateException e) {
            return false;
        }
    }
}
