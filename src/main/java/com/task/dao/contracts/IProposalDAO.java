package com.task.dao.contracts;

import com.task.model.Proposal;

import java.util.ArrayList;

public interface IProposalDAO {

    boolean createProposal(Proposal proposal);

    ArrayList<Proposal> getProposalsByCustomer(String customer);

    ArrayList<Proposal> getProposalList();

    boolean approveProposal(int id);

    int getCustomerIdByProposalId(int id);

}
