package com.task.service.contracts;

import com.task.model.Task;

import java.util.ArrayList;

public interface IEmployeeService {
    /**
     *
     * @return
     */
    ArrayList<Task> getEmployeeTasks();

    /**
     *
     * @param id
     * @return
     */
    boolean confirmTask(int id);

    /**
     *
     * @param id
     * @return
     */
    boolean completeTask(int id);
}
