package com.task.dao.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.dao.ConnectionPool;
import com.task.dao.contracts.ITaskDAO;
import com.task.model.Task;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class TaskDAO implements ITaskDAO {
    private static Logger logger = Logger.getLogger(TaskDAO.class);
    private ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public ArrayList<Task> getTasksBySprintID(int id) {
        ArrayList<Task> list = new ArrayList<Task>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM TASK WHERE SPRINT = ? ORDER BY ID");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setName(resultSet.getString("name"));
                task.setId(resultSet.getInt("id"));
                task.setEstimate(resultSet.getInt("estimate"));
                task.setStartDate(resultSet.getDate("start_date"));
                task.setEndDate(resultSet.getDate("end_date"));
                task.setComplete(resultSet.getBoolean("complete"));
                task.setPrimaryTask(AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskNameById(resultSet.getInt("subtask_of")));
                task.setQualification(AbstractDAOFactory.getDAOFactory().getQualificationDAO().getQualificationById(resultSet.getInt("qualification")));
                list.add(task);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.getTasksBySprintID");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public Task getTaskByID(int id) {
        Connection connection = null;
        Task task = new Task();
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM TASK WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                task.setId(resultSet.getInt("id"));
                task.setPrimaryTask(AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskNameById(resultSet.getInt("subtask_of")));
                task.setSprint(AbstractDAOFactory.getDAOFactory().getSprintDAO().getSprintNameByID(resultSet.getInt("sprint")));
                task.setQualification(AbstractDAOFactory.getDAOFactory().getQualificationDAO().getQualificationById(resultSet.getInt("qualification")));
                task.setEstimate(resultSet.getInt("estimate"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getDate("start_date"));
                task.setEndDate(resultSet.getDate("end_date"));
                task.setComplete(resultSet.getBoolean("complete"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.getTaskNameById");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return task;
    }

    @Override
    public String getTaskNameById(int id) {
        Connection connection = null;
        String name = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT NAME FROM TASK WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.getTaskNameById");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return name;
    }

    @Override
    public ArrayList<String> getFreeFromTaskEmployees(int taskID) {
        ArrayList<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT LOGIN FROM USERS WHERE QUALIFICATION >= \n" +
                            "                              (SELECT QUALIFICATION FROM TASK WHERE ID = ?)\n" +
                            "      AND ROLE = 2\n" +
                            "      AND (SELECT COMPLETE FROM TASK WHERE ID = ?) = 0\n" +
                            "MINUS\n" +
                            "SELECT LOGIN FROM USER_TASK_MAP\n" +
                            "  LEFT JOIN USERS ON USER_TASK_MAP.USER_ID = USERS.ID\n" +
                            "WHERE TASK_ID = ?");
            preparedStatement.setInt(1, taskID);
            preparedStatement.setInt(2, taskID);
            preparedStatement.setInt(3, taskID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("login"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.getFreeFromTaskEmployees");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public boolean setEmployeeToTask(int taskID, String login) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO USER_TASK_MAP (USER_ID, TASK_ID) VALUES (?, ?)");
            preparedStatement.setInt(1, AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(login));
            preparedStatement.setInt(2, taskID);
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.setEmployeeToTask");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public ArrayList<String> getTaskEmployees(int taskID) {
        ArrayList<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT LOGIN FROM USER_TASK_MAP\n" +
                            "  LEFT JOIN USERS ON USER_TASK_MAP.USER_ID = USERS.ID\n" +
                            "WHERE TASK_ID = ?");
            preparedStatement.setInt(1, taskID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("login"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.getTaskEmployees");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public ArrayList<Task> getEmployeeTasks(int userID) {
        ArrayList<Task> list = new ArrayList<Task>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM TASK LEFT JOIN USER_TASK_MAP ON TASK_ID = TASK.ID WHERE USER_ID = ? AND COMPLETE = 0 ORDER BY ID");
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setEstimate(resultSet.getInt("estimate"));
                task.setName(resultSet.getString("name"));
                task.setSprint(AbstractDAOFactory.getDAOFactory().getSprintDAO().getSprintNameByID(resultSet.getInt("sprint")));
                task.setPrimaryTask(AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskNameById(resultSet.getInt("subtask_of")));
                task.setStartDate(resultSet.getDate("start_date"));
                task.setEndDate(resultSet.getDate("end_date"));
                task.setConfirm(resultSet.getBoolean("accepted"));
                task.setComplete(resultSet.getBoolean("complete"));
                list.add(task);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.getEmployeeTasks");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public boolean confirmTaskByID(int taskID) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE USER_TASK_MAP SET ACCEPTED = 1 WHERE TASK_ID = ?");
            preparedStatement.setInt(1, taskID);
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.confirmTaskByID");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean completeTaskByID(int taskID) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE TASK SET COMPLETE = 1, END_DATE = SYSDATE  WHERE ID = ?\n" +
                            " AND 0 NOT IN (SELECT COMPLETE FROM TASK WHERE SUBTASK_OF = ?)");
            preparedStatement.setInt(1, taskID);
            preparedStatement.setInt(2, taskID);
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.completeTaskByID");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean addTask(Task task, int sprintID) {
        Connection connection = null;
        boolean result = false;
        int subTask = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO TASK (NAME, ESTIMATE, SUBTASK_OF, SPRINT, QUALIFICATION) "
                            + "VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getEstimate());
            subTask = AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskIdByName(task.getPrimaryTask());
            if (subTask == 0){
                preparedStatement.setNull(3, Types.INTEGER);
            } else {
                preparedStatement.setInt(3, subTask);
            }
            preparedStatement.setInt(4, sprintID);
            preparedStatement.setInt(5, AbstractDAOFactory.getDAOFactory().getQualificationDAO().getIdByQualification(task.getQualification()));
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.addTask");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public int getTaskIdByName(String name) {
        Connection connection = null;
        int taskID = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT ID FROM TASK WHERE NAME = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                taskID = resultSet.getInt("id");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.getTaskIdByName");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return taskID;
    }

    @Override
    public ArrayList<String> getLastSprintTaskNames(int sprintID) {
        ArrayList<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT NAME FROM TASK WHERE SPRINT = ?");
            preparedStatement.setInt(1, sprintID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("name"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in TaskDAO.getLastSprintTaskNames");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

}
