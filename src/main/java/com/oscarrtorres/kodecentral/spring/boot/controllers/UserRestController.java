package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.exceptions.AuthorizationException;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.models.response.UserModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.services.FileUploadService;
import com.oscarrtorres.kodecentral.spring.boot.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.sasl.AuthenticationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "${angular.cors.url}")
@RestController
@RequestMapping("${spring.data.rest.basePath}/user")
public class UserRestController {
    private final UserService userService;
    private final FileUploadService fileUploadService;

    public UserRestController(UserService userService, FileUploadService fileUploadService) {
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping
    public List<UserModelResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/username")
    public UserModelResponse findByUsername(@RequestParam("username") String username) {
        return userService.findByUsername(username);
    }

    @PatchMapping
    public UserModelResponse update(@RequestBody User user) throws Exception{
        User currentUser = userService.getCurrent();
        if(currentUser == null)
            throw new AuthorizationException();

        currentUser.setBio(user.getBio());
        return userService.save(currentUser);
    }

    @PostMapping("/uploadProfilePicture")
    public UserModelResponse uploadImage(@RequestParam("imageFile")MultipartFile file) throws IOException {
        User user = userService.getCurrent();

        if(user == null) {
            throw new AuthorizationException();
        } else if (!file.isEmpty()){
            String uploadDir = "uploads/pfp";

            user.setProfilePicture(uploadDir + "/" + user.getUsername() + ".png");
            UserModelResponse savedUser = userService.save(user);

            fileUploadService.saveFile(uploadDir, user.getUsername()+".png", file);

            return savedUser;
        } else {
            throw new FileNotFoundException(file.getName());
        }
    }
}
