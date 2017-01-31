package com.task.service.contracts;

import com.task.model.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * Provides the interface for service to perform user duties.
 */
public interface IUserService {
    /**
     * Returns the map with user information required for authentication.
     * @param username the user login.
     * @return the map.
     */
    Map<String, Object> getUserByUsername(String username);

    /**
     * Creates new user.
     * @param user the user.
     * @return true if the user was created successfully, otherwise false.
     */
    boolean addUser(User user);

    /**
     * Returns the roles list.
     * @return roles list.
     */
    ArrayList<String> getRoles();

    /**
     * Returns the qualifications list.
     * @return the qualifications list.
     */
    ArrayList<String> getQualifications();

}
