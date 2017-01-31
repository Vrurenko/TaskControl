package com.task.model;

import java.util.Date;

/**
 * Represents a sprint entity, providing access to the sprint's
 * id, project, name, complete, startDate, endDate.
 */
public class Sprint {
    private int id;
    private String project;
    private String name;
    private Date startDate;
    private Date endDate;
    private boolean complete;

    public Sprint() {
    }

    public Sprint(int id, String project, String name, Date startDate, Date endDate, boolean complete) {
        this.id = id;
        this.project = project;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", project='" + project + '\'' +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", complete=" + complete +
                '}';
    }
}
