package org.utfpr.client.exception;

import java.io.Serial;

public class UnauthenticatedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnauthenticatedException() {
        super("Não Autenticado.");
    }

    public UnauthenticatedException(String msg) {
        super(msg);
    }
}
