package com.tresorerie.voyage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(
		info = @Info(
				title = "Promovac API",
				version = "1.0",
				description = "Documentation des APIs de Promovac"
		)
)
@SpringBootApplication
@EnableJpaAuditing
public class PromovacApplication {


	@Value("${frontapp.url}")
	private String frontBaseUrl;

	public static void main(String[] args) {
		SpringApplication.run(PromovacApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer myMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*") // Autoriser toutes les origines
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
						.allowedHeaders("*")
						.exposedHeaders("Authorization")
						.allowCredentials(false); // Désactivez si vous testez sans authentification
			}
		};
	}

}
