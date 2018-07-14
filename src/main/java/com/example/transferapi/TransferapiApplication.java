package com.example.transferapi;

import com.example.transferapi.data.TransferItemsData;
import com.example.transferapi.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransferapiApplication {

	private static final Logger log = LoggerFactory.getLogger(TransferapiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TransferapiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(TransferRepository transferRepository) {
		return (args) -> {
			log.info("Adding transfer items into the database.");
			TransferItemsData.GetData().forEach(transferItem -> {
				transferRepository.save(transferItem);
			});
		};
	}
}
