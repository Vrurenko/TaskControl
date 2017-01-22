package com.task.controller;

import com.task.dao.AbstractDAOFactory;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomeTest(ModelMap model) {
        return "welcome";
    }

}
