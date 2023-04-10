package com.web.roomiez.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
   private UserRepository userRepository;

   //Override to implement interface
    //Saves user in repository
   @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void register(int ID, int groupID, String name, String username, String password) {

    }

    @Override
    public void update(User user) {

    }
    @Override
    public User findByUserID(int userID) throws ChangeSetPersister.NotFoundException {
        Optional<User> optionalUser = userRepository.findById(userID);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw new ChangeSetPersister.NotFoundException();
        }
    }

}

