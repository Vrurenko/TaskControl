package com.task.service.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Task;
import com.task.service.contracts.IPrincipalService;
import com.task.service.contracts.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("employeeService")
@Transactional
/**
 * Provides the implementation for service to perform employee duties.
 */
public class EmployeeService implements IEmployeeService {

    @Autowired
    IPrincipalService principalService;

    @Override
    public ArrayList<Task> getEmployeeTasks(){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getEmployeeTasks(principalService.getCurrentPrincipalID());
    }

    @Override
    public boolean confirmTask(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().confirmTaskByID(id);
    }

    @Override
    public boolean completeTask(int id){
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().completeTaskByID(id);
    }
}
