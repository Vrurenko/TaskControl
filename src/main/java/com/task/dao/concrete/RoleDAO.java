package com.task.dao.concrete;

import com.task.dao.ConnectionPool;
import com.task.dao.contracts.IRoleDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class RoleDAO implements IRoleDAO {
    private static Logger logger = Logger.getLogger(RoleDAO.class);
    ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public ArrayList<String> getRolesList() {
        ArrayList<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT POSITION FROM ROLE");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("position"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in RoleDAO.getRolesList");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllowedRolesList() {
        ArrayList<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT POSITION FROM ROLE WHERE ALLOWED = 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("position"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in RoleDAO.getAllowedRolesList");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public int getIdByRole(String role) {
        Connection connection = null;
        int id = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID FROM ROLE WHERE POSITION = ?");
            preparedStatement.setString(1, role);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in RoleDAO.getIdByRole");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return id;
    }

    @Override
    public String getRoleById(int id) {
        Connection connection = null;
        String role = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT POSITION FROM ROLE WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                role = resultSet.getString("position");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in RoleDAO.getRoleById");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return role;
    }
}
