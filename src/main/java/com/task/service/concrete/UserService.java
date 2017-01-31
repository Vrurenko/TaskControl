package com.task.service.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.model.User;
import com.task.service.contracts.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
@Transactional
/**
 * Provides the implementation for service to perform user duties.
 */
public class UserService implements IUserService {
    private static final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, Object> getUserByUsername(String username) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        User user = AbstractDAOFactory.getDAOFactory().getUserDAO().getUserByLogin(username);
        userMap.put("username", user.getLogin());
        userMap.put("password", user.getPassword());
        userMap.put("role", user.getRole());
        return userMap;
    }

    @Override
    public boolean addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return AbstractDAOFactory.getDAOFactory().getUserDAO().addUser(user);
    }

    @Override
    public ArrayList<String> getRoles(){
        return AbstractDAOFactory.getDAOFactory().getRoleDAO().getAllowedRolesList();
    }

    @Override
    public ArrayList<String> getQualifications(){
        return AbstractDAOFactory.getDAOFactory().getQualificationDAO().getQualificationsList();
    }

}
