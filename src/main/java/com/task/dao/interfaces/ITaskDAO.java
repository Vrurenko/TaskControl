package com.task.dao.interfaces;

import com.task.model.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ITaskDAO {

    public ArrayList<Task> getLastSprintTasks(int projectID);

    public ArrayList<Task> getTasksBySprintID(int projectID);

    public Task getTaskByID(int id);

    public String getTaskNameById(int id);

    public ArrayList<String> getFreeFromTaskEmployees(int taskID);

    public ArrayList<String> getTaskEmployees(int taskID);

    public boolean setEmployeeToTask(int taskID, String login);

    public ArrayList<Task> getEmployeeTasks(int userID);

    public boolean confirmTaskByID(int taskID);

    public boolean completeTaskByID(int taskID);

    public boolean addTask(Task task, int sprintID);

    public int getTaskIdByName(String name);

    public ArrayList<String> getLastSprintTaskNames(int sprintID);

}
