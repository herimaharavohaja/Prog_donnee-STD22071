package com.backend.patrimoine;

import com.backend.patrimoine.service.PatrimoineService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatrimoineApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatrimoineApplication.class, args);
	}

}
