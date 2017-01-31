package com.task.dao.contracts;

import com.task.model.Proposal;

import java.util.ArrayList;
/**
 * DAO for the {@link Proposal} objects.
 */
public interface IProposalDAO {

    /**
     * Creates new proposal.
     * @param proposal The proposal.
     * @return true if the proposal was created successfully, otherwise false.
     */
    boolean createProposal(Proposal proposal);

    /**
     * Returns proposals of specified customer.
     * @param customer The customer login.
     * @return list of proposals.
     */
    ArrayList<Proposal> getProposalsByCustomer(String customer);

    /**
     * Returns all proposals list
     * @return proposals list
     */
    ArrayList<Proposal> getProposalList();

    /**
     * Approves specified proposal.
     * @param id The proposal id.
     * @return true if the proposal was approved successfully, otherwise false.
     */
    boolean approveProposal(int id);

    /**
     * Returns proposal`s customer id.
     * @param id The proposal id.
     * @return customer id.
     */
    int getCustomerIdByProposalId(int id);

}
