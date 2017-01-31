package com.task.service.contracts;

import com.task.model.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * Provides service to perform administrator duties.
 */
public interface IUserService {
    /**
     * @param username
     * @return
     */
    Map<String, Object> getUserByUsername(String username);

    /**
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * @return
     */
    ArrayList<String> getRoles();

    /**
     * @return
     */
    ArrayList<String> getQualifications();

    /**
     * @return
     */
    String getCurrentPrincipal();
}
