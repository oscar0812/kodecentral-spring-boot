package com.oscarrtorres.kodecentral.spring.boot.security;

import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new MyUserPrincipal(user.get());
    }
}
