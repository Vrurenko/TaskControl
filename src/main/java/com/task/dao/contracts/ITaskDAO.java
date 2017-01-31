package com.task.dao.contracts;

import com.task.model.Task;

import java.util.ArrayList;
/**
 * DAO for the {@link Task} objects.
 */
public interface ITaskDAO {

    /**
     * Returns task list of specified sprint.
     * @param sprintID The sprint id.
     * @return The task list.
     */
    ArrayList<Task> getTasksBySprintID(int sprintID);

    /**
     * Returns specified task.
     * @param id The task id.
     * @return The task.
     */
    Task getTaskByID(int id);

    /**
     * Returns name of the specified task.
     * @param id The task id.
     * @return task name.
     */
    String getTaskNameById(int id);

    /**
     * Returns login list of employees not working on specified task.
     * @param taskID The task id.
     * @return User login list.
     */
    ArrayList<String> getFreeFromTaskEmployees(int taskID);

    /**
     * Returns login list of employees working on specified task.
     * @param taskID The task id.
     * @return User login list.
     */
    ArrayList<String> getTaskEmployees(int taskID);

    /**
     * Sets specified employees for working on specified task.
     * @param taskID The task id.
     * @param login The employees login.
     * @return true if the employee was set successfully, otherwise false.
     */
    boolean setEmployeeToTask(int taskID, String login);

    /**
     * Returns task list of specified employee.
     * @param userID The employee id.
     * @return The task list.
     */
    ArrayList<Task> getEmployeeTasks(int userID);

    /**
     * Confirms the specified task.
     * @param taskID The task id.
     * @return true if the task was confirmed successfully, otherwise false.
     */
    boolean confirmTaskByID(int taskID);

    /**
     * Completes the specified task.
     * @param taskID The task id.
     * @return true if the task was completed successfully, otherwise false.
     */
    boolean completeTaskByID(int taskID);

    /**
     * Creates new task.
     * @param task The task.
     * @param sprintID The sprint id.
     * @return true if the task was created successfully, otherwise false.
     */
    boolean addTask(Task task, int sprintID);

    /**
     * Returns the task id by the task name.
     * @param name The task name.
     * @return the task id.
     */
    int getTaskIdByName(String name);

    /**
     * Returns the task names list of project last sprint.
     * @param sprintID The sprint id.
     * @return the list of task names.
     */
    ArrayList<String> getLastSprintTaskNames(int sprintID);

}
