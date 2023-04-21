package com.web.roomiez.user;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();

    public User findByUserID(int userID) throws ChangeSetPersister.NotFoundException;

    public User findByUsername(String username);

    //method to create new user
    public String register(User user);


    //update user parameters
    public void deleteGroupID(int groupID);


    public int IDbyUser(String username);


}
