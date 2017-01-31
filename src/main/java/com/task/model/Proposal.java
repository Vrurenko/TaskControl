package com.task.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents a proposal entity, providing access to the proposal's
 * id, description, customer, name, approved.
 */
public class Proposal {
    private int id;
    private String description;
    @NotEmpty
    private String name;
    @NotEmpty
    private String customer;
    private boolean approved;

    public Proposal() {
    }

    public Proposal(int id, String description, String name, String customer, boolean approved) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.customer = customer;
        this.approved = approved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
