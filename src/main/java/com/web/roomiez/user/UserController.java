package com.web.roomiez.user;

import com.web.roomiez.Task.Task;
import com.web.roomiez.Task.TaskService;
import com.web.roomiez.email.ConfirmationToken;
import com.web.roomiez.email.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Handles Get/post
@RestController
@RequestMapping("/user")
@CrossOrigin
//TODO: add @CrossOrigins annotation to all controllers to interact with react app
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private TaskService taskService;

    //We want to see the main tasks for a particular user
    @GetMapping("/{userID}")
    public ResponseEntity<User> getUser(@PathVariable("userID") int userID) throws ChangeSetPersister.NotFoundException{
        User user = userService.findByUserID(userID);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //registration -> adding a new user to the database
    //TODO: Need to avoid auth for this path
    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User user){
        if (userService.findByUsername(user.getUsername()) != null){
            //User already exists
            throw new IllegalStateException("Email already exists");
            //return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.register(user);
        User newUser = userService.saveUser(user);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    //TODO: get all the tasks that the user needs
//    @GetMapping("/{userID}/tasks")
//    public ResponseEntity<User> userTasks(@PathVariable int userID) throws ChangeSetPersister.NotFoundException {
//        User user = userService.findByUserID(userID);
//        if (user == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        //TODO: taskService method to return all tasks associated with user
//        //ArrayList<Task> tasks =
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return confirmationTokenService.confirmToken(token);
    }

    @GetMapping("/getId/{username}")
    public ResponseEntity<Integer> IDbyUser(@PathVariable String username){
        Integer id = userService.IDbyUser(username);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //TODO: mapping for login page?


    //TODO: update user information -> name
    @PutMapping("/{userID}/setGroup")
    public ResponseEntity<User> update(@PathVariable int userID, @RequestParam int groupID) throws ChangeSetPersister.NotFoundException {
        User user = userService.findByUserID(userID);
        user.setGroupID(groupID);
        userService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Prints all users
    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/update")
    public List<User> update(@RequestParam int groupID){
        userService.deleteGroupID(groupID);
        return userService.getAllUsers();
    }

    @PostMapping("/send/{email}")
    public ResponseEntity<User> sendNudge(@PathVariable String email){
        User user = userService.findByUsername(email);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
