package com.task.dao.concrete;

import com.task.dao.ConnectionPool;
import com.task.dao.contracts.ISprintDAO;
import com.task.model.Sprint;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SprintDAO implements ISprintDAO {
    private static Logger logger = Logger.getLogger(SprintDAO.class);
    private ConnectionPool connectionPool = new ConnectionPool();

    public ArrayList<Sprint> getSprintList(int projectID) {
        ArrayList<Sprint> list = new ArrayList<Sprint>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM SPRINT WHERE PROJECT = ?");
            preparedStatement.setInt(1, projectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sprint sprint = new Sprint();
                sprint.setId(resultSet.getInt("id"));
                sprint.setName(resultSet.getString("name"));
                sprint.setStartDate(resultSet.getDate("start_date"));
                sprint.setEndDate(resultSet.getDate("end_date"));
                sprint.setComplete(resultSet.getBoolean("complete"));
                list.add(sprint);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in SprintDAO.getSprintList");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public String getSprintNameByID(int id) {
        Connection connection = null;
        String name = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT NAME FROM SPRINT WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in SprintDAO.getSprintNameByID");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return name;
    }

    @Override
    public boolean createSprint(Sprint sprint, int projectID) {
        Connection connection = null;
        Boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO SPRINT (PROJECT, NAME, END_DATE) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, projectID);
            preparedStatement.setString(2, sprint.getName());
            preparedStatement.setDate(3, new java.sql.Date(sprint.getEndDate().getTime()));
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in SprintDAO.createSprint");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean closeSprint(int sprintID) {
        Connection connection = null;
        Boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE SPRINT SET COMPLETE = 1, END_DATE = sysdate WHERE ID = ? AND 0 NOT IN \n" +
                            "(SELECT COMPLETE FROM TASK WHERE SPRINT = ?)");
            preparedStatement.setInt(1, sprintID);
            preparedStatement.setInt(2, sprintID);
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in SprintDAO.closeSprint");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public int getLastSprintID(int projectID) {
        Connection connection = null;
        int sprintID = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT MAX(ID) FROM SPRINT WHERE PROJECT = ?");
            preparedStatement.setInt(1, projectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                sprintID = resultSet.getInt(1);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            logger.warn("SQLException in SprintDAO.getLastSprintID");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return sprintID;
    }
}
