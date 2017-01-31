package com.task.model;

import java.sql.Date;

/**
 * Represents a task entity, providing access to the task's
 * id, primaryTask, name, estimate, startDate, endDate,
 * complete, qualification, sprint, confirm.
 */
public class Task {
    private int id;
    private String primaryTask;
    private String name;
    private int estimate;
    private Date startDate;
    private Date endDate;
    private boolean complete;
    private String qualification;
    private String sprint;
    private boolean confirm;

    public Task() {
    }

    public Task(int id, String primaryTask, String name, int estimate, Date startDate, Date endDate, boolean complete, String qualification, String sprint, boolean confirm) {
        this.id = id;
        this.primaryTask = primaryTask;
        this.name = name;
        this.estimate = estimate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.complete = complete;
        this.qualification = qualification;
        this.sprint = sprint;
        this.confirm = confirm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimaryTask() {
        return primaryTask;
    }

    public void setPrimaryTask(String primaryTask) {
        this.primaryTask = primaryTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
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

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", primaryTask='" + primaryTask + '\'' +
                ", name='" + name + '\'' +
                ", estimate=" + estimate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", complete=" + complete +
                ", qualification='" + qualification + '\'' +
                ", sprint='" + sprint + '\'' +
                ", confirm=" + confirm +
                '}';
    }
}
