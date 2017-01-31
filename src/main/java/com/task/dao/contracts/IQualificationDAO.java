package com.task.dao.contracts;

import java.util.ArrayList;
/**
 * DAO for the qualification objects.
 */
public interface IQualificationDAO {

    /**
     * Returns list of qualifications.
     * @return list of qualifications.
     */
    ArrayList<String> getQualificationsList();

    /**
     * Returns qualification id.
     * @param qualification The qualification.
     * @return qualification id.
     */
    int getIdByQualification(String qualification);

    /**
     * Return qualification bu id.
     * @param id qualification id.
     * @return qualification.
     */
    String getQualificationById(int id);

}
