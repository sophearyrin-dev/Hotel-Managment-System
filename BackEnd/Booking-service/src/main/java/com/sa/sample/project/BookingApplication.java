package com.sa.sample.project;

import com.sa.sample.project.kafka.KafkaSenderService;
import com.sa.sample.project.kafka.KafkaSenderServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableKafka
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public KafkaSenderService kafkaSenderService() {
		return new KafkaSenderServiceImpl();
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:3000")
////				registry.addMapping("/")
////						.allowedOrigins("http://localhost:3000")
//						.allowedMethods("*");
////						.allowedHeaders("header1", "header2", "header3")
////						.exposedHeaders("header1", "header2")
////						.allowCredentials(false).maxAge(3600);
//			}
//		};
//	}
}
