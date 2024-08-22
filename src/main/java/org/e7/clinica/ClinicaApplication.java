package org.e7.clinica;.class, args);

	}

}

import org.e7.clinica.db.H2Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class
ClinicaApplication {

	public static void main(String[] args) {

		H2Connection.crearTablas();
		SpringApplication.run(ClinicaApplication
