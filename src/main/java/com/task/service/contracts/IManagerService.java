package com.task.service.contracts;

import com.task.model.Sprint;
import com.task.model.Task;

import java.util.ArrayList;
/**
 * Provides service to perform manager duties.
 */
public interface IManagerService {
    /**
     * Returns the list of manager`s project sprints.
     * @return the list of sprints.
     */
    ArrayList<Sprint> getSprints();

    /**
     *
     * @param id
     * @return
     */
    Task getTaskByID(int id);

    /**
     *
     * @param taskID
     * @return
     */
    ArrayList<String> getFreeFromTaskEmployees(int taskID);

    /**
     *
     * @param taskID
     * @param login
     * @return
     */
    boolean setEmployeeToTask(int taskID, String login);

    /**
     *
     * @param taskID
     * @return
     */
    ArrayList<String> getTaskEmployees(int taskID);

    /**
     *
     * @param sprint
     * @return
     */
    boolean createSprint(Sprint sprint);

    /**
     *
     * @return
     */
    boolean closeSprint();

    /**
     *
     * @return
     */
    ArrayList<String> getQualifications();

    /**
     *
     * @return
     */
    ArrayList<String> getTaskNames();

    /**
     *
     * @param task
     * @return
     */
    boolean addTask(Task task);
}
