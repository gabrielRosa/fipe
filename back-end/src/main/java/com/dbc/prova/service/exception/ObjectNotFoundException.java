package com.dbc.prova.service.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5305415611680118380L;

    public ObjectNotFoundException(String field, String value) {
        super(String.format("Object not found! Field: %s Value: %s", field, value));
    }

}
