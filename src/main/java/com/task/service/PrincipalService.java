package com.task.service;

import com.task.dao.AbstractDAOFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("principalService")
public class PrincipalService {

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

    public int getCurrentPrincipalID(){
        return AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(getCurrentPrincipal());
    }

}
