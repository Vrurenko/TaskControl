package com.task.controller;

import com.task.dao.AbstractDAOFactory;
import com.task.model.Proposal;
import com.task.model.User;
import com.task.service.CustomerService;
import com.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String getProposals(ModelMap model) {
        model.addAttribute("proposal", new Proposal());
        model.addAttribute("list",customerService.getCustomerProposals());
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
