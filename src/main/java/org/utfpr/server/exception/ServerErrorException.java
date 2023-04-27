package org.utfpr.server.exception;

import java.io.Serial;

public class ServerErrorException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ServerErrorException() {
        super("Erro no Servidor.");
    }

    public ServerErrorException(String msg) {
        super(msg);
    }
}
