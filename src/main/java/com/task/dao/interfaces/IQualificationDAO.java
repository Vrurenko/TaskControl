package com.task.dao.interfaces;

import java.util.ArrayList;

public interface IQualificationDAO {

    public ArrayList<String> getQualificationsList();

    public int getIdByQualification(String qualification);

    public String getQualificationById(int id);

}
