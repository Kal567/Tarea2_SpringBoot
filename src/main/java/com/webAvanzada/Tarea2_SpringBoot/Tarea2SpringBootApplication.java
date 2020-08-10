package com.webAvanzada.Tarea2_SpringBoot;

import com.webAvanzada.Tarea2_SpringBoot.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tarea2SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tarea2SpringBootApplication.class, args);
	}

}
