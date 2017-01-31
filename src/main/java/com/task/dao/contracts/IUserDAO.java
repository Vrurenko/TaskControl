package com.task.dao.contracts;

import com.task.model.User;

import java.util.ArrayList;
/**
 * DAO for the {@link User} objects.
 */
public interface IUserDAO {

    /**
     * Returns list of employees logins.
     * @return The list of logins.
     */
    ArrayList<String> getEmployeeLogins();

    /**
     * Returns user by login.
     * @param login The user login.
     * @return The user.
     */
    User getUserByLogin(String login);

    /**
     * Returns user username by user id.
     * @param id The user id.
     * @return user username.
     */
    String getUsernameById(int id);

    /**
     * Returns user login by user id.
     * @param id The user id.
     * @return user login.
     */
    String getLoginById(int id);

    /**
     * Returns user id by login.
     * @param login The user login.
     * @return the user id.
     */
    int getUserIdByLogin(String login);

    /**
     * Set the employee to manager.
     * @param login the employee login.
     * @return true if the employee was set successfully, otherwise false.
     */
    boolean setManager(String login);

    /**
     * Demotes the manager to employee.
     * @param login the manager login.
     * @return true if the manager was demoted successfully, otherwise false.
     */
    boolean demoteManager(String login);

    /**
     * Creates new user.
     * @param user the user.
     * @return true if the user was created successfully, otherwise false.
     */
    boolean addUser(User user);
}
