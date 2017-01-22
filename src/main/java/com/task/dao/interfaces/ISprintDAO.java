package com.task.dao.interfaces;

import com.task.model.Sprint;

import java.util.ArrayList;

public interface ISprintDAO {

    ArrayList<Sprint> getSprintList(int projectID);

    String getSprintNameByID(int id);

    boolean createSprint(Sprint sprint, int projectID);

    boolean closeSprint(int sprintID);

    int getLastSprintID(int projectID);

}
