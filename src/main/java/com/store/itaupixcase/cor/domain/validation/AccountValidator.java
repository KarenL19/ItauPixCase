package com.store.itaupixcase.cor.domain.validation;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.ports.out.ValidateIdPixOutPort;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class AccountValidator {

    public final ValidateIdPixOutPort validateIdPixOutPort;

    public AccountValidator(ValidateIdPixOutPort validateIdPixOutPort) {
        this.validateIdPixOutPort = validateIdPixOutPort;
    }

    public void validateAccountNumber(Integer accountNumber) {
        if (accountNumber == null) {
            throw new IllegalArgumentException("Número de conta não pode ser vazio");        }
        if (accountNumber.toString().length() > 8) {
        throw new IllegalArgumentException("Número de conta não pode exceder 8 digitos");        }
    }

    public AccountType validateAccountType(String value) {
        if (value.length() > 10) {
            throw new IllegalArgumentException("Quantidade de caracteres não deve exceder 10 caracteres no campo `accountType`.");
        }
        switch (value.trim().toLowerCase()) {
            case "corrente":
                return AccountType.CORRENTE;
            case "poupanca":
            case "poupança":
                return AccountType.POUPANCA;
            default:
                throw new IllegalArgumentException("Tipo de conta inválido: '" + value + "'. Os valores aceitos são: 'corrente', 'poupanca' ou 'poupança'.");        }
    }
    public void validateAgencyNumber(Integer agencyNumber) {
        if (agencyNumber == null) {
            throw new IllegalArgumentException("Número da agência não pode ser vazio");
        }
        if (agencyNumber.toString().length()  > 4) {
            throw new IllegalArgumentException("Número da agência não pode exceder 4 dígitos");
        }
    }

    public void validateHolderName(String holderName) {
        if (holderName == null || holderName.isEmpty()) {
            throw new IllegalArgumentException("Nome do titular não pode ser vazio");
        }
        if (holderName.length() > 30) {
            throw new IllegalArgumentException("Nome do titular não pode exceder 30 caracteres");
        }
    }
    public void validateHolderSurname(String holderSurname) {
        if (holderSurname.length() > 45) {
            throw new IllegalArgumentException("Sobrenome do titular não pode exceder 45 caracteres");
        }
    }

    public void validateIdPix(UUID idPix) {
        if (!validateIdPixOutPort.validateIdPix(idPix)){
            throw new NoSuchElementException("ID não existe " + idPix);
        }
    }

    public void validateIdActive(UUID idActive) {
        if (!validateIdPixOutPort.validateIdActive(idActive)) {
            throw new NoSuchElementException("ID ativo não encontrado: " + idActive);
        }
    }
    }
