package com.web.roomiez.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

//Handles Get/post
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //gets the main page for the user
    @GetMapping("/{userID}")
    public ResponseEntity<User> getUser(@PathVariable("userID") int userID) throws ChangeSetPersister.NotFoundException{
        User user = userService.findByUserID(userID);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //registration -> adding a new user to the database
    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User user){
        User newUser = userService.saveUser(user);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/all")
    public String admin() {
        return "this is All page";
    }
    //Method for group page?
}
