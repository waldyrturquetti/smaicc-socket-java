package org.utfpr.server.domain.entities;

import java.util.HashMap;

public enum IncidentsTypesEnum {
    ALAGAMENTO(1),
    DESLIZAMENTO(2),
    ACIDENTE_DE_CARRO(3),
    OBSTRUCAO_DA_VIA(4),
    FISSURA_DA_VIA(5),
    PISTA_EM_OBRAS(6),
    LENTIDAO_NA_PISTA(7),
    ANIMAIS_NA_PISTA(8),
    NEVOEIRO(9),
    TROMBA_DAGUA(10);

    private final Integer value;

    private final static HashMap<Integer, IncidentsTypesEnum> map = new HashMap<Integer, IncidentsTypesEnum>();

    IncidentsTypesEnum(Integer value) {
        this.value = value;
    }

    static {
        for (IncidentsTypesEnum incidentsTypesEnum : IncidentsTypesEnum.values()) {
            map.put(incidentsTypesEnum.getValue(), incidentsTypesEnum);
        }
    }

    public Integer getValue() {
        return value;
    }

    public static IncidentsTypesEnum getEnum(Integer value) {
        return map.get(value);
    }
}
