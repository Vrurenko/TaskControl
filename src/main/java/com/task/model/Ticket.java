package com.task.model;

import java.sql.Date;

public class Ticket {
    private int id;
    private Task task;
    private User employee;
    private String name;
    private String reason;
    private Date newEndDate;
    private boolean approved;

    public Ticket() {
    }

    public Ticket(int id, Task task, User employee, String name, String reason, Date newEndDate, boolean approved) {
        this.id = id;
        this.task = task;
        this.employee = employee;
        this.name = name;
        this.reason = reason;
        this.newEndDate = newEndDate;
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getEmployee() {
        return employee;
    }

    public void setEmployee(User employee) {
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getNewEndDate() {
        return newEndDate;
    }

    public void setNewEndDate(Date newEndDate) {
        this.newEndDate = newEndDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
