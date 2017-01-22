package com.task.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class Test {

    @NotNull
    private String name;
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Past
    private Date date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
