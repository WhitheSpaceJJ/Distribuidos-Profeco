package com.app.web;

import com.app.web.repositorio.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudSpringBootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringBootApplication.class, args);
	}

	@Autowired
	private IProductoRepositorio repositorio;

	@Override
	public void run(String... args) throws Exception {
		/*
		Producto estudiante1 = new Producto("Israel", "Parra", "israelparracamara@gmail.com");
		repositorio.save(estudiante1);

		Producto estudiante2 = new Producto("Luis", "Parra", "luisito777@gmail.com");
		repositorio.save(estudiante2);
        */
	}



	}

