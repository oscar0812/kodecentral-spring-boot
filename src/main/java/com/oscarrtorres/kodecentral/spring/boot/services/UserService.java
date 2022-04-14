package com.oscarrtorres.kodecentral.spring.boot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscarrtorres.kodecentral.spring.boot.exceptions.AlreadyExistsException;
import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.models.response.CustomHttpResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        for(User user: userList) {
            user.setPassword(null); // don't expose password
        }
        return userList;
    }

    public User save(User user) throws AlreadyExistsException {
        userRepository.findByEmail(user.getEmail()).ifPresent(p -> {throw new AlreadyExistsException("Email already exists");});
        userRepository.findByUsername(user.getUsername()).ifPresent(p -> {throw new AlreadyExistsException("Username already exists");});
        User savedUser = userRepository.save(user);
        savedUser.setPassword(null); // don't expose password
        return savedUser;
    }
}
