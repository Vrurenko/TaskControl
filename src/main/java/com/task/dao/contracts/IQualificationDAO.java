package com.task.dao.contracts;

import java.util.ArrayList;

public interface IQualificationDAO {

    public ArrayList<String> getQualificationsList();

    public int getIdByQualification(String qualification);

    public String getQualificationById(int id);

}
