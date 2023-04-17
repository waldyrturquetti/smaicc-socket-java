package org.utfpr.server.dto.common;

import org.utfpr.server.dto.DataReturned;

public class CommonDataReturned extends DataReturned {

    public CommonDataReturned() {
        super();
    }

    public CommonDataReturned(Integer operation, String status) {
        super(operation, status);
    }

}
