package com.task.dao.implementations;

import com.task.dao.ConnectionPool;
import com.task.dao.interfaces.ITicketDAO;

public class TicketDAO implements ITicketDAO {
    ConnectionPool connectionPool = new ConnectionPool();
}
