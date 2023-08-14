package com.gutotech.loteriasapi.model.exception;

public class LoteriaInvalidException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoteriaInvalidException(String message) {
	super(message);
    }
}
