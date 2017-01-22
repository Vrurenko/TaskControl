package com.task.service;

import com.task.dao.AbstractDAOFactory;
import com.task.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
@Transactional
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Map<String, Object> getUserByUsername(String username) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        User user = AbstractDAOFactory.getDAOFactory().getUserDAO().getUserByLogin(username);
        userMap.put("username", user.getLogin());
        userMap.put("password", user.getPassword());
        userMap.put("role", user.getRole());
        return userMap;
    }

    public boolean addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return AbstractDAOFactory.getDAOFactory().getUserDAO().addUser(user);
    }

    public ArrayList<String> getRoles(){
        return AbstractDAOFactory.getDAOFactory().getRoleDAO().getRolesList();
    }

    public ArrayList<String> getQualifications(){
        return AbstractDAOFactory.getDAOFactory().getQualificationDAO().getQualificationsList();
    }

    public String getCurrentPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
