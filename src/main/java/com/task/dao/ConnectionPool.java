package com.task.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;
import java.sql.*;
import java.util.Locale;


public class ConnectionPool {

    private DataSource dataSource;

    /**
     * Retrieves a dataSource from InitialContext.
     */
    public ConnectionPool(){
        Locale.setDefault(new Locale("EN"));
        try {
            Context ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/HRDB");
        } catch (NamingException ex) {
            System.out.println("Cannot retrieve java:comp/env/jdbc/HRDB");
        }
    }

    /**
     * Retrieves a connection from connection pool.
     * @return the connection.
     */
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Cannot obtain connection");
        }
        return connection;
    }

    /**
     * Releases the connection into connection pool.
     * @param connection teh connection.
     */
    public void releaseConnection(Connection connection){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot release connection");
        }
    }

    /**
     * Closes the prepared statement.
     * @param preparedStatement the prepared statement.
     */
    public void closeStatement(PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot close preparedStatement");
        }
    }

    /**
     * Closes the result set.
     * @param resultSet the result set.
     */
    public void closeResultSet(ResultSet resultSet){
        try {
            if (resultSet != null){
                resultSet.close();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot close resultSet");
        }
    }

}
