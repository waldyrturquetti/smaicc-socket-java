package org.utfpr.client.util;

import org.utfpr.client.exception.InvalidFieldException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ClientCheck {

    public static void checkName(String name) {
        if (name.length() < 3 || name.length() > 50) {
            throw new InvalidFieldException("Formato do nome é invalido.");
        }
    }

    /**
     *  In the project specifications it was defined that the email would be as follows:
     *  Email: minimum 3 characters before and after the @.
     *  Required: E-mail will have a maximum length of 50 characters before @ and 10 after (domain).
     **/
    public static void checkEmail(String email) {
        email = email.trim();

        if(email.isBlank()) {
            throw new InvalidFieldException("O campo Email tem que estar preenchido.");
        }

        if (!email.matches("([a-zA-Z0-9]*[~_$&+,:;=?#|'<>.^*()%!-]*){3,50}@([a-zA-Z0-9]*[~_$&+,:;=?#|'<>.^*()%!-]*){3,10}")) {
            throw new InvalidFieldException("Formato do email invalido.");
        }
    }

    public static void checkPassword(String password) {
        if(password.isBlank()) {
            throw new InvalidFieldException("O campo Senha tem que estar preenchido.");
        }

        if (password.length() < 5 || password.length() > 10 || !password.matches("[a-zA-Z0-9]*")) {
            throw new InvalidFieldException("Formato da senha é invalido.");
        }
    }

    public static void checkDate(String date) {
        if(date.isBlank()) {
            throw new InvalidFieldException("O campo Data tem que estar preenchido.");
        }

        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            throw new InvalidFieldException("Formato da data está incorreto.");
        }
    }

    public static void checkHour(String hour) {
        if(hour.isBlank()) {
            throw new InvalidFieldException("O campo Hora tem que estar preenchido.");
        }

        try {
            LocalTime.parse(hour, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            throw new InvalidFieldException("Formato da Hora está incorreto.");
        }
    }

    public static void checkState(Object state) {
        if (state == null) {
            throw new InvalidFieldException("Selecione o Estado");
        }

        String stateString = (String) state;

        if (!stateString.matches("([A-Z]*){2}")) {
            throw new InvalidFieldException("Formato do Estado é inválido.");
        }
    }

    public static void checkCity(String city) {
        if(city.isBlank()) {
            throw new InvalidFieldException("O campo cidade tem que estar preenchido.");
        }

        if (!city.matches("([A-Z0-9 ]*){1,50}")) {
            throw new InvalidFieldException("Formato do Cidade é inválido.");
        }
    }

    public static void checkNeighborhood(String neighborhood) {
        if(neighborhood.isBlank()) {
            throw new InvalidFieldException("O campo Bairro tem que estar preenchido.");
        }

        if (!neighborhood.matches("([A-Z0-9 ]*){1,50}")) {
            throw new InvalidFieldException("Formato da Rua é inválido.");
        }
    }

    public static void checkStreet(String street) {
        if(street.isBlank()) {
            throw new InvalidFieldException("O campo Rua tem que estar preenchido.");
        }

        if (!street.matches("([A-Z0-9 ]*){1,50}")) {
            throw new InvalidFieldException("Formato da Rua é inválido.");
        }
    }

    public static void checkIncidentType(String incidentTypeValue) {
        if (incidentTypeValue == null) {
            throw new InvalidFieldException("Selecione o tipo de incidente.");
        }

        if (!Arrays.asList(ComboBoxValues.getTypeIncidents()).contains(incidentTypeValue)){
            throw new InvalidFieldException("Tipo de incidente é inválido.");
        }
    }

    public static void checkIncidentChoose(Integer incidentId) {
        if (incidentId == null) {
            throw new InvalidFieldException("Selecione o Identificador do Incidente para poder Deletar o mesmo.");
        }
    }
}
