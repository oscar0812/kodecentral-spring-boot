package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.exceptions.AlreadyExistsException;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) throws AlreadyExistsException {
        userRepository.findByEmail(user.getEmail()).ifPresent(p -> {throw new AlreadyExistsException("Email already exists");});
        userRepository.findByUsername(user.getUsername()).ifPresent(p -> {throw new AlreadyExistsException("Username already exists");});
        return userRepository.save(user);
    }
}
