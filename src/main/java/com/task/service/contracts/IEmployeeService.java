package com.task.service.contracts;

import com.task.model.Task;

import java.util.ArrayList;
/**
 * Provides service to perform employee duties.
 */
public interface IEmployeeService {
    /**
     * Return the list of concrete employee.
     * @return list of tasks.
     */
    ArrayList<Task> getEmployeeTasks();

    /**
     * Confirms the concrete task reception.
     * @param id task id.
     * @return true if the task was confirmed, otherwise false.
     */
    boolean confirmTask(int id);

    /**
     * Completes the concrete task.
     * @param id task id.
     * @return true if the task was completed.
     */
    boolean completeTask(int id);
}
