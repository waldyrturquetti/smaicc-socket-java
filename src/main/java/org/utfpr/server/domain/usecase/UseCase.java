package org.utfpr.server.domain.usecase;

import java.util.HashMap;

public interface UseCase<T> {

    HashMap<String, Object> executeOperation(HashMap<String, Object> json);

    T convertHashMapToData();

    HashMap<String, Object> convertDataToHashMap(T data);
}
