package org.krams.tutorial.service;

import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.DepartmentRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("departmentService")
@Transactional
public class DepartmentService {

    protected static Logger logger = Logger.getLogger("service");

    private static Map<String, DepartmentRequest> departments = new HashMap<String, DepartmentRequest>();

    public void doAdd(DepartmentRequest departmentRequest) {
        // Search HashMap
        if ( departments.get(departmentRequest.getId()) != null ) {
            logger.error("Department is already added!");
            throw new RuntimeException("Department is already added!");
        }

        // Add to HashMap
        logger.debug("Department has been added");
        departments.put(departmentRequest.getId(), departmentRequest);

    }

    public ArrayList<DepartmentRequest> getAll() {
        ArrayList<DepartmentRequest> departmentList = new ArrayList<DepartmentRequest>();

        logger.debug("Retrieving all added departments");
        for (Map.Entry<String, DepartmentRequest> entry: departments.entrySet()) {
            departmentList.add(entry.getValue());
            logger.debug(entry.getValue());
        }

        return departmentList;
    }

}
