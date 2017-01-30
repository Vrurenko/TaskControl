package com.task.dao.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.dao.ConnectionPool;
import com.task.dao.contracts.IProposalDAO;
import com.task.model.Proposal;

import java.sql.*;
import java.util.ArrayList;

public class ProposalDAO implements IProposalDAO {
    ConnectionPool connectionPool = new ConnectionPool();

    public boolean createProposal(Proposal proposal) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PROPOSAL(NAME, DESCRIPTION, CUSTOMER) VALUES (?, ?, ?)");
            preparedStatement.setString(1, proposal.getName());
            preparedStatement.setString(2, proposal.getDescription());
            preparedStatement.setInt(3, AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(proposal.getCustomer()));
            if (preparedStatement.executeUpdate() > 0){
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("SQLException in ProposalDAO.createProposal");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Proposal> getProposalsByCustomer(String customer) {
        ArrayList<Proposal> list = new ArrayList<Proposal>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM PROPOSAL WHERE CUSTOMER = ? AND APPROVED = 0 ");
            preparedStatement.setInt(1, AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(customer));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Proposal proposal = new Proposal();
                proposal.setName(resultSet.getString("name"));
                proposal.setDescription(resultSet.getString("description"));
                proposal.setApproved(resultSet.getBoolean("approved"));
                list.add(proposal);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in ProposalDAO.getProposalsByCustomer");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public ArrayList<Proposal> getProposalList() {
        ArrayList<Proposal> list = new ArrayList<Proposal>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PROPOSAL WHERE APPROVED = 0 ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Proposal proposal = new Proposal();
                proposal.setId(resultSet.getInt("id"));
                proposal.setName(resultSet.getString("name"));
                proposal.setDescription(resultSet.getString("description"));
                proposal.setApproved(resultSet.getBoolean("approved"));
                String customer = AbstractDAOFactory.getDAOFactory().getUserDAO().getUsernameById(resultSet.getInt("customer"));
                proposal.setCustomer(customer);
                list.add(proposal);
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            System.out.println("SQLException in ProposalDAO.getProposalList");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return list;
    }

    @Override
    public boolean approveProposal(int id) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PROPOSAL SET APPROVED = 1 WHERE ID = ?");
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0){
                result = true;
            }
        } catch (SQLException e) {
            System.out.println("SQLException in ProposalDAO.approveProposal");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return result;
    }

    @Override
    public int getCustomerIdByProposalId(int id) {
        Connection connection = null;
        int customerId = 0;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT CUSTOMER FROM PROPOSAL WHERE ID = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                customerId = resultSet.getInt("customer");
            }
        } catch (SQLException e) {
            System.out.println("SQLException in ProposalDAO.getCustomerIdByProposalId");
        } finally {
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return customerId;
    }

}
