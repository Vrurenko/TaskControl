package com.task;

import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

public class Test {

    public static void main(String[] args) {

        Date date = new Date();

        date = DateUtils.addHours(date, 3);

        System.out.println(date);

    }

}
