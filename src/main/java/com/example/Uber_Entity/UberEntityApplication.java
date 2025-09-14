package com.example.Uber_Entity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UberEntityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberEntityApplication.class, args);
	}

}
