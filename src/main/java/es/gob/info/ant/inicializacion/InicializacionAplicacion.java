package es.gob.info.ant.inicializacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.constantes.ConstantesExternalPaths;
import jakarta.annotation.PostConstruct;

@Component
@PropertySource(ConstantesExternalPaths.INFOANTENAS_CONFIGURATION_PROPERTIES_PATH)
public class InicializacionAplicacion {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantesAplicacion.CONSTANTELOGGUERINFOANTENAS);
	
	@Value("${spring.profiles.active}")
	private String profileActive;
	
	@PostConstruct
	public void iniciarAplicacion() {
		
		LOGGER.info("Inicializando la aplicación para el entorno {}", profileActive);
		
	}
		
	
}
