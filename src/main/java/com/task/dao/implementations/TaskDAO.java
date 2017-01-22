package com.task.dao.implementations;

import com.sun.org.apache.xerces.internal.parsers.AbstractDOMParser;
import com.task.dao.AbstractDAOFactory;
import com.task.dao.ConnectionPool;
import com.task.dao.interfaces.ITaskDAO;
import com.task.model.Task;
import com.task.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TaskDAO implements ITaskDAO {
    private ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public ArrayList<Task> getLastSprintTasks(int projectID) {
        ArrayList<Task> list = new ArrayList<Task>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM TASK WHERE SPRINT = (SELECT MAX(ID) FROM SPRINT WHERE COMPLETE = 0 AND PROJECT = ?)");
            preparedStatement.setInt(1, projectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setEstimate(resultSet.getInt("estimate"));
                task.setSubTaskOf(AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskNameById(resultSet.getInt("subtask_of")));
                task.setStartDate(resultSet.getDate("start_date"));
                task.setEndDate(resultSet.getDate("end_date"));
                task.setComplete(resultSet.getBoolean("complete"));
                list.add(task);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in TaskDAO.getLastSprintTasks");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public ArrayList<Task> getTasksBySprintID(int id) {
        ArrayList<Task> list = new ArrayList<Task>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM TASK WHERE SPRINT = ?");
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
                task.setSubTaskOf(AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskNameById(resultSet.getInt("subtask_of")));
                task.setQualification(AbstractDAOFactory.getDAOFactory().getQualificationDAO().getQualificationById(resultSet.getInt("qualification")));
                list.add(task);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in TaskDAO.getTasksBySprintID");
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
                task.setSubTaskOf(AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskNameById(resultSet.getInt("subtask_of")));
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
            System.out.println("SQLException in TaskDAO.getTaskNameById");
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
            System.out.println("SQLException in TaskDAO.getTaskNameById");
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
                            "MINUS\n" +
                            "SELECT LOGIN FROM USER_TASK_MAP\n" +
                            "  LEFT JOIN USERS ON USER_TASK_MAP.USER_ID = USERS.ID\n" +
                            "WHERE TASK_ID = ?");
            preparedStatement.setInt(1, taskID);
            preparedStatement.setInt(2, taskID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("login"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in TaskDAO.getFreeFromTaskEmployees");
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
            System.out.println("SQLException in TaskDAO.setEmployeeToTask");
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
            System.out.println("SQLException in TaskDAO.getTaskEmployees");
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
                    .prepareStatement("SELECT * FROM TASK LEFT JOIN USER_TASK_MAP ON TASK_ID = TASK.ID WHERE USER_ID = ? AND COMPLETE = 0");
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setEstimate(resultSet.getInt("estimate"));
                task.setName(resultSet.getString("name"));
                task.setSprint(AbstractDAOFactory.getDAOFactory().getSprintDAO().getSprintNameByID(resultSet.getInt("sprint")));
                task.setSubTaskOf(AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskNameById(resultSet.getInt("subtask_of")));
                task.setStartDate(resultSet.getDate("start_date"));
                task.setEndDate(resultSet.getDate("end_date"));
                task.setConfirm(resultSet.getBoolean("accepted"));
                task.setComplete(resultSet.getBoolean("complete"));
                list.add(task);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in TaskDAO.getEmployeeTasks");
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
            System.out.println("SQLException in TaskDAO.confirmTaskByID");
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
                    .prepareStatement("UPDATE TASK SET COMPLETE = 1 WHERE ID = ?\n" +
                            " AND 0 NOT IN (SELECT COMPLETE FROM TASK WHERE SUBTASK_OF = ?)");
            preparedStatement.setInt(1, taskID);
            preparedStatement.setInt(2, taskID);
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in TaskDAO.completeTaskByID");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean addTask(Task task, int projectID) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO TASK (NAME, ESTIMATE, END_DATE, SUBTASK_OF, SPRINT, QUALIFICATION) "
                            + "VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, task.getName());
            preparedStatement.setInt(2, task.getEstimate());
            preparedStatement.setDate(3, new java.sql.Date(task.getStartDate().getTime() + TimeUnit.DAYS.toMillis(task.getEstimate())));
            preparedStatement.setInt(4, AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskIdByName(task.getName()));
            preparedStatement.setInt(5, AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(projectID));
            preparedStatement.setInt(6, AbstractDAOFactory.getDAOFactory().getQualificationDAO().getIdByQualification(task.getQualification()));
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in TaskDAO.addTask");
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
            System.out.println("SQLException in TaskDAO.getTaskIdByName");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return taskID;
    }

}
