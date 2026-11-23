package com.casanova.agendadortarefas.infrastructure.exceptions;

import javax.naming.AuthenticationException;

public class UnauthorizedExceptiom extends AuthenticationException {

    public UnauthorizedExceptiom(String message) {
        super(message);
    }

    public UnauthorizedExceptiom(String message, Throwable throwable) {
        super(message);
    }


}
