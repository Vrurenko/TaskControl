package com.task.dao.contracts;

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
}
