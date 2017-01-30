package com.task.service.contracts;

import com.task.model.Project;
import com.task.model.Proposal;

import java.util.ArrayList;

public interface IAdminService {
    /**
     *
     * @return
     */
    ArrayList<Proposal> getProposalList();

    /**
     *
     * @return
     */
    ArrayList<String> getEmployeeList();

    /**
     *
     * @param project
     * @return
     */
    boolean acceptProposal(Project project);
}
