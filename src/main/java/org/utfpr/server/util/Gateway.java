package org.utfpr.server.util;

import org.utfpr.server.auth.Section;
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
import org.utfpr.server.dto.common.CommonDataReturned;
import org.utfpr.server.exception.OperationNotKnownException;

import java.util.HashMap;

public class Gateway {

    public static String chooseOperation(String message) {

        HashMap<String, Object> json;
        HashMap<String, Object> returnedJson;
        Integer operation = null;

        try {
            json = Convert.convertStringToHashMap(message);
            operation = (Integer) json.get("operacao");

            switch (operation) {
                case 1 -> returnedJson = executeOperation(new CreateUser(), json, false);
                case 2 -> returnedJson = executeOperation(new Login(), json, false);
                case 3 -> returnedJson = executeOperation(new UpdateUser(), json, true);
                case 4 -> returnedJson = executeOperation(new GetIncidents(), json, false);
                case 5 -> returnedJson = executeOperation(new GetIncidentsByUser(), json, true);
                case 6 -> returnedJson = executeOperation(new DeleteIncident(), json, true);
                case 7 -> returnedJson = executeOperation(new CreateIncident(), json, true);
                case 8 -> returnedJson = executeOperation(new DeleteUser(), json, true);
                case 9 -> returnedJson = executeOperation(new Logout(), json, true);
                default -> throw new OperationNotKnownException();
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            returnedJson = Convert.convertDataToHashMap(new CommonDataReturned(operation, Status.ERROR + e.getMessage()));
        }

        assert returnedJson != null;
        return Convert.convertHashMapToString(returnedJson);
    }

    private static HashMap<String, Object> executeOperation(UseCase useCase, HashMap<String, Object> json, Boolean isNeedToBeAuthenticated) {
        if (isNeedToBeAuthenticated) {
            verifySection(json);
        }

        return useCase.executeOperation(json);
    }

    private static void verifySection(HashMap<String, Object> json) {
        Check.checkIdAndTokenFromJson(json);
        String token = (String) json.get("token");
        Integer id = (Integer) json.get("id");
        Section.verifyToken(id, token);
    }
}
