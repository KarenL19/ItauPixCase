package com.store.itaupixcase.cor.ports.in;

import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.cor.usecase.command.RegisterPixKeyCommand;

import java.util.List;

public interface InsertKeyPixInPort {
    Pix insertKeyPix(RegisterPixKeyCommand command);
    Pix updateKeyPix(RegisterPixKeyCommand command);
    List<ConsultPixKeyCommand> consultKeyPix(FiltersCommand command);
}
