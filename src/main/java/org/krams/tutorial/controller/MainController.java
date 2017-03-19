package org.krams.tutorial.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.krams.tutorial.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {

	private static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="taskService")
	private TaskService taskService;
	
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String getTaskPage(Model model) {
    	logger.debug("Received request to show task page");
    
    	model.addAttribute("subscriptions",  taskService.getAll());
    	
    	return "tasks";
	}
    
}
