package com.task.service.concrete;

import com.task.service.contracts.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
/**
 *
 */
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Override
    /**
     *
     */
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map<String, Object> userMap = userService.getUserByUsername(s);

        if (userMap == null) {
            throw new UsernameNotFoundException("User details not found with this username: " + s);
        }

        String username = (String) userMap.get("username");
        String password = (String) userMap.get("password");
        String role = (String) userMap.get("role");

        return new User(username, password, getAuthorities(role));
    }

    /**
     *
     * @param role
     * @return
     */
    private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>();
        authList.add(new SimpleGrantedAuthority(role));
        return authList;
    }
}
