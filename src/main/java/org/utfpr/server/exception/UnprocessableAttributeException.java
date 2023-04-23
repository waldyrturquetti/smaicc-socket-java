package org.utfpr.server.exception;

import java.io.Serial;

public class UnprocessableAttributeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;


    public UnprocessableAttributeException() {
        super("Atributo nao processavel");
    }

    public UnprocessableAttributeException(String msg) {
        super(msg);
    }
}
