package com.task.controller;

import com.task.model.Proposal;
import com.task.service.contracts.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CustomerController {

    @Autowired
    @Qualifier("customerService")
    ICustomerService customerService;


    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String getProposals(ModelMap model) {
        boolean hasProject = customerService.hasProject();
        model.addAttribute("hasProject", hasProject);
        if (hasProject) {
            model.addAttribute("sprintList", customerService.getSprints());
        } else {
            model.addAttribute("proposal", new Proposal());
            model.addAttribute("list", customerService.getCustomerProposals());
        }
        return "customer";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveProposal(@Valid Proposal proposal,
                               BindingResult bindingResult,
                               ModelMap model) {
        customerService.offerProposal(proposal);
        return "redirect:/customer";
    }
}
