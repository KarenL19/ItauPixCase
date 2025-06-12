package com.store.itaupixcase.cor.domain;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.domain.enuns.ClientType;
import com.store.itaupixcase.cor.domain.enuns.KeyType;

import java.time.LocalDateTime;
import java.util.UUID;

public class Pix {
    private UUID id;
    private KeyType keyType;
    private KeyPix keyPix;
    private String keyStatus;
    private AccountType accountType;
    private Integer agencyNumber;
    private Integer accountNumber;
    private String accountHolderName;
    private String accountHolderSurname;
    private ClientType clientType;
    private LocalDateTime createdTime;
    private LocalDateTime inactiveTime;

    public Pix(KeyType keyType, KeyPix keyPix, AccountType accountType, Integer agencyNumber, Integer accountNumber, String accountHolderName, String accountHolderSurname, ClientType clientType) {
        this.id = UUID.randomUUID();
        this.keyType = keyType;
        this.keyPix = keyPix;
        this.accountType = accountType;
        this.agencyNumber = agencyNumber;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accountHolderSurname;
        this.createdTime = LocalDateTime.now();
        this.clientType = clientType;
        this.keyStatus = "A";
    }

    public Pix(UUID id, AccountType accountType, Integer accountNumber, Integer agencyNumber, String accountHolderName, String accountHolderSurname) {
        this.id = id;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.agencyNumber = agencyNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accountHolderSurname;
    }


    public String getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(String keyStatus) {
        this.keyStatus = keyStatus;
    }

    public LocalDateTime getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(LocalDateTime inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    public KeyPix getKeyPix() {
        return keyPix;
    }

    public void setKeyPix(KeyPix keyPix) {
        this.keyPix = keyPix;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(Integer agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountHolderSurname() {
        return accountHolderSurname;
    }

    public void setAccountHolderSurname(String accountHolderSurname) {
        this.accountHolderSurname = accountHolderSurname;
    }
}
