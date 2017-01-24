package com.task.controller;

import com.task.dao.AbstractDAOFactory;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcomeTest(ModelMap model) {
        return "welcome";
    }

    @RequestMapping(value = "/excel", method = RequestMethod.GET)
    public String excelTest(ModelMap model) {
        model.addAttribute("load", false);
        return "excel";
    }

    @RequestMapping(value = "/excel/load", method = RequestMethod.GET)
    public String excelLoad(ModelMap model) {
        model.addAttribute("load", true);
        return "excel";
    }

}
