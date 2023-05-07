package com.practica.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.practica.core.config.CORSConfig;

@SpringBootApplication
public class PracticafApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticafApplication.class, args);
	}

	@Bean
	public CORSConfig corsConfig() {
		return new CORSConfig();
	}

}
