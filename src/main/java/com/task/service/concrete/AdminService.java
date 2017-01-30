package com.task.service.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Project;
import com.task.model.Proposal;
import com.task.service.contracts.IAdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("adminService")
@Transactional
/**
 *
 */
public class AdminService implements IAdminService {

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Proposal> getProposalList(){
        return AbstractDAOFactory.getDAOFactory().getProposalDAO().getProposalList();
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<String> getEmployeeList(){
        return AbstractDAOFactory.getDAOFactory().getUserDAO().getEmployeeLogins();
    }

    /**
     *
     * @param project
     * @return
     */
    @Override
    public boolean acceptProposal(Project project){
        boolean result = AbstractDAOFactory.getDAOFactory().getProjectDAO().createProject(project);
        if (result){
            AbstractDAOFactory.getDAOFactory().getUserDAO().setManager(project.getManager());
            AbstractDAOFactory.getDAOFactory().getProposalDAO().approveProposal(project.getProposalId());
        }
        return result;
    }

}
