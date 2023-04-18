package org.utfpr.server.exception;

import java.io.Serial;

public class DbException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DbException(String msg) {
        super("Erro no Banco de dados:\n" + msg);
    }
}
