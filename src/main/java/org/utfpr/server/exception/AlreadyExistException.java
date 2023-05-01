package org.utfpr.server.exception;

import java.io.Serial;

public class AlreadyExistException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AlreadyExistException() {
        super("Já existente.");
    }

    public AlreadyExistException(String msg) {
        super(msg);
    }
}
