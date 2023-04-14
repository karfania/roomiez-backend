package com.web.roomiez.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username));
        }
        return new CustomUserDetails(user);
    }

    public int enableAppUser(String username){
        return userRepository.enableUser(username);
    }
}
