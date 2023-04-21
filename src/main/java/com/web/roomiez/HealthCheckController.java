package com.web.roomiez;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TO PASS BEANSTALK HEALTH CHECK
@RestController
@RequestMapping("/")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity.BodyBuilder healthCheck() {
        return ResponseEntity.ok();
    }
}