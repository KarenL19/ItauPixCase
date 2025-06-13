package com.store.itaupixcase.cor.domain.validation;

import com.store.itaupixcase.cor.usecase.command.ConsultPixKeyCommand;

import java.util.List;
import java.util.NoSuchElementException;

public class ConsultValidator {

    public static List<ConsultPixKeyCommand> validateConsult(List<ConsultPixKeyCommand> commands) {
        if (commands == null || commands.isEmpty()) {
            throw new NoSuchElementException("Registro não encontrado.");
        }
            for (ConsultPixKeyCommand cmd : commands) {
                if (cmd.getInactiveTime().isEmpty() || cmd.getInactiveTime().equals("null")) {
                    cmd.setInactiveTime("");
                }
            }

        return commands;
    }
}
