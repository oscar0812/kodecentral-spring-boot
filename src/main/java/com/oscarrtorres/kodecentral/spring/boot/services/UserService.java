package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.exceptions.AlreadyExistsException;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.models.response.UserResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream().map(UserResponse::new).toList();
    }

    public UserResponse save(User user) throws AlreadyExistsException {
        userRepository.findByEmail(user.getEmail()).ifPresent(p -> {throw new AlreadyExistsException("Email already exists");});
        userRepository.findByUsername(user.getUsername()).ifPresent(p -> {throw new AlreadyExistsException("Username already exists");});
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser);
    }
}
