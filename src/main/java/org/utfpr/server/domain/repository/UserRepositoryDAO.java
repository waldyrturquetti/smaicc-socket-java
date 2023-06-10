package org.utfpr.server.domain.repository;

import org.utfpr.server.domain.entities.User;
import org.utfpr.server.exception.DbException;
import org.utfpr.server.exception.ServerErrorException;
import org.utfpr.server.infra.database.Database;

import java.sql.*;

public class UserRepositoryDAO {

    private final Connection connection;

    public UserRepositoryDAO() throws SQLException {
        this.connection = Database.getConnection();
    }

    public void createUser(User user) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("insert into user (id, name, email, password)" +
                                                                "values (null, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName().trim());
            preparedStatement.setString(2, user.getEmail().trim());
            preparedStatement.setString(3, user.getPassword().trim());

            int rowsAffected  = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ServerErrorException("Erro ao cadastrar o Usuario.");
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(preparedStatement);
        }
    }

    public void updateUser(User user) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("update user " +
                                                                "set name = ?, email = ?, password = ?" +
                                                                "where id = ?");

            preparedStatement.setString(1, user.getName().trim());
            preparedStatement.setString(2, user.getEmail().trim());
            preparedStatement.setString(3, user.getPassword().trim());
            preparedStatement.setInt(4, user.getId());

            int rowsAffected  = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ServerErrorException("Erro ao cadastrar o Usuario.");
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(preparedStatement);
        }
    }

    public void deleteUser(Integer userId) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("delete from user as u where u.id = ?");

            preparedStatement.setInt(1, userId);

            int rowsAffected  = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new ServerErrorException("Erro ao deletar o Usuario.");
            }
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeStatement(preparedStatement);
        }
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

    public Boolean existsUserByEmail(String email) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("select * from user as u where u.email like ?");
            preparedStatement.setString(1, email.trim());
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }
    }

    public Boolean existsUserByThisEmailAndDiffId(String email, Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("select * from user as u where u.email like ? and u.id != ?");
            preparedStatement.setString(1, email.trim());
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }
    }

    public Boolean existsUserById(Integer id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("select * from user as u where u.id = ?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        } finally {
            Database.closeResultSet(resultSet);
            Database.closeStatement(preparedStatement);
        }
    }
}
