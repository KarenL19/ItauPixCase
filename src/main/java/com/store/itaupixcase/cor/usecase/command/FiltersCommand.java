package com.store.itaupixcase.cor.usecase.command;

import java.util.UUID;

public class FiltersCommand {
    private UUID id;
    private String keyType;
    private String keyValue;
    private String accountType;
    private Integer accountNumber;
    private Integer agencyNumber;
    private String accountHolderName;
    private String createdTime;
    private String inactiveTime;

    public FiltersCommand(UUID id, String keyType, Integer accountNumber, String keyValue, Integer agencyNumber, String accountHolderName, String createdTime, String inactiveTime, String accountType) {
        this.id = id;
        this.keyType = keyType;
        this.accountNumber = accountNumber;
        this.keyValue = keyValue;
        this.agencyNumber = agencyNumber;
        this.accountHolderName = accountHolderName;
        this.createdTime = createdTime;
        this.inactiveTime = inactiveTime;
        this.accountType = accountType;
    }
    public  FiltersCommand() {}

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(Integer agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(String inactiveTime) {
        this.inactiveTime = inactiveTime;
    }
}
