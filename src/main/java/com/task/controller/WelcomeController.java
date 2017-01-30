package com.task.controller;

import com.task.dao.AbstractDAOFactory;
import com.task.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {
    private static final Logger logger = Logger.getLogger(WelcomeController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomeTest(ModelMap model) {
        logger.warn("THERE");
        return "welcome";
    }


    @RequestMapping(value = "/excel/load", method = RequestMethod.GET)
    public String excelLoad(ModelMap model) {
        model.addAttribute("load", true);
        return "excel";
    }

}
