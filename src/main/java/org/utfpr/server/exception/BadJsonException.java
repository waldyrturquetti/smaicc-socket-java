package org.utfpr.server.exception;

import java.io.Serial;

public class BadJsonException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BadJsonException() {
        super("Json não processavel.");
    }

    public BadJsonException(String msg) {
        super("Json não processavel: " + msg.replace("\n", ""));
    }
}
