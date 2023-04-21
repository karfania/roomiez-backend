package com.web.roomiez.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class loginController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home() {
        return "This is home page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "this is Admin page";
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        //user has only username and password, find that logged user
        User loggedUser = userService.findByUsername(user.getUsername());
        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
    }

}



