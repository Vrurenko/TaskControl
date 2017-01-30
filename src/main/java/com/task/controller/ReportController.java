package com.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
        return new ModelAndView("excelView");
    }


    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String excelLoad(ModelMap model) {
        model.addAttribute("load", true);
        return "report";
    }
}
