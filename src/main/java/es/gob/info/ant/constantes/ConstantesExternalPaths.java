package es.gob.info.ant.constantes;

import org.springframework.stereotype.Component;

@Component
public class ConstantesExternalPaths {

	//public static final String INFOANTENAS_CONFIGURATION_PROPERTIES_PATH="file:${infoantenas.configpath}/infoant.properties";
	
	// Trabajando con docker
	public static final String INFOANTENAS_CONFIGURATION_PROPERTIES_PATH="file:/config/infoant.properties";
}


