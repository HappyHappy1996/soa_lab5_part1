package org.krams.tutorial.endpoint;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.SubscriptionRequest;
import org.krams.tutorial.oxm.SubscriptionResponse;
import org.krams.tutorial.service.SubscriptionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Handles subscription requests. 
 * <p>
 *<pre>
 * @Endpoint: similar to Spring MVC's @Controller. 
 * @PayloadRoot: similar to Spring MVC's @RequestMapping</pre>
 */
@Endpoint
public class SubscriptionEndpoint {

	protected static Logger logger = Logger.getLogger("endpoint");

	@Resource(name="subscriptionService")
	private SubscriptionService subscriptionService;
	
	// The namespace of both request and response as declared in the XSD file
	public static final String NAMESPACE_URI = "http://krams915.blogspot.com/ws/schema/oss";

	// The local name of the expected request.
	public static final String REQUEST_LOCAL_NAME = "subscriptionRequest";

	/**
	 * Processes subscriptions. Actual processing is delegated to a service
	 * <p>
	 * In order for this method to be triggered, the localPart and namespace must
	 * match from the incoming XML request. This is exactly similar to Spring MVC.
	 * <p>
	 * In Spring MVC, we declare:
	 * <pre>@RequestMapping(value = "/admin", method = RequestMethod.GET)</pre>
	 * <p>
	 * In Spring WS, we declare:
	 * <pre>@PayloadRoot(localPart = "myRequest", namespace = "http://my.domain.com...")</pre>
	 */
	@PayloadRoot(localPart = REQUEST_LOCAL_NAME, namespace = NAMESPACE_URI)
	@ResponsePayload
	public SubscriptionResponse processSubscription( @RequestPayload SubscriptionRequest subscriptionRequest) {

		try {
			logger.debug("Received subscription request");
			
			try {
				logger.debug("Delegate to service");

				subscriptionService.doSubscribe(subscriptionRequest);
				
			}  catch (Exception e) {
				logger.error("Unable to subscribe");

				// Return response
				SubscriptionResponse response = new SubscriptionResponse();
				response.setCode("FAILURE");
				response.setDescription("Unable to subscribe");
				
				return response;
			}

		} catch (Exception e) {
			logger.error("Problem with subscription request");

			// Return response
			SubscriptionResponse response = new SubscriptionResponse();
			response.setCode("FAILURE");
			response.setDescription("Problem with subscription request");
			
			return response;
		}
		logger.debug("Success in subscribing");
		
		SubscriptionResponse response = new SubscriptionResponse();
		response.setCode("SUCCESS");
		response.setDescription("User has been subscribed");

		// Return response
		return response;
	}
}
