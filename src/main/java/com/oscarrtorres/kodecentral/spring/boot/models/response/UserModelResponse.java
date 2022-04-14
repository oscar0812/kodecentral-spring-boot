package com.oscarrtorres.kodecentral.spring.boot.models.response;

import com.oscarrtorres.kodecentral.spring.boot.models.User;
import lombok.*;

import java.time.Instant;

@ToString
@Getter
@Setter
public class UserModelResponse {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String profilePicture;
    private String bio;
    private Boolean isSuper;
    private String confirmationKey;
    private String resetKey;
    private Instant createdAt;
    private Instant updatedAt;

    public UserModelResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profilePicture = user.getProfilePicture();
        this.bio = user.getBio();
        this.isSuper = user.getIsSuper();
        this.confirmationKey = user.getConfirmationKey();
        this.resetKey = user.getResetKey();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}