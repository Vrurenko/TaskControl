package com.task.dao.contracts;

import java.util.ArrayList;

public interface IRoleDAO {

    ArrayList<String> getRolesList();

    ArrayList<String> getAllowedRolesList();

    int getIdByRole(String role);

    String getRoleById(int id);

}
