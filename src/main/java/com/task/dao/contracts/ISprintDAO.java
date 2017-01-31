package com.task.dao.contracts;

import com.task.model.Sprint;

import java.util.ArrayList;
/**
 * DAO for the {@link Sprint} objects.
 */
public interface ISprintDAO {

    /**
     * Returns the sprint list of specified project.
     * @param projectID The project id.
     * @return The list of sprints.
     */
    ArrayList<Sprint> getSprintList(int projectID);

    /**
     * Returns the name of the specified sprint.
     * @param id The sprint id.
     * @return The sprint name.
     */
    String getSprintNameByID(int id);

    /**
     * Creates a new sprint in specified project.
     * @param sprint The sprint to create.
     * @param projectID The project id.
     * @return true if the sprint was created successfully, otherwise false.
     */
    boolean createSprint(Sprint sprint, int projectID);

    /**
     * Closes specified sprint.
     * @param sprintID The sprint id.
     * @return true if the sprint was closed successfully, otherwise false.
     */
    boolean closeSprint(int sprintID);

    /**
     * Return the specified project last sprint id.
     * @param projectID The project id.
     * @return sprint id.
     */
    int getLastSprintID(int projectID);

}
