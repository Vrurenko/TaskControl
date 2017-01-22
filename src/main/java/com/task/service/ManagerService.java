package com.task.service;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Sprint;
import com.task.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("managerService")
@Transactional
public class ManagerService {

    @Autowired
    @Qualifier("principalService")
    PrincipalService principalService;

    public ArrayList<Sprint> getSprints(){
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().getSprintList(getPrincipalProjectID());
    }

    public ArrayList<Task> getCurrentTasks(){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getLastSprintTasks(getPrincipalProjectID());
    }

    public ArrayList<Task> getTasksBySprintID(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTasksBySprintID(id);
    }

    private int getPrincipalProjectID(){
        return AbstractDAOFactory.getDAOFactory().getProjectDAO()
                .getProjectIdByManager(principalService.getCurrentPrincipalID());
    }

    public Task getTaskByID(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskByID(id);
    }

    public ArrayList<String> getFreeFromTaskEmployees(int taskID){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getFreeFromTaskEmployees(taskID);
    }

    public boolean setEmployeeToTask(int taskID, String login){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().setEmployeeToTask(taskID, login);
    }

    public ArrayList<String> getTaskEmployees(int taskID){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskEmployees(taskID);
    }

    public boolean createSprint(Sprint sprint){
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().createSprint(sprint, getPrincipalProjectID());
    }

    public boolean closeSprint(){
        int sprintID = AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(getPrincipalProjectID());
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().closeSprint(sprintID);
    }

    public boolean addTask(Task task){
        int sprintID = AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(getPrincipalProjectID());

        return false; // TODO: 22.01.2017 Доделать
    }

}
