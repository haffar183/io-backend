package com.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    public Optional<Users> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
