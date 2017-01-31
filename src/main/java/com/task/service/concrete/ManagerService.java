package com.task.service.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Sprint;
import com.task.model.Task;
import com.task.service.contracts.IPrincipalService;
import com.task.service.contracts.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("managerService")
@Transactional
/**
 * Provides the implementation for service to perform manager duties.
 */
public class ManagerService implements IManagerService {

    @Autowired
    @Qualifier("principalService")
    IPrincipalService principalService;

    @Override
    public ArrayList<Sprint> getSprints(){
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().getSprintList(getPrincipalProjectID());
    }

    private int getPrincipalProjectID(){
        return AbstractDAOFactory.getDAOFactory().getProjectDAO()
                .getProjectIdByManager(principalService.getCurrentPrincipalID());
    }

    @Override
    public Task getTaskByID(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskByID(id);
    }

    @Override
    public ArrayList<String> getFreeFromTaskEmployees(int taskID){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getFreeFromTaskEmployees(taskID);
    }

    @Override
    public boolean setEmployeeToTask(int taskID, String login){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().setEmployeeToTask(taskID, login);
    }

    @Override
    public ArrayList<String> getTaskEmployees(int taskID){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskEmployees(taskID);
    }

    @Override
    public boolean createSprint(Sprint sprint){
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().createSprint(sprint, getPrincipalProjectID());
    }

    @Override
    public boolean closeSprint(){
        int sprintID = AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(getPrincipalProjectID());
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().closeSprint(sprintID);
    }

    @Override
    public ArrayList<String> getQualifications(){
        return AbstractDAOFactory.getDAOFactory().getQualificationDAO().getQualificationsList();
    }

    @Override
    public ArrayList<String> getTaskNames(){
        int lastSprintID = AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(getPrincipalProjectID());
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getLastSprintTaskNames(lastSprintID);
    }

    @Override
    public boolean addTask(Task task){
        int sprintID = AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(getPrincipalProjectID());
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().addTask(task, sprintID);
    }

}
