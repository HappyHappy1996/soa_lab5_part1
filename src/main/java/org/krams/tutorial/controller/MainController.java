package org.krams.tutorial.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.krams.tutorial.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {

	private static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public String getDepartmentsPage(Model model) {
    	logger.debug("Received request to show departments page");
    
    	model.addAttribute("subscriptions",  departmentService.getAll());
    	
    	return "departments";
	}
    
}
