package com.oscarrtorres.kodecentral.spring.boot;

import com.github.slugify.Slugify;
import com.oscarrtorres.kodecentral.spring.boot.models.User;
import com.oscarrtorres.kodecentral.spring.boot.security.MyUserPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Application {

	@Bean
	public AuditorAware<User> auditorAware(){

		return () -> {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authentication == null || !authentication.isAuthenticated()) {
				return Optional.empty();
			}

			return Optional.ofNullable(((MyUserPrincipal) authentication.getPrincipal()).getUser());
		};
	}

	@Bean
	public Slugify slugify() {
		return new Slugify();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
