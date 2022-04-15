package com.oscarrtorres.kodecentral.spring.boot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscarrtorres.kodecentral.spring.boot.exceptions.AlreadyExistsException;
import com.oscarrtorres.kodecentral.spring.boot.models.Comment;
import com.oscarrtorres.kodecentral.spring.boot.models.Library;
import com.oscarrtorres.kodecentral.spring.boot.models.Post;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.models.response.CustomHttpResponse;
import com.oscarrtorres.kodecentral.spring.boot.models.response.UserModelResponse;
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

    public List<UserModelResponse> findAll() {
        return userRepository.findAll().stream().map(UserModelResponse::new).toList();
    }

    public UserModelResponse save(User user) throws AlreadyExistsException {
        userRepository.findByEmail(user.getEmail()).ifPresent(p -> {throw new AlreadyExistsException("Email already exists");});
        userRepository.findByUsername(user.getUsername()).ifPresent(p -> {throw new AlreadyExistsException("Username already exists");});
        User savedUser = userRepository.save(user);
        return new UserModelResponse(savedUser);
    }
}
