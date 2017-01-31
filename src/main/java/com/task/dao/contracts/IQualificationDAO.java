package com.task.dao.contracts;

import java.util.ArrayList;

public interface IQualificationDAO {

    ArrayList<String> getQualificationsList();

    int getIdByQualification(String qualification);

    String getQualificationById(int id);

}
