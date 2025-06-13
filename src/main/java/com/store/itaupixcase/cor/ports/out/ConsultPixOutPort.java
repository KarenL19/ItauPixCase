package com.store.itaupixcase.cor.ports.out;

import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;

import java.util.List;

public interface ConsultPixOutPort {
    List<ConsultPixKeyCommand> consultKeyPix(FiltersCommand command);
}
