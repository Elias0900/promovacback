package com.tresorerie.voyage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableJpaAuditing
public class PromovacApplication {

	public static void main(String[] args) {
		SpringApplication.run(PromovacApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer myMvcConfigurer() {
		return new WebMvcConfigurer() {

			// CROS ORIGIN
			@Override
			public void addCorsMappings(CorsRegistry registry) {;
				registry.addMapping("/**")
						.allowedOrigins("*") //en dév
						//.allowedOrigins(allowedUrls) //en prod
						.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
			}


		};
	}
}
