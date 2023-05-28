package org.utfpr.client.exception;

import java.io.Serial;

public class EmptyFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmptyFieldException() {
        super("Os Campos não podem estar vazios para concluir a operação.");
    }

    public EmptyFieldException(String msg) {
        super(msg);
    }
}
