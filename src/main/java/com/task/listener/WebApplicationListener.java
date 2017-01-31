package com.task.listener;

import com.task.dao.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * The interface implementation to receive event notifications about changes ServletContext lifecycle.
 */
@WebListener()
public class WebApplicationListener implements ServletContextListener{

    /**
     * Public constructor is required by servlet spec
     */
    public WebApplicationListener() {
    }

    /**
     * Receives a notification that a Web application initialization process starts.
     * @param sce - ServletContextEvent, containing ServletContext, which is initialized
     */
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            sce.getServletContext().setAttribute("connectionPool", connectionPool); // Application scope attribute
        } catch (Exception e) {
            throw new RuntimeException("Can't create connection pool", e);
        }
    }

    /**
     * Receives a notification that is going to be turned off.
     * @param sce - ServletContextEvent, containing ServletContext, which destroyed.
     */
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("connectionPool", null);
    }

}
