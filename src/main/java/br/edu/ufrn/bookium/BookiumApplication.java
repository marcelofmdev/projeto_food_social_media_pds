package br.edu.ufrn.bookium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookiumApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookiumApplication.class, args);
	}

}
