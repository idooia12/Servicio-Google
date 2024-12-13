/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */
package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;


@Configuration
public class DataInitializer {

	private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
	
    @Bean
    @Transactional
    CommandLineRunner initData(UserRepository userRepository) {
		return args -> {
			
			// Create some users
			User manu = new User("Manu@deusto.es", "pass");
			User carlos = new User("Carlos@deusto.es", "paso");
			
			// Save users
			userRepository.saveAll(List.of(manu, carlos));			
			logger.info("Users saved!");
		};
	}
}