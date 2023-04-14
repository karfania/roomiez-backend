package com.web.roomiez.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {
    @GetMapping("/home")
    public String home(){
        return "This is home page";
    }
    @GetMapping("/admin")
    public String admin() {
        return "this is Admin page";
    }


    //TODO: mapping for login page
}

