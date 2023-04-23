package org.utfpr.server.exception;

import java.io.Serial;

public class OperationNotKnownException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public OperationNotKnownException() {
        super("Operação não conhecida.");
    }
}
