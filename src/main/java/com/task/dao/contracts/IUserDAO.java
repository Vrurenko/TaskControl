package com.task.dao.contracts;

import com.task.model.User;

import java.util.ArrayList;

public interface IUserDAO {

    public ArrayList<User> getUsersByRole(String role);

    public ArrayList<String> getEmployeeLogins();

    public ArrayList<String> getEmployeeByQualification(int qualification);

    public User getUserByLogin(String login);

    public String getUsernameById(int id);

    public String getLoginById(int id);

    public int getUserIdByLogin(String login);

    public boolean setManager(String login);

    public boolean demoteManager(String login);

    public boolean addUser(User user);

    public boolean checkUserByLogin(String login);

}
