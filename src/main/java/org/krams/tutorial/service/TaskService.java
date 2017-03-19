package org.krams.tutorial.service;

import org.apache.log4j.Logger;
import org.krams.tutorial.oxm.TaskRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("taskService")
@Transactional
public class TaskService {

    protected static Logger logger = Logger.getLogger("service");

    private static Map<String, TaskRequest> tasks = new HashMap<String, TaskRequest>();

    public void doAdd(TaskRequest taskRequest) {
        // Search HashMap
        if ( tasks.get(taskRequest.getId()) != null ) {
            logger.error("Task is already added!");
            throw new RuntimeException("Task is already added!");
        }

        // Add to HashMap
        logger.debug("Task has been added");
        tasks.put(taskRequest.getId(), taskRequest);

    }

    public ArrayList<TaskRequest> getAll() {
        ArrayList<TaskRequest> taskList = new ArrayList<TaskRequest>();

        logger.debug("Retrieving all added tasks");
        for (Map.Entry<String, TaskRequest> entry: tasks.entrySet()) {
            taskList.add(entry.getValue());
            logger.debug(entry.getValue());
        }

        return taskList;
    }

}
