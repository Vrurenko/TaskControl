package com.task.service.contracts;

/**
 * Provides service to perform administrator duties.
 */
public interface IPrincipalService {
    /**
     * @return
     */
    String getCurrentPrincipal();

    /**
     * @return
     */
    int getCurrentPrincipalID();
}
