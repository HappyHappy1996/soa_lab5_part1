package org.krams.tutorial.endpoint;

import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.DepartmentRequest;
import org.krams.tutorial.oxm.DepartmentResponse;
import org.krams.tutorial.service.DepartmentService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.Resource;

@Endpoint
public class DepartmentEndpoint {

    protected static Logger logger = Logger.getLogger("endpoint");

    @Resource(name="departmentService")
    private DepartmentService departmentService;

    // The namespace of both request and response as declared in the XSD file
    public static final String NAMESPACE_URI = "http://krams915.blogspot.com/ws/schema/oss";

    // The local name of the expected request.
    public static final String REQUEST_LOCAL_NAME = "departmentRequest";

    @PayloadRoot(localPart = REQUEST_LOCAL_NAME, namespace = NAMESPACE_URI)
    @ResponsePayload
    public DepartmentResponse processDepartment(@RequestPayload DepartmentRequest departmentRequest) {

        try {
            logger.debug("Received department request");

            try {
                logger.debug("Delegate to service");

                departmentService.doAdd(departmentRequest);

            }  catch (Exception e) {
                logger.error("Unable to add");

                // Return response
                DepartmentResponse response = new DepartmentResponse();
                response.setDescription("Unable to add");

                return response;
            }

        } catch (Exception e) {
            logger.error("Problem with department request");

            // Return response
            DepartmentResponse response = new DepartmentResponse();
            response.setDescription("Problem with department request");

            return response;
        }
        logger.debug("Success in processDepartment method");

        DepartmentResponse response = new DepartmentResponse();
        response.setDescription("Department has been added");

        // Return response
        return response;
    }

}
