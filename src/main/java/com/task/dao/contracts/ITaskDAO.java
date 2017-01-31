package com.task.dao.contracts;

import com.task.model.Task;

import java.util.ArrayList;

public interface ITaskDAO {

    ArrayList<Task> getLastSprintTasks(int projectID);

    ArrayList<Task> getTasksBySprintID(int projectID);

    Task getTaskByID(int id);

    String getTaskNameById(int id);

    ArrayList<String> getFreeFromTaskEmployees(int taskID);

    ArrayList<String> getTaskEmployees(int taskID);

    boolean setEmployeeToTask(int taskID, String login);

    ArrayList<Task> getEmployeeTasks(int userID);

    boolean confirmTaskByID(int taskID);

    boolean completeTaskByID(int taskID);

    boolean addTask(Task task, int sprintID);

    int getTaskIdByName(String name);

    ArrayList<String> getLastSprintTaskNames(int sprintID);

}
