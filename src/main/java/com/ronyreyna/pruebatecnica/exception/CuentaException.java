package com.ronyreyna.pruebatecnica.exception;

public class CuentaException extends Exception{

    public CuentaException() {
        super();
    }

    public CuentaException(String message) {
        super(message);
    }

    public CuentaException(String message, Throwable cause) {
        super(message, cause);
    }

    public CuentaException(Throwable cause) {
        super(cause);
    }
}
