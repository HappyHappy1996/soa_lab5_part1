package org.krams.tutorial.oxm;

/**
 * A simple POJO containing a custom response. 
 * Castor will map the outgoing Java class response to an equivalent xml. 
 *
 */
public class SubscriptionResponse {

	private String code;
	private String description;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
