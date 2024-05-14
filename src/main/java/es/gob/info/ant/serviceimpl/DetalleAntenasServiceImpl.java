package es.gob.info.ant.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gob.info.ant.dto.EstacionesDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.service.IDetalleAntenasService;

@Service
public class DetalleAntenasServiceImpl implements IDetalleAntenasService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DetalleAntenasServiceImpl.class); 

	@Autowired
	private IEstacionesService estacionesService;
	
	@Override
	public Map<String, Object> obtenerDetalleAntenas(String codEmplazamiento, Pageable page,
			PaginadorDto paginador) {
		Page<EstacionesDto> estaciones = null;
		Map<String, Object> param = new HashMap<>();
		LOGGER.info("Se procede a buscar las estaciones por emplazamiento");
		try {
			LOGGER.info("Buscando desde la pagina: {} hasta la página: {} ", page.getPageNumber(), page.getPageSize());
			estaciones = estacionesService.listadoEstacionesPageable(codEmplazamiento, page);
			
			LOGGER.info("Configurando el tampo del paginador");
			paginador.setInboxSize((int)estaciones.getTotalElements());
			
			LOGGER.info("Se han encontrado un total de {} registros", estaciones.getNumberOfElements());
			param.put("Estaciones",estaciones);
			param.put("Paginador", paginador);

		} catch (Exception e) {
			LOGGER.error("ERROR en la query encargada de buscar los emplazamientos {}", e.getMessage(), e.getCause());
		}
		return param;
	}

}
