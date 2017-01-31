package com.task.dao.concrete;

import com.task.dao.ConnectionPool;
import com.task.dao.contracts.IQualificationDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QualificationDAO implements IQualificationDAO {
    private static Logger logger = Logger.getLogger(QualificationDAO.class);
    ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public ArrayList<String> getQualificationsList() {
        ArrayList<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT POSITION FROM QUALIFICATION");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("position"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in QualificationDAO.getQualificationsList");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public int getIdByQualification(String qualification) {
        Connection connection = null;
        int id = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ID FROM QUALIFICATION WHERE POSITION = ?");
            preparedStatement.setString(1, qualification);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in QualificationDAO.getIdByQualification");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return id;
    }

    @Override
    public String getQualificationById(int id) {
        Connection connection = null;
        String qualification = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT POSITION FROM QUALIFICATION WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                qualification = resultSet.getString("position");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in QualificationDAO.getQualificationById");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return qualification;
    }
}
