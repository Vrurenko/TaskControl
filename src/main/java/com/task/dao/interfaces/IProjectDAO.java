package com.task.dao.interfaces;

import com.task.model.Project;

public interface IProjectDAO {

    public boolean createProject(Project project);

    public int getProjectIdByManager(int id);

}
