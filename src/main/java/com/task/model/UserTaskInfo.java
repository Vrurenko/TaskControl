package com.task.model;

public class UserTaskInfo {
    private String userLogin;
    private String project;
    private String sprint;
    private String task;
    private boolean completed;

    public UserTaskInfo() {
    }

    public UserTaskInfo(String userLogin, String project, String sprint, String task, boolean completed) {
        this.userLogin = userLogin;
        this.project = project;
        this.sprint = sprint;
        this.task = task;
        this.completed = completed;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "UserTaskInfo{" +
                "userLogin='" + userLogin + '\'' +
                ", project='" + project + '\'' +
                ", sprint='" + sprint + '\'' +
                ", task='" + task + '\'' +
                ", completed=" + completed +
                '}';
    }
}
