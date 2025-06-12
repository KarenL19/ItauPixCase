package com.store.itaupixcase.cor.ports.in;

import com.store.itaupixcase.cor.domain.Pix;
import com.store.itaupixcase.cor.usecase.command.FiltersCommand;
import com.store.itaupixcase.cor.usecase.command.RegisterKeyPixCommand;

public interface InsertKeyPixInPort {
    Pix insertKeyPix(RegisterKeyPixCommand command);
    Pix updateKeyPix(RegisterKeyPixCommand command);

    FiltersCommand consultKeyPix(FiltersCommand command);
}
