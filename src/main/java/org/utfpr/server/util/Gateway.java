package org.utfpr.server.util;

import org.utfpr.server.domain.usecase.UseCase;
import org.utfpr.server.domain.usecase.auth.Login;
import org.utfpr.server.domain.usecase.auth.Logout;
import org.utfpr.server.domain.usecase.incident.CreateIncident;
import org.utfpr.server.domain.usecase.incident.DeleteIncident;
import org.utfpr.server.domain.usecase.incident.GetIncidents;
import org.utfpr.server.domain.usecase.incident.GetIncidentsByUser;
import org.utfpr.server.domain.usecase.user.CreateUser;
import org.utfpr.server.domain.usecase.user.DeleteUser;
import org.utfpr.server.domain.usecase.user.UpdateUser;

import java.util.HashMap;

public class Gateway {

    public static String chooseOperation(String message) {

        try {
            HashMap<String, Object> json = Converts.convertStringToHashMap(message);
            Integer operation = (Integer) json.get("operacao");

            switch (operation) {
                case 1 -> executeOperation(new CreateUser(), json, false);
                case 2 -> executeOperation(new Login(), json, false);
                case 3 -> executeOperation(new UpdateUser(), json, true);
                case 4 -> executeOperation(new GetIncidents(), json, false);
                case 5 -> executeOperation(new GetIncidentsByUser(), json, true);
                case 6 -> executeOperation(new DeleteIncident(), json, true);
                case 7 -> executeOperation(new CreateIncident(), json, true);
                case 8 -> executeOperation(new DeleteUser(), json, true);
                case 9 -> executeOperation(new Logout(), json, true);
            }

            return "";
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "";
        }
    }

    private static void executeOperation(UseCase useCase, HashMap<String, Object> json, Boolean isNeedToBeAuthenticated) {
        if (isNeedToBeAuthenticated) {
            return;
        }
        useCase.executeOperation(json);
    }
}
