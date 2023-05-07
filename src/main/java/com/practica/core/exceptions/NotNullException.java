package com.practica.core.exceptions;

public class NotNullException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotNullException(String message) {
        super(message);
    }
}
