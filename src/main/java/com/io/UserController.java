package com.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Users> signup(@RequestBody Users user) {
        Users savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody Users user) {
        return userService.findByUsername(user.getUsername())
                .map(existingUser -> {
                    if (existingUser.getPassword().equals(user.getPassword())) {
                        return ResponseEntity.ok("Sign-in successful");
                    } else {
                        return ResponseEntity.status(401).body("Invalid password");
                    }
                })
                .orElseGet(() -> ResponseEntity.status(404).body("User not found"));
    }
}
