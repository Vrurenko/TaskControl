package com.task.listener;

import com.task.dao.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class WebApplicationListener implements ServletContextListener{

    // Public constructor is required by servlet spec
    public WebApplicationListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            sce.getServletContext().setAttribute("connectionPool", connectionPool); // Application scope attribute
        } catch (Exception e) {
            throw new RuntimeException("Can't create connection pool", e);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("connectionPool", null);
    }

}
