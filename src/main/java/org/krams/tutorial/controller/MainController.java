package org.krams.tutorial.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.krams.tutorial.service.SubscriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admin can see
 * the adminpage, however.
 */
@Controller
@RequestMapping("/main")
public class MainController {

	private static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="subscriptionService")
	private SubscriptionService subscriptionService;
	
	/**
	 * Handles and retrieves subscribed users and
	 * display it in a JSP page
	 * 
	 * @return the name of the JSP page
	 */
    @RequestMapping(value = "/subscribers", method = RequestMethod.GET)
    public String getSubscribersPage(Model model) {
    	logger.debug("Received request to show subscribers page");
    
    	// Attach list of subscriptions to the Model
    	model.addAttribute("subscriptions",  subscriptionService.getAll());
    	
    	// This will resolve to /WEB-INF/jsp/subscribers.jsp
    	return "subscribers";
	}
    
}
