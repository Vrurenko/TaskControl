package com.task.service.contracts;

/**
 * Provides the interface for service to perform principal duties.
 */
public interface IPrincipalService {
    /**
     * Returns the current authorised user login
     * @return user login
     */
    String getCurrentPrincipal();

    /**
     * returns the current authorised user id.
     * @return user id
     */
    int getCurrentPrincipalID();
}
