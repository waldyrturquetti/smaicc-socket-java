package org.utfpr.client.exception;

import java.io.Serial;

public class ProblemCommunicatingWithTheServerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ProblemCommunicatingWithTheServerException() {
        super("Problema com a comunicação com o Servidor.");
    }

    public ProblemCommunicatingWithTheServerException(String msg) {
        super(msg);
    }
}
