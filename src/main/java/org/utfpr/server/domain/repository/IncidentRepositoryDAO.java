package org.utfpr.server.domain.repository;

import org.utfpr.server.domain.entities.Incident;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.exception.ServerErrorException;
import org.utfpr.server.infra.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class IncidentRepositoryDAO {

    private final Connection connection;

    public IncidentRepositoryDAO() throws SQLException {
        this.connection = Database.getConnection();
    }

    public void createIncident(Incident incident) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement =
                    connection.prepareStatement("insert into incident (id, user_id, date, hour, state, city, neighborhood, street, incident_type)" +
                                                    "values (null, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, incident.getId());
            preparedStatement.setString(2, incident.getDate().toString());
            preparedStatement.setString(3, incident.getHour().toString());
            preparedStatement.setString(4, incident.getState().trim());
            preparedStatement.setString(5, incident.getCity().trim());
            preparedStatement.setString(6, incident.getNeighborhood().trim());
            preparedStatement.setString(7, incident.getStreet().trim());
            preparedStatement.setString(8, incident.getIncidentsTypesEnum().toString());

            int rowsAffected  = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ServerErrorException("Erro ao cadastrar o Incidente.");
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(preparedStatement);
        }
    }
}
