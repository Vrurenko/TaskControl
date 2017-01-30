package com.task.dao.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.model.User;
import com.task.dao.ConnectionPool;
import com.task.dao.contracts.IUserDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements IUserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class);
    ConnectionPool connectionPool = new ConnectionPool();


    public ArrayList<User> getUsersByRole(String role) {
        ArrayList<User> list = new ArrayList<User>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE ROLE = ?");
            preparedStatement.setInt(1, AbstractDAOFactory.getDAOFactory().getRoleDAO().getIdByRole(role));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in UserDAO.getUsersByRole");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public boolean addUser(User user) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.
                    prepareStatement("INSERT INTO USERS (ROLE, QUALIFICATION, NAME, SURNAME, LOGIN, PASSWORD) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, AbstractDAOFactory.getDAOFactory().getRoleDAO().getIdByRole(user.getRole()));
            int qualification = AbstractDAOFactory.getDAOFactory().getQualificationDAO().getIdByQualification(user.getQualification());
            if (qualification == 0) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, qualification);
            }
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            if (preparedStatement.executeUpdate() > 0) {
                result = true;
            }
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in UserDAO.addUser");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean checkUserByLogin(String login) {
        Connection connection = null;
        boolean result = true;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE LOGIN = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.next();
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQLException in UserDAO.checkUserByLogin");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public User getUserByLogin(String login) {
        Connection connection = null;
        User user = new User();
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM USERS WHERE LOGIN = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(AbstractDAOFactory.getDAOFactory().getRoleDAO().getRoleById(resultSet.getInt("role")));
                user.setQualification(AbstractDAOFactory.getDAOFactory().getQualificationDAO().getQualificationById(resultSet.getInt("qualification")));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQLException in UserDAO.getUserByLogin");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return user;
    }

    @Override
    public int getUserIdByLogin(String login) {
        Connection connection = null;
        int id = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT ID FROM USERS WHERE LOGIN = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQLException in UserDAO.getUserIdByLogin");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return id;
    }

    @Override
    public String getUsernameById(int id) {
        Connection connection = null;
        String userName = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT NAME, SURNAME FROM USERS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userName = resultSet.getString("name") + " " + resultSet.getString("surname");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQLException in UserDAO.getUsernameById");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return userName;
    }

    @Override
    public ArrayList<String> getEmployeeLogins() {
        ArrayList<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT LOGIN FROM USERS WHERE ROLE = 2");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("login"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in UserDAO.getEmployeeLogins");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public ArrayList<String> getEmployeeByQualification(int qualification) {
        ArrayList<String> list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT LOGIN FROM USERS WHERE QUALIFICATION >= ?");
            preparedStatement.setInt(1, qualification);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(resultSet.getString("login"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in UserDAO.getEmployeeByQualification");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public String getLoginById(int id) {
        Connection connection = null;
        String login = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT LOGIN FROM USERS WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                login = resultSet.getString("login");
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQLException in UserDAO.getLoginById");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return login;
    }

    @Override
    public boolean setManager(String login) {
        Connection connection = null;
        Boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE USERS SET ROLE = 4 WHERE LOGIN = ?");
            preparedStatement.setString(1, login);
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQLException in UserDAO.setManager");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }

    @Override
    public boolean demoteManager(String login) {
        Connection connection = null;
        Boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE USERS SET ROLE = 2 WHERE LOGIN = ?");
            preparedStatement.setString(1, login);
            result = preparedStatement.executeUpdate() > 0;
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException e) {
            System.out.println("SQLException in UserDAO.demoteManager");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return result;
    }


}
