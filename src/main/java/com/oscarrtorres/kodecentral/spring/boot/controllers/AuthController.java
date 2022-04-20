package com.oscarrtorres.kodecentral.spring.boot.controllers;

import com.oscarrtorres.kodecentral.spring.boot.dtos.LoginUserDTO;
import com.oscarrtorres.kodecentral.spring.boot.models.response.AuthenticationResponse;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.models.response.UserModelResponse;
import com.oscarrtorres.kodecentral.spring.boot.security.JwtUtil;
import com.oscarrtorres.kodecentral.spring.boot.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@CrossOrigin(origins = "${angular.cors.url}")
@RestController
@RequestMapping("${spring.data.rest.basePath}/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginUserDTO loginUserDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUserDTO.getUsername(), loginUserDTO.getPassword())
            );
        } catch (BadCredentialsException be) {
            throw new BadCredentialsException("Incorrect username or password", be);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginUserDTO.getUsername());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        final Date expiresAt = jwtUtil.extractExpiration(jwtToken);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken, expiresAt, loginUserDTO.getUsername()));
    }

    @PostMapping("/register")
    public UserModelResponse register(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userService.save(user);
    }
}
