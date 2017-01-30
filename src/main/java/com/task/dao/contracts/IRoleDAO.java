package com.task.dao.contracts;

import java.util.ArrayList;

public interface IRoleDAO {

    public ArrayList<String> getRolesList();

    public ArrayList<String> getAllowedRolesList();

    public int getIdByRole(String role);

    public String getRoleById(int id);

}
