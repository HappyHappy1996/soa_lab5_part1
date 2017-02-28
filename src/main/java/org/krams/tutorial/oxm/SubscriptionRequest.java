package org.krams.tutorial.oxm;

/**
 * A simple POJO containing a subscription request. 
 * Castor will map the incoming xml request to this class. 
 *
 */
public class SubscriptionRequest {
	
	private String id;
	private String name;
	private String email;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
