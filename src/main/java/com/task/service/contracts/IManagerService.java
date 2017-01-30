package com.task.service.contracts;

import com.task.model.Sprint;
import com.task.model.Task;

import java.util.ArrayList;

public interface IManagerService {
    /**
     *
     * @return
     */
    ArrayList<Sprint> getSprints();

    /**
     *
     * @return
     */
    ArrayList<Task> getCurrentTasks();

    /**
     *
     * @param id
     * @return
     */
    ArrayList<Task> getTasksBySprintID(int id);

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
