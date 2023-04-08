package com.moxa.micro.core.exception;


public class MicroException extends RuntimeException {

    public MicroException(String message) {
        super(message);
    }

    public MicroException(Throwable cause) {
        super(cause);
    }

    public MicroException(String message, Throwable cause) {
        super(message, cause);
    }
}
