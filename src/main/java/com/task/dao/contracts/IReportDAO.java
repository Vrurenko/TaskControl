package com.task.dao.contracts;

import com.task.model.UserTaskInfo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * DAO for the reporting.
 */
public interface IReportDAO {

    /**
     * Returns the map of task names and delay.
     * @return map of tasks and delays.
     */
    HashMap<String, Float> getTaskReport();

    /**
     * Returns the users task info list
     * @return list of UserTaskInfo
     */
    ArrayList<UserTaskInfo> getUserTaskInfo();
}
