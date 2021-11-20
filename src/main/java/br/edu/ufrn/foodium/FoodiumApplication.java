package br.edu.ufrn.foodium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FoodiumApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodiumApplication.class, args);
	}

}
