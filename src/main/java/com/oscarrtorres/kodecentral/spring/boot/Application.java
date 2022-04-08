package com.oscarrtorres.kodecentral.spring.boot;

import com.github.slugify.Slugify;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	@Bean
	public Slugify slugify() {
		return new Slugify();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
