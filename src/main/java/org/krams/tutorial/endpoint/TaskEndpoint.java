package org.krams.tutorial.endpoint;

import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.TaskRequest;
import org.krams.tutorial.oxm.TaskResponse;
import org.krams.tutorial.service.TaskService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.Resource;

@Endpoint
public class TaskEndpoint {

    protected static Logger logger = Logger.getLogger("endpoint");

    @Resource(name="taskService")
    private TaskService taskService;

    // The namespace of both request and response as declared in the XSD file
    public static final String NAMESPACE_URI = "http://krams915.blogspot.com/ws/schema/oss";

    // The local name of the expected request.
    public static final String REQUEST_LOCAL_NAME = "taskRequest";

    @PayloadRoot(localPart = REQUEST_LOCAL_NAME, namespace = NAMESPACE_URI)
    @ResponsePayload
    public TaskResponse processTask(@RequestPayload TaskRequest taskRequest) {

        try {
            logger.debug("Received task request");

            try {
                logger.debug("Delegate to service");

                taskService.doAdd(taskRequest);

            }  catch (Exception e) {
                logger.error("Unable to add");

                // Return response
                TaskResponse response = new TaskResponse();
                response.setDescription("Unable to add");

                return response;
            }

        } catch (Exception e) {
            logger.error("Problem with task request");

            // Return response
            TaskResponse response = new TaskResponse();
            response.setDescription("Problem with task request");

            return response;
        }
        logger.debug("Success in processTask method");

        TaskResponse response = new TaskResponse();
        response.setDescription("Task has been added");

        // Return response
        return response;
    }

}
