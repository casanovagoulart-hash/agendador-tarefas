package com.casanova.agendadortarefas.infrastructure.exceptions;

public class ResourseNotFoundException extends RuntimeException{

    public ResourseNotFoundException(String message) {
        super(message);
    }

    public ResourseNotFoundException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }

}
