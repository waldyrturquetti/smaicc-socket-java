package org.utfpr.server.domain.repository;

import org.utfpr.server.domain.entities.User;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.infra.Database;

import java.sql.*;

public class UserRepositoryDAO {

    private final Connection connection;

    public UserRepositoryDAO() throws SQLException {
        this.connection = Database.getConnection();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            preparedStatement = connection.prepareStatement("select * from user as u where u.email like ? and u.password like ?");
            preparedStatement.setString(1, email.trim());
            preparedStatement.setString(2, password.trim());

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("email"), resultSet.getString("password"));
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }

        return user;
    }
}
