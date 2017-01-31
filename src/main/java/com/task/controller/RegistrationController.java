package com.task.controller;

import com.task.model.User;
import javax.validation.Valid;

import com.task.service.contracts.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController{
    private static Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    @Qualifier("userService")
    IUserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.getRoles());
        model.addAttribute("qualifications", userService.getQualifications());
        logger.info("Forwarded to registration");
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@Valid User user,
                           BindingResult bindingResult,
                           ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", userService.getRoles());
            model.addAttribute("qualifications", userService.getQualifications());
            logger.info("Forwarded to registration");
            return "registration";
        }
        model.addAttribute("answer", userService.addUser(user));
        logger.info("Redirected to registration");
        return "redirect:/login";
    }

}
