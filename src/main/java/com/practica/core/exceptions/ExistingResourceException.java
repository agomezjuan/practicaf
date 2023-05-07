package com.practica.core.exceptions;

public class ExistingResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistingResourceException(String message) {
        super(message);
    }
}
