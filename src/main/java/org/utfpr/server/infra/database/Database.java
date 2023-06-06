package org.utfpr.server.infra.database;

import org.utfpr.server.exception.DbException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {
    private static Connection conn = null;

    public static Connection getConnection() throws SQLException, DbException {
        if (conn == null || conn.isClosed()) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() throws DbException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() throws DbException {
        try (FileInputStream fs = new FileInputStream("src\\main\\resources\\db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new DbException(e.getMessage());
        }
    }

    public static void closeStatement(Statement st) throws DbException {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) throws DbException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new DbException(e.getMessage());
            }
        }
    }
}
