package com.oscarrtorres.kodecentral.spring.boot.services;

import com.oscarrtorres.kodecentral.spring.boot.exceptions.AlreadyExistsException;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.models.response.UserModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.repositories.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MailClient mailClient;
    private final StringGeneratorService stringGeneratorService;


    public UserService(UserRepository userRepository, MailClient mailClient, @Lazy StringGeneratorService stringGeneratorService) {
        this.userRepository = userRepository;
        this.mailClient = mailClient;
        this.stringGeneratorService = stringGeneratorService;
    }


    public List<UserModelResponse> findAll() {
        return userRepository.findAll().stream().map(UserModelResponse::new).toList();
    }

    public UserModelResponse findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new UserModelResponse(user.get());
    }

    public UserModelResponse save(User user) throws AlreadyExistsException {
        boolean newUser = user.getId() == null || user.getId() == 0;
        if(newUser) {
            userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
                throw new AlreadyExistsException("Email already exists");
            });
            userRepository.findByUsername(user.getUsername()).ifPresent(u -> {
                throw new AlreadyExistsException("Username already exists");
            });

            user.setConfirmationKey(stringGeneratorService.generateRandomAlphaNumericString(32));
        }

        User savedUser = userRepository.save(user);
        if(newUser) {
            mailClient.sendConfirmationEmail(savedUser);
        }
        return new UserModelResponse(user);
    }
}
