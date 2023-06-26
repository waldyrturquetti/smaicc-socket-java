package org.utfpr.client.exception;

import java.io.Serial;

public class InvalidFieldException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidFieldException() {
        super("Os Campos não podem estar vazios ou não podem estar inválidos para concluir a operação.");
    }

    public InvalidFieldException(String msg) {
        super(msg);
    }
}
