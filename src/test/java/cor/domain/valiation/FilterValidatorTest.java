package cor.domain.valiation;

import com.store.itaupixcase.cor.domain.enuns.AccountType;
import com.store.itaupixcase.cor.domain.validation.AccountValidator;
import com.store.itaupixcase.cor.domain.validation.FilterValidator;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilterValidatorTest {

    private AccountValidator accountValidator;
    private FilterValidator filterValidator;
    private FiltersCommand filter;

    @BeforeEach
    void setUp() {
        accountValidator = mock(AccountValidator.class);
        filterValidator = new FilterValidator(accountValidator);
        filter = mock(FiltersCommand.class);
    }

    @Test
    void devePermitirFiltroSomentePorId() {
        when(filter.getId()).thenReturn(UUID.fromString("728bb8f8-0587-4b89-bc77-2299ab22b46a"));
        when(filter.getKeyType()).thenReturn(null);
        when(filter.getAgencyNumber()).thenReturn(null);
        when(filter.getAccountNumber()).thenReturn(null);
        when(filter.getAccountHolderName()).thenReturn(null);
        when(filter.getCreatedTime()).thenReturn(null);
        when(filter.getInactiveTime()).thenReturn(null);

        assertDoesNotThrow(() -> filterValidator.validateFilters(filter));
    }

    @Test
    void deveLancarExcecaoSeIdComOutroFiltro() {
        when(filter.getId()).thenReturn(UUID.fromString("728bb8f8-0587-4b89-bc77-2299ab22b46a"));
        when(filter.getKeyType()).thenReturn("CPF");

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> filterValidator.validateFilters(filter));
        assertTrue(ex.getMessage().contains("Ao consultar por ID"));
    }

    @Test
    void deveLancarExcecaoSeDuasDatasPreenchidas() {
        when(filter.getId()).thenReturn(null);
        when(filter.getCreatedTime()).thenReturn(String.valueOf(new java.util.Date()));
        when(filter.getInactiveTime()).thenReturn(String.valueOf(new java.util.Date()));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> filterValidator.validateFilters(filter));
        assertTrue(ex.getMessage().contains("Informe apenas uma das datas"));
    }

    @Test
    void deveLancarExcecaoSeApenasUmNumeroAgenciaOuConta() {
        when(filter.getId()).thenReturn(null);
        when(filter.getAgencyNumber()).thenReturn(123);
        when(filter.getAccountNumber()).thenReturn(null);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> filterValidator.validateFilters(filter));
        assertTrue(ex.getMessage().contains("agencyNumber e accountNumber"));
    }

    @Test
    void deveValidarAccountTypeQuandoPresente() {
        when(filter.getId()).thenReturn(null);
        when(filter.getAgencyNumber()).thenReturn(123);
        when(filter.getAccountNumber()).thenReturn(456);
        when(filter.getAccountType()).thenReturn("corrente");
        when(accountValidator.validateAccountType("corrente")).thenReturn(AccountType.CORRENTE);

        doNothing().when(filter).setAccountType("CORRENTE");

        assertDoesNotThrow(() -> filterValidator.validateFilters(filter));
        verify(accountValidator).validateAccountType("corrente");
        verify(filter).setAccountType("CORRENTE");
    }

    @Test
    void devePermitirFiltrosValidos() {
        when(filter.getId()).thenReturn(null);
        when(filter.getAgencyNumber()).thenReturn(123);
        when(filter.getAccountNumber()).thenReturn(456);
        when(filter.getAccountType()).thenReturn(null);
        when(filter.getCreatedTime()).thenReturn(null);
        when(filter.getInactiveTime()).thenReturn(null);

        assertDoesNotThrow(() -> filterValidator.validateFilters(filter));
    }
}