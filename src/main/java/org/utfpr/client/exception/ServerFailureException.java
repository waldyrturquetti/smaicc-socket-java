package org.utfpr.client.exception;

import java.io.Serial;

public class ServerFailureException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ServerFailureException() {
        super("Aconteceu algo de errado.");
    }

    public ServerFailureException(String msg) {
        super(msg);
    }
}
