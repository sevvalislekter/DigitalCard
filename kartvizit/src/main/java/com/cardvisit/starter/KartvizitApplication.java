package com.cardvisit.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.cardvisit"})
@SpringBootApplication(scanBasePackages = {"com.cardvisit"})
@EnableJpaRepositories(basePackages = {"com.cardvisit.repository"})
@EntityScan(basePackages = {"com.cardvisit"})
public class KartvizitApplication {

	public static void main(String[] args) {
		SpringApplication.run(KartvizitApplication.class, args);
	}
}
