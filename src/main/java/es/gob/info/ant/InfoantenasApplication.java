package es.gob.info.ant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/* 
 	Agregamos el discovery client al agregar spring cloud kubernetes para realizar los descubrimientos
 	de los microservicios que se realizará de forma automática mediante spring cloud. El buscara en 
 	el application.properties el nombre spring.application.name para saber el host:puerto
 */
@EnableDiscoveryClient
@SpringBootApplication
public class InfoantenasApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoantenasApplication.class, args);
	}

}
