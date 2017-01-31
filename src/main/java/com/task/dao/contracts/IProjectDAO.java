package com.task.dao.contracts;

import com.task.model.Project;

/**
 * DAO for the {@link Project} objects.
 */
public interface IProjectDAO {
    /**
     * Checks to see if the customer has a project
     * @param customerID The customer id
     * @return true if the customer has a project, otherwise false.
     */
    boolean hasProject(int customerID);

    /**
     * Creates a new project.
     * @param project The project.
     * @return true, if the project was created successfully, otherwise false.
     */
    boolean createProject(Project project);

    /**
     * Returns the project id by the manager id.
     * @param id The manager id.
     * @return project id.
     */
    int getProjectIdByManager(int id);

    /**
     * Returns the project id by customer id.
     * @param id The customer id.
     * @return project id.
     */
    int getProjectIdByCustomer(int id);

    /**
     * Closes specified project.
     * @param projectID the project id.
     * @return true id the project was slosed successfully, otherwise false.
     */
    boolean closeProject(int projectID);

}
