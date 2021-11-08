package ru.vivt.corpapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.vivt.corpapp.repositories.UserRepository;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CorpAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorpAppApplication.class, args);
	}

}
