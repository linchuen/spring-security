package com.cooba.contoller;

import com.cooba.request.RegisterRequest;
import com.cooba.response.RegisterResponse;
import com.cooba.service.MapUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    MapUserDetailsService mapUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok( mapUserDetailsService.register(request));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/user")
    public ResponseEntity<String> forUser() {
        return ResponseEntity.ok("This is for user api!");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> forAdmin() {
        return ResponseEntity.ok("This is for admin api!");
    }

    @GetMapping("/create")
    public ResponseEntity<String> create() {
        return ResponseEntity.ok("This is create authority api!");
    }

    @GetMapping("/update")
    public ResponseEntity<String> update() {
        return ResponseEntity.ok("This is update authority api!");
    }

    @GetMapping("/special")
    public ResponseEntity<String> special() {
        return ResponseEntity.ok("This is special api!");
    }
}
