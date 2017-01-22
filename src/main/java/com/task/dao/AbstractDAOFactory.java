package com.task.dao;

import com.task.dao.interfaces.*;

public abstract class AbstractDAOFactory {

    public abstract IProjectDAO getProjectDAO();

    public abstract IProposalDAO getProposalDAO();

    public abstract IQualificationDAO getQualificationDAO();

    public abstract IRoleDAO getRoleDAO();

    public abstract ISprintDAO getSprintDAO();

    public abstract ITaskDAO getTaskDAO();

    public abstract ITicketDAO getTicketDAO();

    public abstract IUserDAO getUserDAO();

    public static AbstractDAOFactory getDAOFactory() {
        return new OracleDAOFactory();
    }

}
