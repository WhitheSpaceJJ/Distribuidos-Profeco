package com.consumidor.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
@ComponentScan(basePackages = {"com.controller.service", "com.consumidor.service.servicio", "com.consumidor.service.configuracion"})
@EntityScan("com.consumidorservice.model")
@EnableJpaRepositories("com.consumidorservice.repository")
public class ConsumidorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumidorServiceApplication.class, args);
	}

}
