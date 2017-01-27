package com.task.controller;

import com.task.model.User;
import javax.validation.Valid;

import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class RegistrationController{

    @Autowired
    @Qualifier("userService")
    UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", userService.getRoles());
        model.addAttribute("qualifications", userService.getQualifications());
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@Valid User user,
                           BindingResult bindingResult,
                           ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", userService.getRoles());
            model.addAttribute("qualifications", userService.getQualifications());
            return "registration";
        }
        System.out.println(user);
        model.addAttribute("answer", userService.addUser(user));
        return "redirect:/login";
    }

}
