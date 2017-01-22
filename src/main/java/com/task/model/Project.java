package com.task.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Project {
    private int id;
    @NotNull
    private String manager;
    private String customer;
    @NotEmpty
    private String name;
    private Date startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Future
    private Date endDate;
    private int proposalId;


    public Project() {
    }

    public Project(int id, String manager, String customer, String name, Date startDate, Date endDate) {
        this.id = id;
        this.manager = manager;
        this.customer = customer;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public int getProposalId() {
        return proposalId;
    }

    public void setProposalId(int proposalId) {
        this.proposalId = proposalId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "endDate=" + endDate +
                ", startDate=" + startDate +
                ", name='" + name + '\'' +
                ", customer='" + customer + '\'' +
                ", manager='" + manager + '\'' +
                ", id=" + id +
                '}';
    }
}
