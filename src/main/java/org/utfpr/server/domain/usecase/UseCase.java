package org.utfpr.server.domain.usecase;

import java.util.HashMap;

public interface UseCase {

    HashMap<String, Object> executeOperation(HashMap<String, Object> json);
}
