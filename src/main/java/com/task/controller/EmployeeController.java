package com.task.controller;

import com.task.model.Task;
import com.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String getAll(ModelMap model) {
        model.addAttribute("taskList", employeeService.getEmployeeTasks());
        return "employee";
    }

    @RequestMapping(value = "/employee/confirm/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean confirm(@PathVariable int id) {
        System.out.println("Confirmed");
        return employeeService.confirmTask(id);
    }

    @RequestMapping(value = "/employee/complete/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    boolean complete(@PathVariable int id) {
        System.out.println("Completed");
        return employeeService.completeTask(id);
    }

    @RequestMapping(value = "/employee/tasks", method = RequestMethod.POST)
    public
    @ResponseBody
    ArrayList<Task> tasks() {
        System.out.println("Returned");
        System.out.println(employeeService.getEmployeeTasks());
        return employeeService.getEmployeeTasks();
    }

}
