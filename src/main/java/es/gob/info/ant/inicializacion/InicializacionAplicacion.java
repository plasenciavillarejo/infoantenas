package es.gob.info.ant.inicializacion;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.models.service.ICacheProvinciasService;
import jakarta.annotation.PostConstruct;

@Component
public class InicializacionAplicacion {

	private static final Logger LOGGER = LoggerFactory.getLogger(InicializacionAplicacion.class);
	
	@Autowired
	private ICacheProvinciasService provinciasService;
	
	@PostConstruct
	public void iniciarAplicacion() {
		LOGGER.info("Inicializando la lista de las provincias cacheadas");
		List<CacheProvinciasDto> listaProv = provinciasService.listarProvincias();
		LOGGER.info("Consulta cacheada  - OKEY");
		
	}
		
	
}
