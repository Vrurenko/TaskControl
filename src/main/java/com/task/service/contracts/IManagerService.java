package com.task.service.contracts;

import com.task.model.Sprint;
import com.task.model.Task;

import java.util.ArrayList;
/**
 * Provides the interface for service to perform manager duties.
 */
public interface IManagerService {
    /**
     * Returns the list of manager`s project sprints.
     * @return the list of sprints.
     */
    ArrayList<Sprint> getSprints();

    /**
     * Returns the task by id.
     * @param id the task id.
     * @return the task.
     */
    Task getTaskByID(int id);

    /**
     * Returns a list of employees not working on specified task.
     * @param taskID the task id.
     * @return the list of employees.
     */
    ArrayList<String> getFreeFromTaskEmployees(int taskID);

    /**
     * Sets an employee to perform a task.
     * @param taskID the task id.
     * @param login the employee login.
     * @return true if the employee was set successfully, otherwise false.
     */
    boolean setEmployeeToTask(int taskID, String login);

    /**
     * Returns the list of employees working on the specified task.
     * @param taskID the task id.
     * @return the list of employees.
     */
    ArrayList<String> getTaskEmployees(int taskID);

    /**
     * Creates new sprint.
     * @param sprint the sprint.
     * @return true if the sprint was created successfully, otherwise false.
     */
    boolean createSprint(Sprint sprint);

    /**
     * Closes a project last sprint.
     * @return true if the sprint was closed successfully, otherwise false.
     */
    boolean closeSprint();

    /**
     * Returns the qualification list.
     * @return the qualification list.
     */
    ArrayList<String> getQualifications();

    /**
     * Returns the project last sprint task names list.
     * @return the task names list.
     */
    ArrayList<String> getTaskNames();

    /**
     * Creates a nes task.
     * @param task the task.
     * @return true if the task was created successfully, otherwise false.
     */
    boolean addTask(Task task);

    /**
     * Closes specified project.
     * @return true id the project was closed successfully, otherwise false.
     */
    boolean closeProject();
}
