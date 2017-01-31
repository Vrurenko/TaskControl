package com.task.dao.concrete;

import com.task.dao.ConnectionPool;
import com.task.dao.contracts.IReportDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ReportDAO implements IReportDAO{
    private static Logger logger = Logger.getLogger(ReportDAO.class);
    private ConnectionPool connectionPool = new ConnectionPool();

    @Override
    public HashMap<String, Float> getTaskReport() {
        HashMap<String, Float> map = new HashMap<String, Float>();
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT NAME, START_DATE + ESTIMATE - NVL(END_DATE,sysdate) AS DELAY FROM TASK");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                map.put(resultSet.getString("NAME"), resultSet.getFloat("DELAY"));
            }
            connectionPool.closeResultSet(resultSet);
            connectionPool.closeStatement(preparedStatement);
        } catch (SQLException ex) {
            logger.warn("SQLException in ReportDAO.getTaskReport");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return map;
    }
}
