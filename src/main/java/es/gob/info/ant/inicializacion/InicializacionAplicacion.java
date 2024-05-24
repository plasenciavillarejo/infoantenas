package es.gob.info.ant.inicializacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.models.service.ICacheProvinciasService;
import jakarta.annotation.PostConstruct;

@Component
public class InicializacionAplicacion {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantesAplicacion.CONSTANTELOGGUERINFOANTENAS);
	
	@Autowired
	private ICacheProvinciasService provinciasService;
	
	@PostConstruct
	public void iniciarAplicacion() {
		LOGGER.info("Inicializando la lista de las provincias cacheadas");
		//Pageable pageable = PageRequest.of(0, 10);
		//Page<CacheProvinciasDto> listaProv = provinciasService.listarProvincias(pageable);
		LOGGER.info("Consulta cacheada  - OKEY");
		
	}
		
	
}
