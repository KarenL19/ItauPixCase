package com.store.itaupixcase.cor.usecase.command;

import java.util.UUID;

public class RegisterPixKeyCommand {
    private UUID id;
    private String keyType;
    private String keyValue;
    private String accountType;
    private Integer accountNumber;
    private Integer agencyNumber;
    private String accountHolderName;
    private String accountHolderSurname;
    private String clientType;

    public RegisterPixKeyCommand(String keyType, String keyValue, String accountType, Integer accountNumber, Integer agencyNumber, String accountHolderName, String accountHolderSurname, String clientType) {
        this.keyType = keyType;
        this.keyValue = keyValue;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accountHolderSurname;
        this.clientType = clientType;
    }

    public RegisterPixKeyCommand(UUID id, String accountType, Integer accountNumber, Integer agencyNumber, String accountHolderName, String accountHolderSurname) {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accountHolderSurname;

    }

    public String getClientType() {
        return clientType;
    }

    public String getKeyType() {
        return keyType;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public String getAccountType() {
        return accountType;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Integer getAgencyNumber() {
        return agencyNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountHolderSurname() {
        return accountHolderSurname;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
