package org.utfpr.server.domain.repository;

import org.utfpr.server.domain.entities.Incident;
import org.utfpr.server.domain.entities.IncidentsTypesEnum;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.exception.ServerErrorException;
import org.utfpr.server.infra.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

            preparedStatement.setInt(1, incident.getUserId());
            preparedStatement.setString(2, incident.getDate().toString());
            preparedStatement.setString(3, incident.getHour().toString());
            preparedStatement.setString(4, incident.getState().trim());
            preparedStatement.setString(5, incident.getCity().trim());
            preparedStatement.setString(6, incident.getNeighborhood().trim());
            preparedStatement.setString(7, incident.getStreet().trim());
            preparedStatement.setString(8, incident.getIncidentsTypesEnum().toString());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ServerErrorException("Erro ao cadastrar o Incidente.");
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(preparedStatement);
        }
    }

    public List<Incident> getIncidentsByDateAndStateAndCity(LocalDate localDate, String state, String city) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Incident> incidentList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("select * from incident as i " +
                                                                    "where i.date = ? and i.state = ? and i.city = ?");

            preparedStatement.setString(1, localDate.toString());
            preparedStatement.setString(2, state.trim());
            preparedStatement.setString(3, city.trim());

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                incidentList.add(
                        new Incident(resultSet.getInt("id"), resultSet.getInt("user_id"),
                                resultSet.getDate("date").toLocalDate(), resultSet.getTime("hour").toLocalTime(),
                                resultSet.getString("state"), resultSet.getString("city"),
                                resultSet.getString("neighborhood"), resultSet.getString("street"),
                                IncidentsTypesEnum.valueOf(resultSet.getString("incident_type"))
                        )
                );
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }

        return incidentList;
    }
}
