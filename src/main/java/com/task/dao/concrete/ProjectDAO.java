package com.task.dao.concrete;

import com.task.controller.AdminController;
import com.task.dao.AbstractDAOFactory;
import com.task.dao.ConnectionPool;
import com.task.dao.contracts.IProjectDAO;
import com.task.model.Project;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDAO implements IProjectDAO {
    private static Logger logger = Logger.getLogger(ProjectDAO.class);
    private ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public boolean hasProject(int customerID) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT NAME FROM PROJECT WHERE CUSTOMER = ?");
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.next();
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in ProjectDAO.hasProject");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return result;
    }

    @Override
    public boolean createProject(Project project) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO PROJECT (NAME, MANAGER, CUSTOMER, END_DATE) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, project.getName());
            preparedStatement.setInt(2, AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(project.getManager()));
            preparedStatement.setInt(3, AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(project.getCustomer()));
            preparedStatement.setDate(4, new java.sql.Date(project.getEndDate().getTime()));
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in ProjectDAO.createProject");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return result;
    }

    @Override
    public int getProjectIdByManager(int id) {
        Connection connection = null;
        int result = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT ID FROM PROJECT WHERE MANAGER = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result = resultSet.getInt("id");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in ProjectDAO.getProjectIdByManager");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return result;
    }

    @Override
    public int getProjectIdByCustomer(int id) {
        Connection connection = null;
        int result = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT ID FROM PROJECT WHERE CUSTOMER = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                result = resultSet.getInt("id");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in ProjectDAO.getProjectIdByCustomer");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return result;
    }

    @Override
    public boolean closeProject(int projectID) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE PROJECT SET RELEASED = 1 WHERE ID = ?\n" +
                            " AND 0 NOT IN (SELECT COMPLETE FROM SPRINT WHERE PROJECT = ?)");
            preparedStatement.setInt(1, projectID);
            preparedStatement.setInt(2, projectID);
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in ProjectDAO.closeProject");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return result;
    }
}
