package com.task.dao.interfaces;

import java.util.ArrayList;

public interface IRoleDAO {

    public ArrayList<String> getRolesList();

    public int getIdByRole(String role);

    public String getRoleById(int id);

}
