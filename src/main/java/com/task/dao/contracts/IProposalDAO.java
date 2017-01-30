package com.task.dao.contracts;

import com.task.model.Proposal;

import java.util.ArrayList;

public interface IProposalDAO {

    public boolean createProposal(Proposal proposal);

    public ArrayList<Proposal> getProposalsByCustomer(String customer);

    public ArrayList<Proposal> getProposalList();

    public boolean approveProposal(int id);

    public int getCustomerIdByProposalId(int id);

}
