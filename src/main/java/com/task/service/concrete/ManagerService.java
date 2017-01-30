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
 *
 */
public class ManagerService implements IManagerService {

    @Autowired
    @Qualifier("principalService")
    IPrincipalService principalService;

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Sprint> getSprints(){
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().getSprintList(getPrincipalProjectID());
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Task> getCurrentTasks(){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getLastSprintTasks(getPrincipalProjectID());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ArrayList<Task> getTasksBySprintID(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTasksBySprintID(id);
    }

    /**
     *
     * @return
     */
    private int getPrincipalProjectID(){
        return AbstractDAOFactory.getDAOFactory().getProjectDAO()
                .getProjectIdByManager(principalService.getCurrentPrincipalID());
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Task getTaskByID(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskByID(id);
    }

    /**
     *
     * @param taskID
     * @return
     */
    @Override
    public ArrayList<String> getFreeFromTaskEmployees(int taskID){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getFreeFromTaskEmployees(taskID);
    }

    /**
     *
     * @param taskID
     * @param login
     * @return
     */
    @Override
    public boolean setEmployeeToTask(int taskID, String login){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().setEmployeeToTask(taskID, login);
    }

    /**
     *
     * @param taskID
     * @return
     */
    @Override
    public ArrayList<String> getTaskEmployees(int taskID){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTaskEmployees(taskID);
    }

    /**
     *
     * @param sprint
     * @return
     */
    @Override
    public boolean createSprint(Sprint sprint){
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().createSprint(sprint, getPrincipalProjectID());
    }

    /**
     *
     * @return
     */
    @Override
    public boolean closeSprint(){
        int sprintID = AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(getPrincipalProjectID());
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().closeSprint(sprintID);
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<String> getQualifications(){
        return AbstractDAOFactory.getDAOFactory().getQualificationDAO().getQualificationsList();
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<String> getTaskNames(){
        int lastSprintID = AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(getPrincipalProjectID());
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getLastSprintTaskNames(lastSprintID);
    }

    /**
     *
     * @param task
     * @return
     */
    @Override
    public boolean addTask(Task task){
        int sprintID = AbstractDAOFactory.getDAOFactory().getSprintDAO().getLastSprintID(getPrincipalProjectID());
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().addTask(task, sprintID);
    }

}
