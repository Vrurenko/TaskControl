package com.task.controller;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Sprint;
import com.task.model.Task;
import com.task.service.contracts.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    @Qualifier("managerService")
    IManagerService managerService;

    @RequestMapping(value = "/project-manager", method = RequestMethod.GET)
    public String viewManager(ModelMap modelMap) {
        modelMap.addAttribute("sprintList", managerService.getSprints());
        return "project-manager";
    }

    @RequestMapping(value = "/project-manager/sprint/get/all", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Sprint> getAllSprints() {
        return managerService.getSprints();
    }

    @RequestMapping(value = "/project-manager/sprint/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    List<Task> approve(@PathVariable int id) {
        return AbstractDAOFactory.getDAOFactory().getTaskDAO().getTasksBySprintID(id);
    }

    @RequestMapping(value = "/project-manager/sprint/add", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean addSprint(@ModelAttribute Sprint sprint, BindingResult bindingResult) {
        return !bindingResult.hasErrors() && managerService.createSprint(sprint);
    }

    @RequestMapping(value = "/project-manager/sprint/close", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean closeSprint() {
        return managerService.closeSprint();
    }


    @RequestMapping(value = "/project-manager/freeexecutors/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    ArrayList<String> getFreeEmployees(@PathVariable int id) {
        return managerService.getFreeFromTaskEmployees(id);
    }

    @RequestMapping(value = "/project-manager/executors/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    ArrayList<String> getTaskExecutors(@PathVariable int id) {
        return managerService.getTaskEmployees(id);
    }

    @RequestMapping(value = "/project-manager/task/{id}/emp/{login}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean setTaskExecutor(@PathVariable int id, @PathVariable String login) {
        return managerService.setEmployeeToTask(id, login);
    }

    @RequestMapping(value = "/project-manager/task/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    Task getTask(@PathVariable int id) {
        return managerService.getTaskByID(id);
    }

    @RequestMapping(value = "/project-manager/task/qualifications", method = RequestMethod.POST)
    public
    @ResponseBody
    ArrayList<String> getQualifications() {
        return managerService.getQualifications();
    }

    @RequestMapping(value = "/project-manager/task/names", method = RequestMethod.POST)
    public
    @ResponseBody
    ArrayList<String> getTaskNames() {
        return managerService.getTaskNames();
    }

    @RequestMapping(value = "/project-manager/task/add", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean addTask(@ModelAttribute Task task, BindingResult bindingResult) {
        return !bindingResult.hasErrors() && managerService.addTask(task);
    }
}
