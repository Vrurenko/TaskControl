package com.task.dao.contracts;

import com.task.model.User;

import java.util.ArrayList;

public interface IUserDAO {

    ArrayList<User> getUsersByRole(String role);

    ArrayList<String> getEmployeeLogins();

    ArrayList<String> getEmployeeByQualification(int qualification);

    User getUserByLogin(String login);

    String getUsernameById(int id);

    String getLoginById(int id);

    int getUserIdByLogin(String login);

    boolean setManager(String login);

    boolean demoteManager(String login);

    boolean addUser(User user);

    boolean checkUserByLogin(String login);

}
