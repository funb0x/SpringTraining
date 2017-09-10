package com.epam.springtraining.security;

import beans.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class SpringTrainingUserService implements UserDetailsService {

    private UserService userService;

    public SpringTrainingUserService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        beans.models.User user = userService.getUserByEmail(username);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for(String role : user.getRoles().split(",")) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User with email'" + username + "' not found.");
    }
}