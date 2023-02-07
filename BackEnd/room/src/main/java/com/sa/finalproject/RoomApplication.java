package com.sa.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
public class RoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/").allowedOrigins("http://localhost:3000").allowedMethods("DELETE", "GET", "POST",
						"PUT");
				// registry.addMapping("/")
				// .allowedOrigins("http://localhost:3000")
				// .allowedMethods("PUT", "DELETE")
				// .allowedHeaders("header1", "header2", "header3")
				// .exposedHeaders("header1", "header2")
				// .allowCredentials(false).maxAge(3600);
			}
		};
	}

}
