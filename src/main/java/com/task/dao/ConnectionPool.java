package com.task.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;
import java.sql.*;
import java.util.Locale;


public class ConnectionPool {

    private DataSource dataSource;

    public ConnectionPool(){
        Locale.setDefault(new Locale("EN"));
        try {
            Context ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/HRDB");
        } catch (NamingException ex) {
            System.out.println("Cannot retrieve java:comp/env/jdbc/HRDB");
        }
    }

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

    public void releaseConnection(Connection connection){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot release connection");
        }
    }

    public void closeStatement(PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        } catch (SQLException ex) {
            System.out.println("Cannot close preparedStatement");
        }
    }

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
