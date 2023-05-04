package org.utfpr.common.dto.common;

import org.utfpr.common.dto.DataServerToClient;

public class CommonDataServerToClient extends DataServerToClient {

    public CommonDataServerToClient() {
        super();
    }

    public CommonDataServerToClient(Integer operation, String status) {
        super(operation, status);
    }

}
