package com.task.controller;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Project;
import com.task.service.contracts.IAdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class AdminController {
    private static Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    @Qualifier("adminService")
    IAdminService adminService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdmin(ModelMap model) {
        model.addAttribute("project", new Project());
        model.addAttribute("proposalList", adminService.getProposalList());
        model.addAttribute("employeeList", adminService.getEmployeeList());
        logger.info("Forwarded to admin");
        return "admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String saveProposal(@Valid Project project,
                               BindingResult bindingResult,
                               ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("proposalList", adminService.getProposalList());
            model.addAttribute("employeeList", adminService.getEmployeeList());
            logger.info("Forwarded to admin");
            return "admin";
        }
        int customerID = AbstractDAOFactory.getDAOFactory().getProposalDAO().getCustomerIdByProposalId(31);
        String customer = AbstractDAOFactory.getDAOFactory().getUserDAO().getLoginById(customerID);
        project.setCustomer(customer);
        adminService.acceptProposal(project);
        logger.info("Redirected to admin");
        return "redirect:/admin";
    }
}
