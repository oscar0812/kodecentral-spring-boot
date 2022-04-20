package com.oscarrtorres.kodecentral.spring.boot.models.response;

import com.oscarrtorres.kodecentral.spring.boot.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AuthenticationResponse {
    private final String jwtToken;
    private final Date expiresAt;

    private final String username;
}
