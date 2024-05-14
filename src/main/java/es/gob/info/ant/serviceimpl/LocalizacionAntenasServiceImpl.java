package es.gob.info.ant.serviceimpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gob.info.ant.dto.EmplazamientosDto;
import es.gob.info.ant.dto.EstacionesDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.models.service.IEmplazamientosService;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.service.ILocalizacionAntenasService;

@Service
public class LocalizacionAntenasServiceImpl implements ILocalizacionAntenasService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocalizacionAntenasServiceImpl.class); 

	@Autowired
	private IEmplazamientosService emplazamientoService;

	@Autowired
	private IEstacionesService estacionesService;
	
	@Override
	public Map<String, Object> listaAntenas(String codProvincia, String codMunicipio, String calle, Pageable page,
			PaginadorDto paginador) {
		Page<Object []> emplazamientos = null;
		Map<String, Object> param = new HashMap<>();
		LOGGER.info("Se procede a buscar los emplazamientos");
		try {
			LOGGER.info("Buscando desde la pagina: {} hasta la página: {} ", page.getPageNumber(), page.getPageSize());
			emplazamientos = emplazamientoService.listaEmplazamientos(codProvincia, codMunicipio,calle, page);
			
			LOGGER.info("Configurando el tampo del paginador");
			paginador.setInboxSize((int)emplazamientos.getTotalElements());
			
			List<EmplazamientosDto> emplDto = emplazamientos.stream().map(empl -> {
				EmplazamientosDto em = new EmplazamientosDto();							
				em.setEmplazamiento(empl[0] != null ? String.valueOf(empl[0]): "");
				em.setDireccion(empl[1] != null ? String.valueOf(empl[1]): "");
				em.setLocalidad(empl[2] != null ? String.valueOf(empl[2]): "");
				em.setMunicipio(empl[3] != null ? String.valueOf(empl[3]): "");
				em.setProvincia(empl[4] != null ? String.valueOf(empl[4]): "");
				em.setLatitudGhms(empl[5] != null ? String.valueOf(empl[5]): "");
				em.setLongitudGhms(empl[6] != null ? String.valueOf(empl[6]): "");
				em.setLatitud(empl[7] != null ?  new BigDecimal(String.valueOf(empl[7])) : null);
				em.setLongitud(empl[8] != null ?  new BigDecimal(String.valueOf(empl[8])) : null);
				em.setLatitudCc(empl[9] != null ?  new BigDecimal(String.valueOf(empl[9])) : null);
				em.setLongitudCc(empl[10] != null ?  new BigDecimal(String.valueOf(empl[10])) : null);
				em.setLatitudIdee(empl[11] != null ?  new BigDecimal(String.valueOf(empl[11])) : null);
				em.setLongitudIdee(empl[12] != null ?  new BigDecimal(String.valueOf(empl[12])) : null);				
				em.setFechaActualizacion(empl[13] != null ? String.valueOf(empl[13]): "");
				em.setObservaciones(!String.valueOf(empl[14]).trim().isEmpty() ? String.valueOf(empl[14]).trim() : "");				
				em.setLatitudEtrs(empl[15] != null ? new BigDecimal(String.valueOf(empl[15])) : null);
				em.setLongitudEtrs(empl[16] != null ? new BigDecimal(String.valueOf(empl[16])) : null);
				return em;
			}).toList();
			
			LOGGER.info("Se han encontrado un total de {} registros", emplazamientos.getNumberOfElements());
			param.put("Emplazamientos",emplDto);
			param.put("Paginador", paginador);
			
			LOGGER.info("Se procede a recuperar las características técnicas asociada a las estaciones ");
			param.put("Estaciones", !emplDto.isEmpty() 
					? emplDto.stream().map(eml -> estacionesService.listadoEstaciones(eml.getEmplazamiento()))
				    : Collections.emptyList());

		} catch (Exception e) {
			LOGGER.error("ERROR en la query encargada de buscar los emplazamientos {}", e.getMessage(), e.getCause());
		}
		return param;
	}

}
