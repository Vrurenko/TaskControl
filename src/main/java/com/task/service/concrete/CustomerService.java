package com.task.service.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Proposal;
import com.task.model.Sprint;
import com.task.service.contracts.ICustomerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("customerService")
@Transactional
/**
 * Provides the implementation for service to perform customer duties.
 */
public class CustomerService implements ICustomerService {

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @Override
    public boolean hasProject() {
        int customerID = AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(getPrincipal());
        return AbstractDAOFactory.getDAOFactory().getProjectDAO().hasProject(customerID);
    }

    @Override
    public ArrayList<Sprint> getSprints(){
        return AbstractDAOFactory.getDAOFactory().getSprintDAO().getSprintList(getCustomerProjectID());
    }

    @Override
    public int getCustomerProjectID(){
        int customerID = AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(getPrincipal());
        return AbstractDAOFactory.getDAOFactory().getProjectDAO().getProjectIdByCustomer(customerID);
    }

    @Override
    public boolean offerProposal(Proposal proposal) {
        proposal.setCustomer(getPrincipal());
        return AbstractDAOFactory.getDAOFactory().getProposalDAO().createProposal(proposal);
    }

    @Override
    public ArrayList<Proposal> getCustomerProposals() {
        return AbstractDAOFactory.getDAOFactory().getProposalDAO().getProposalsByCustomer(getPrincipal());
    }

}
