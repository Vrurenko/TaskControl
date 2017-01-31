package com.task.service.contracts;

import com.task.model.Proposal;
import com.task.model.Sprint;

import java.util.ArrayList;
/**
 * Provides the interface for service to perform customer duties.
 */
public interface ICustomerService {
    /**
     * Returns true if the authorized user is a customer of some project.
     * @return true if the customer has a project, otherwise false.
     */
    boolean hasProject();

    /**
     * Returns a authorised user`s project sprint list.
     * @return customer`s project sprint list.
     */
    ArrayList<Sprint> getSprints();

    /**
     * Returns the authorised user project id.
     * @return project id.
     */
    int getCustomerProjectID();

    /**
     * Creates a new proposal.
     * @param proposal the proposal.
     * @return true if proposal was added, otherwise false.
     */
    boolean offerProposal(Proposal proposal);

    /**
     * Returns list of customer proposals
     * @return list of proposals
     */
    ArrayList<Proposal> getCustomerProposals();
}
