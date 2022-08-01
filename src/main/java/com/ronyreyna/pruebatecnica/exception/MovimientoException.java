package com.ronyreyna.pruebatecnica.exception;

public class MovimientoException extends Exception{

    public MovimientoException() {
        super();
    }

    public MovimientoException(String message) {
        super(message);
    }

    public MovimientoException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovimientoException(Throwable cause) {
        super(cause);
    }
}
