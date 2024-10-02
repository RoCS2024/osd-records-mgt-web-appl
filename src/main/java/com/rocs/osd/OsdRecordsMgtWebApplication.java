package com.rocs.osd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Main class for the OSD Records Management Application.
 * Serves as the entry point to start the Spring Boot application
 */
@SpringBootApplication
public class OsdRecordsMgtWebApplication {
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(OsdRecordsMgtWebApplication.class, args);
	}

}
