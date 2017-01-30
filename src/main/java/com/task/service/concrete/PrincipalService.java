package com.task.service.concrete;

import com.task.dao.AbstractDAOFactory;
import com.task.service.contracts.IPrincipalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("principalService")
/**
 *
 */
public class PrincipalService implements IPrincipalService {

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public int getCurrentPrincipalID(){
        return AbstractDAOFactory.getDAOFactory().getUserDAO().getUserIdByLogin(getCurrentPrincipal());
    }

}
