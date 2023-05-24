package org.utfpr.client.util;

public class ComboBoxValues {

    public static String[] getStateValues() {
        return new String[]{
            null, "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MS", "MT", "MG", "PA",
            "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        };
    }

    public static String[] getTypeIncidents() {
        return new String[] {
            null, "Alagamento", "Deslizamento", "Acidente de carro", "Obstrução da via", "Fissura da via",
            "Pista em obras", "Lentidão na pista", "Animais na pista", "Nevoeiro", "Tromba d'água"
        };
    }
}
