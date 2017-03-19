package org.krams.tutorial.oxm;

/**
 * Created by happy on 3/8/17.
 */
public class TaskRequest {

    private String id;
    private String taskText;
    private String reportType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
