package com.task.dao;

import com.task.dao.concrete.*;
import com.task.dao.contracts.*;

public class OracleDAOFactory extends AbstractDAOFactory {

    public IProjectDAO getProjectDAO() {
        return new ProjectDAO();
    }

    public IProposalDAO getProposalDAO() {
        return new ProposalDAO();
    }

    public IQualificationDAO getQualificationDAO() {
        return new QualificationDAO();
    }

    public IRoleDAO getRoleDAO() {
        return new RoleDAO();
    }

    public ISprintDAO getSprintDAO() {
        return new SprintDAO();
    }

    public ITaskDAO getTaskDAO() {
        return new TaskDAO();
    }

    public IUserDAO getUserDAO() {
        return new UserDAO();
    }

}
