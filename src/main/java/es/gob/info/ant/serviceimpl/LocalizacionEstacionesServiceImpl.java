package es.gob.info.ant.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gob.info.ant.dto.FiltradoAntenasDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.exception.FiltroEstacionesException;
import es.gob.info.ant.models.service.IEmplazamientosService;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.models.service.IMedicionesService;
import es.gob.info.ant.service.ILocalizacionEstacionesService;

@Service
public class LocalizacionEstacionesServiceImpl implements ILocalizacionEstacionesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocalizacionEstacionesServiceImpl.class); 

	@Autowired
	private IEmplazamientosService emplazamientoService;

	@Autowired
	private IEstacionesService estacionesService;
	
	@Autowired
	private IMedicionesService medicioneService;

	@Override
	public Map<String, Object> listaEstaciones(Double latitud, Double longitud, Double radio, Pageable page,
			PaginadorDto paginador) throws FiltroEstacionesException {
		Page<Object []> emplazamientos = null;
		Map<String, Object> param = new HashMap<>();
		List<FiltradoAntenasDto> emplDto = null;
		LOGGER.info("Se procede a buscar los emplazamientos");
		try {
			LOGGER.info("Buscando desde la pagina: {} hasta la página: {} ", page.getPageNumber(), page.getPageSize());
			emplazamientos = emplazamientoService.listaEstacionesFiltradas(latitud, longitud, radio, page);

			LOGGER.info("Se han encontrado un total de {} registros", emplazamientos.getNumberOfElements());
			
			LOGGER.info("Configurando el tampo del paginador");
			paginador.setRegistros((int)emplazamientos.getTotalElements());
			param.put("Paginador", paginador);
			
			emplDto = emplazamientos.stream().map(empl -> {
				FiltradoAntenasDto em = new FiltradoAntenasDto();							
				em.setEmplazamiento(empl[0] != null ? String.valueOf(empl[0]): "");
				em.setDireccion(empl[1] != null ? String.valueOf(empl[1]): "");
				em.setLocalidad(empl[2] != null ? String.valueOf(empl[2]): "");
				em.setMunicipio(empl[3] != null ? String.valueOf(empl[3]): "");
				em.setProvincia(empl[4] != null ? String.valueOf(empl[4]): "");
				em.setLatitud(empl[5] != null ?  new BigDecimal(String.valueOf(empl[5])) : null);
				em.setLongitud(empl[6] != null ?  new BigDecimal(String.valueOf(empl[6])) : null);
				em.setFechaActualizacion(empl[7] != null ? String.valueOf(empl[7]): "");
				em.setObservaciones(!String.valueOf(empl[8]).trim().isEmpty() ? String.valueOf(empl[8]).trim() : "");				
				LOGGER.info("Se procede a recuperar las Características Técnicas asociada a las estaciones ");
				em.setDatosCaracteristicasTecnicas(estacionesService.listadoEstaciones(String.valueOf(empl[0])));
				LOGGER.info("Se procede a recuperar los Niveles Medios");
				em.setNivelesMedios(medicioneService.listarMediciones(String.valueOf(empl[0])));
				return em;
			}).toList();
		} catch (Exception e) {
			throw new FiltroEstacionesException("Error en la obtención de las query para el filtrado de las Antenas: "+  e.getCause() + " " + e.getCause());
		}
		param.put("Emplazamiento", emplDto);
		return param;
	}

}
