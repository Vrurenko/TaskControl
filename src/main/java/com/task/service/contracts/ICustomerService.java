package com.task.service.contracts;

import com.task.model.Proposal;
import com.task.model.Sprint;

import java.util.ArrayList;

public interface ICustomerService {
    /**
     *
     * @return
     */
    boolean hasProject();

    /**
     *
     * @return
     */
    ArrayList<Sprint> getSprints();

    /**
     *
     * @return
     */
    int getCustomerProjectID();

    /**
     *
     * @param proposal
     * @return
     */
    boolean offerProposal(Proposal proposal);

    /**
     *
     * @return
     */
    ArrayList<Proposal> getCustomerProposals();
}
