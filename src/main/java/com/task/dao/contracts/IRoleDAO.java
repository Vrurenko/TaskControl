package com.task.dao.contracts;

import java.util.ArrayList;
/**
 * DAO for the role objects.
 */
public interface IRoleDAO {

    /**
     * Returns list of all roles.
     * @return list of roles.
     */
    ArrayList<String> getRolesList();

    /**
     * Returns list of allowed roles.
     * @return list of roles.
     */
    ArrayList<String> getAllowedRolesList();

    /**
     * Returns role id.
     * @param role The role.
     * @return role id.
     */
    int getIdByRole(String role);

    /**
     * Returns role by the id.
     * @param id The role id.
     * @return The role.
     */
    String getRoleById(int id);

}
