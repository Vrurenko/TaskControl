package com.task.controller;

import com.task.dao.AbstractDAOFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {
    private static Logger logger = Logger.getLogger(ReportController.class);

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
        logger.info("Forwarded to excelView");
        return new ModelAndView("excelView");
    }


    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String excelLoad(ModelMap model) {
        System.out.println(AbstractDAOFactory.getDAOFactory().getReportDAO().getTaskReport());
        model.addAttribute("taskDelay", AbstractDAOFactory.getDAOFactory().getReportDAO().getTaskReport());
        logger.info("Forwarded to report");
        return "report";
    }
}
