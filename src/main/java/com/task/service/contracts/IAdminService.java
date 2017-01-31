package com.task.service.contracts;

import com.task.model.Project;
import com.task.model.Proposal;

import java.util.ArrayList;

/**
 * Provides the interface for service to perform administrator duties.
 */
public interface IAdminService {
    /**
     * Returns all proposals of all customers
     * @return all customers proposals list
     */
    ArrayList<Proposal> getProposalList();

    /**
     * Returns a list of all employees
     * @return all employees list
     */
    ArrayList<String> getEmployeeList();

    /**
     * Creates new project based
     * @param project the project
     * @return true if project was created successfully, otherwise false.
     */
    boolean acceptProposal(Project project);
}
