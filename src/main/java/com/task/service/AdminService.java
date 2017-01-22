package com.task.service;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Project;
import com.task.model.Proposal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("adminService")
@Transactional
public class AdminService {

    public ArrayList<Proposal> getProposalList(){
        return AbstractDAOFactory.getDAOFactory().getProposalDAO().getProposalList();
    }

    public ArrayList<String> getEmployeeList(){
        return AbstractDAOFactory.getDAOFactory().getUserDAO().getEmployeeLogins();
    }

    public boolean acceptProposal(Project project){
        boolean result = AbstractDAOFactory.getDAOFactory().getProjectDAO().createProject(project);
        if (result){
            AbstractDAOFactory.getDAOFactory().getUserDAO().setManager(project.getManager());
            AbstractDAOFactory.getDAOFactory().getProposalDAO().approveProposal(project.getProposalId());
        }
        return result;
    }

}
