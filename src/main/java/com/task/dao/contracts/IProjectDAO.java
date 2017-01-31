package com.task.dao.contracts;

import com.task.model.Project;

public interface IProjectDAO {
    boolean hasProject(int customerID);

    boolean createProject(Project project);

    int getProjectIdByManager(int id);

    int getProjectIdByCustomer(int id);

}
