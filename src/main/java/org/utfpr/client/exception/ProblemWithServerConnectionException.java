package org.utfpr.client.exception;

import java.io.Serial;

public class ProblemWithServerConnectionException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProblemWithServerConnectionException() {
        super("Problema para se conectar com o Servidor.");
    }

    public ProblemWithServerConnectionException(String msg) {
        super(msg);
    }
}
