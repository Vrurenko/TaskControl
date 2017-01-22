package com.task.dao.interfaces;

import com.task.model.Sprint;

import java.util.ArrayList;

public interface ISprintDAO {

    public ArrayList<Sprint> getSprintList(int projectID);

    public String getSprintNameByID(int id);

    public boolean createSprint(Sprint sprint, int projectID);

    public boolean closeSprint(int sprintID);

    public int getLastSprintID(int sprintID);

}
