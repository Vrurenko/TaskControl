package com.task.service.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.service.contracts.IPrincipalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("principalService")
/**
 * Provides the implementation for service to perform principal duties.
 */
public class PrincipalService implements IPrincipalService {

    @Override
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

    @Override
    public int getCurrentPrincipalID(){
        return AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(getCurrentPrincipal());
    }

}
