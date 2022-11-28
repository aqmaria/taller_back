package com.evertcode.phonebook.config.security;

import com.evertcode.phonebook.config.properties.ApplicationProperties;
import com.evertcode.phonebook.config.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    SecurityProperties securityProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if(!username.equals(securityProperties.getUsername())){
            throw new UsernameNotFoundException("user not found");
        }
        return new User(securityProperties.getUsername(),
                "{noop}".concat(securityProperties.getPassword()),
                new ArrayList<>());
    }
}
