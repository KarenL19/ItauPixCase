package com.store.itaupixcase.cor.domain.validation;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import org.springframework.stereotype.Component;

@Component
public class FilterValidator {
    private final AccountValidator accountValidator;

    public FilterValidator(AccountValidator accountValidator) {
        this.accountValidator = accountValidator;
    }

    public void validateFilters(FiltersCommand filter) {


        if (filter.getId() != null) {
            boolean hasOtherFilters =
                    filter.getKeyType() != null ||
                            filter.getAgencyNumber() != null ||
                            filter.getAccountNumber() != null ||
                            filter.getAccountHolderName() != null ||
                            filter.getCreatedTime() != null ||
                            filter.getInactiveTime() != null;

            if (hasOtherFilters) {
                throw new IllegalArgumentException("Ao consultar por ID, nenhum outro filtro é permitido.");
            }
        }

        if (filter.getCreatedTime() != null && filter.getInactiveTime() != null) {
            throw new IllegalArgumentException("Informe apenas uma das datas: inclusão ou inativação.");
        }

        if ((filter.getAgencyNumber() == null) ^ (filter.getAccountNumber() == null)) {
            throw new IllegalArgumentException("Os campos agencyNumber e accountNumber devem ser preenchidos juntos.");
        }

        if (filter.getAccountType() != null) {
           AccountType accountType = accountValidator.validateAccountType(filter.getAccountType());
           filter.setAccountType(accountType.name());
        }


    }

}
