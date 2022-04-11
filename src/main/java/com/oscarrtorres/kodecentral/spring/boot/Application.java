package com.oscarrtorres.kodecentral.spring.boot;

import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	@Bean
	public Authentication currentUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Bean
	public Slugify slugify() {
		return new Slugify();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
