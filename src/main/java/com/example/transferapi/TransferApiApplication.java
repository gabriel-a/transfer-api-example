package com.example.transferapi;

import com.example.transferapi.data.TransferItemsData;
import com.example.transferapi.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TransferApiApplication {

	private static final Logger log = LoggerFactory.getLogger(TransferApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TransferApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(TransferRepository transferRepository) {
		return (args) -> {
			log.info("Adding transfer items into the database.");
			TransferItemsData.GetData().forEach(transferRepository::save);
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				//This is for
				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000","http://localhost:4200");
			}
		};
	}
}
