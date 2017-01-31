package com.task.dao;

import com.task.dao.contracts.*;

/**
 * Implementation of the Abstract Factory pattern.
 */
public abstract class AbstractDAOFactory {

    public abstract IProjectDAO getProjectDAO();

    public abstract IProposalDAO getProposalDAO();

    public abstract IQualificationDAO getQualificationDAO();

    public abstract IRoleDAO getRoleDAO();

    public abstract ISprintDAO getSprintDAO();

    public abstract ITaskDAO getTaskDAO();

    public abstract IUserDAO getUserDAO();

    public abstract IReportDAO getReportDAO();

    public static AbstractDAOFactory getDAOFactory() {
        return new OracleDAOFactory();
    }

}
