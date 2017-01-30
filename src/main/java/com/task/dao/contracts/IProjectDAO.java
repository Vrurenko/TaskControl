package com.task.dao.contracts;

import com.task.model.Project;

public interface IProjectDAO {
    public boolean hasProject(int customerID);

    public boolean createProject(Project project);

    public int getProjectIdByManager(int id);

    public int getProjectIdByCustomer(int id);

}
