package com.task.service;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Task;
import com.task.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("employeeService")
@Transactional
public class EmployeeService {

    @Autowired
    PrincipalService principalService;

    public ArrayList<Task> getEmployeeTasks(){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getEmployeeTasks(principalService.getCurrentPrincipalID());
    }

    public boolean confirmTask(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().confirmTaskByID(id);
    }

    public boolean completeTask(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().completeTaskByID(id);
    }

    public boolean createTicket(Ticket ticket){
        return false;
    }
}
