package com.task.dao.implementations;

import com.task.dao.ConnectionPool;
import com.task.dao.interfaces.IQualificationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QualificationDAO implements IQualificationDAO {
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
        } catch (SQLException ex) {
            System.out.println("SQLException in QualificationDAO.getQualificationsList");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
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
        } catch (SQLException ex) {
            System.out.println("SQLException in QualificationDAO.getIdByQualification");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
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
        } catch (SQLException ex) {
            System.out.println("SQLException in QualificationDAO.getQualificationById");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return qualification;
    }
}
