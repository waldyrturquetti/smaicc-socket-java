package org.utfpr.server.domain.repository;

import org.utfpr.server.exception.NotFoundException;
import org.utfpr.server.infra.Database;

import java.sql.*;

public class UserRepositoryDAO {

    private final Connection connection;

    public UserRepositoryDAO() throws SQLException {
        this.connection = Database.getConnection();
    }

    public void getUserByEmailAndPassword(String email, String password) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("select * from user as u where u.email like ? and u.password like ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            System.out.println();
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
            } else {
                throw new NotFoundException("Usuario não encontrado.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }
    }
}
