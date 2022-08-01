package com.ronyreyna.pruebatecnica.exception;

public class ClienteException extends Exception{

    public ClienteException() {
        super();
    }

    public ClienteException(String message) {
        super(message);
    }

    public ClienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClienteException(Throwable cause) {
        super(cause);
    }
}
