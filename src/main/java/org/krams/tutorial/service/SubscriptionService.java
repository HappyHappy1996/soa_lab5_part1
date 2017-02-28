package org.krams.tutorial.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.SubscriptionRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Handles subscription services like adding a new record or unsubscribing.
 * <p>
 * This is a Spring MVC @Service. Spring WS can communicate with this service.
 */
@Service("subscriptionService")
@Transactional
public class SubscriptionService {

	protected static Logger logger = Logger.getLogger("service");
	
	/**
	 *  Our in-memory list of subscriptions. Feel free to use a DAO or JDBC instead
	 */
	private Map<String, SubscriptionRequest> subscriptions = new HashMap<String, SubscriptionRequest>();
	
	/**
	 * Processes the actual subscriptions. Throws an error if the user already exists
	 */
	public void doSubscribe(SubscriptionRequest subscriptionRequest) {
		// Search HashMap
		if ( subscriptions.get(subscriptionRequest.getId()) != null ) {
			logger.error("User is already subscribed!");
			throw new RuntimeException("User is already subscribed!");
		}
		
		// Add to HashMap
		logger.debug("User has been subscribed");
		subscriptions.put(subscriptionRequest.getId(), subscriptionRequest);
		
	}
	
	/**
	 * Retrieves all subscriptions. Not the best way to retrieve however
	 * 
	 * @return list of subscribed users
	 */
	public ArrayList<SubscriptionRequest> getAll() {
		ArrayList<SubscriptionRequest> users = new ArrayList<SubscriptionRequest>();
		
		logger.debug("Retrieving all subscribed users");
		for (Map.Entry<String, SubscriptionRequest> entry: subscriptions.entrySet()) {
			users.add(entry.getValue());
			logger.debug(entry.getValue());
		}
		
		// Return all users
		return users;
	}
}
