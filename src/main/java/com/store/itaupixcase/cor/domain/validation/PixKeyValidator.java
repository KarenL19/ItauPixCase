package com.store.itaupixcase.cor.domain.validation;

import com.store.itaupixcase.cor.domain.enuns.ClientType;
import com.store.itaupixcase.cor.domain.enuns.GenericsRules;
import com.store.itaupixcase.cor.domain.enuns.KeyType;
import com.store.itaupixcase.cor.ports.out.ValidatePixKeyOutPort;
import org.springframework.stereotype.Component;

@Component
public class PixKeyValidator {
    public final ValidatePixKeyOutPort validatePixKeyOutPort;

    public PixKeyValidator(ValidatePixKeyOutPort validatePixKeyOutPort) {
        this.validatePixKeyOutPort = validatePixKeyOutPort;
    }

    public ClientType validateType(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Tipo de cliente não pode ser nulo");
        }
        switch (value.trim().toLowerCase()) {
            case "pf":
                return ClientType.PF;
            case "pj":
                return ClientType.PJ;
            default:
                throw new IllegalArgumentException("Tipo de cliente inválido: '" + value + "'. Os valores aceitos são: 'PF' ou 'PJ'.");
        }
    }

    public void validateKeysByCLient(Integer agencyNumber, Integer accountNumber, String clientType, String keyStatus) {
        if (agencyNumber == null || agencyNumber <= 0 || accountNumber == null || accountNumber <= 0) {
            throw new IllegalArgumentException("Agencia e Conta não podem ser nulas");
        }
        long count = validatePixKeyOutPort.countPixKey(agencyNumber, accountNumber, keyStatus);
        switch (clientType) {
            case "PF":
                if (count >= 5) {
                    throw new IllegalArgumentException("Número máximo de chaves Pix para pessoa física excedido. Limite: 5");
                }
                break;
            case "PJ":
                if (count >= 20) {
                    throw new IllegalArgumentException("Número máximo de chaves Pix para pessoa jurídica excedido. Limite: 20");
                }
                break;
       }
    }

    public void existsPixKey(String pixKey, String activeKey) {
        if (pixKey == null || pixKey.isEmpty()) {
            throw new IllegalArgumentException("Chave Pix não pode ser nula ou vazia");
        }
        if (validatePixKeyOutPort.existsPixKey(pixKey, activeKey)) {
            throw new IllegalArgumentException("Chave Pix já existe: " + pixKey);
        }
    }
    public void validateTypePerson(String typePerson, String keyType) {
        if (typePerson == null || typePerson.isEmpty()) {
            throw new IllegalArgumentException("Tipo de pessoa não pode ser vazio");
        }
        if (keyType.equals(KeyType.CPF.name())) {

            if (!typePerson.equals(ClientType.PF.name())) {
                throw new IllegalArgumentException("Tipo de pessoa inválido para CPF. Deve ser PF (Pessoa Física).");
            }
        }
        if (keyType.equals(KeyType.CNPJ.name())) {
            if (!typePerson.equals(ClientType.PJ.name())) {
                throw new IllegalArgumentException("Tipo de pessoa inválido para CNPJ. Deve ser PJ (Pessoa Jurídica).");
            }
        }
    }
}