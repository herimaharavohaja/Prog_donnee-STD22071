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

	@Bean
	public CommandLineRunner commandLineRunner(PatrimoineService patrimoineService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				com.fresh.coding.patrimoineapi.model.Patrimoine foundPatrimoine = patrimoineService.findByName("Marie");
				if (foundPatrimoine != null) {
					System.out.println("Found Patrimoine: " + foundPatrimoine);
				} else {
					System.out.println("No Patrimoine found with the name 'Marie'.");
				}
			}
		};
	}
}
