package org.utfpr.server.domain.repository;

import org.utfpr.server.infra.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryDAO {

    private final Connection connection;

    public UserRepositoryDAO() throws SQLException {
        this.connection = Database.getConnection();
    }

    public void getUser() {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("Id") + ", " + resultSet.getString("Name"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(statement);
        }
    }
}
