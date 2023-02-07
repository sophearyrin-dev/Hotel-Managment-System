package com.sa.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggingApplication.class, args);
	}

	@GetMapping("/")
	public String greeting() {
		return String.format(
				"Hello Logging service!");
	}
}
