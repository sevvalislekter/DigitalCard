package com.sevvalislekter.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.sevvalislekter"})
@SpringBootApplication(scanBasePackages = {"com.sevvalislekter"})
@EnableJpaRepositories(basePackages = {"com.sevvalislekter.repository"})
@EntityScan(basePackages = {"com.sevvalislekter"})
public class KartvizitApplication {

	public static void main(String[] args) {
		SpringApplication.run(KartvizitApplication.class, args);
	}
}
