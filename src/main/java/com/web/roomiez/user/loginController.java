package com.web.roomiez.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;

@RestController
@CrossOrigin
public class loginController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationProvider authenticationProvider;

    @GetMapping("/home")
    public String home() {
        return "This is home page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "this is Admin page";
    }


//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestBody User user) {
//        //user has only username and password, find that logged user
//        User loggedUser = userService.findByUsername(user.getUsername());
//        authenticationProvider.authenticate()
//        return new ResponseEntity<>(loggedUser, HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user1) {
        String username = user1.getUsername();
        String password = user1.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}



