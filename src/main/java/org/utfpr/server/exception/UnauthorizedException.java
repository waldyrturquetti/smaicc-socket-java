package org.utfpr.server.exception;

import java.io.Serial;

public class UnauthorizedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        super("Usuario nao Autorizado.");
    }
}
