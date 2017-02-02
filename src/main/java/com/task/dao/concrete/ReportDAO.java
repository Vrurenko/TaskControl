package com.task.dao.concrete;

import com.task.dao.ConnectionPool;
import com.task.dao.contracts.IReportDAO;
import com.task.model.UserTaskInfo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportDAO implements IReportDAO{
    private static Logger logger = Logger.getLogger(ReportDAO.class);
    private ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public HashMap<String, Float> getTaskReport() {
        HashMap<String, Float> map = new HashMap<String, Float>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT NAME, START_DATE + ESTIMATE - NVL(END_DATE,sysdate) AS DELAY FROM TASK");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("NAME"), resultSet.getFloat("DELAY"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in ReportDAO.getTaskReport");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return map;
    }

    @Override
    public ArrayList<UserTaskInfo> getUserTaskInfo() {
        ArrayList<UserTaskInfo> list = new ArrayList<UserTaskInfo>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT USERS.NAME AS USERNAME, PROJECT.NAME AS PROJECT, SPRINT.NAME  AS SPRINT,\n" +
                            "  TASK.NAME AS TASK, TASK.COMPLETE\n" +
                            "FROM TASK\n" +
                            "  LEFT JOIN USER_TASK_MAP ON TASK.ID = USER_TASK_MAP.TASK_ID\n" +
                            "  LEFT JOIN SPRINT ON TASK.SPRINT = SPRINT.ID\n" +
                            "  LEFT JOIN PROJECT ON SPRINT.PROJECT = PROJECT.ID\n" +
                            "  LEFT JOIN USERS ON USER_TASK_MAP.USER_ID = USERS.ID\n" +
                            "WHERE USER_ID IN (SELECT ID FROM USERS WHERE ROLE = 2)\n" +
                            "ORDER BY USERNAME, PROJECT, SPRINT, TASK");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserTaskInfo userTaskInfo = new UserTaskInfo();
                userTaskInfo.setUserLogin(resultSet.getString("username"));
                userTaskInfo.setProject(resultSet.getString("project"));
                userTaskInfo.setSprint(resultSet.getString("sprint"));
                userTaskInfo.setTask(resultSet.getString("task"));
                userTaskInfo.setCompleted(resultSet.getBoolean("complete"));
                list.add(userTaskInfo);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in ReportDAO.getUserTaskInfo");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }
}
