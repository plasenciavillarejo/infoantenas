package es.gob.info.ant.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.FiltradoAntenasDto;
import es.gob.info.ant.exception.FiltroEstacionesException;
import es.gob.info.ant.models.service.IEmplazamientosService;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.models.service.IMedicionesService;
import es.gob.info.ant.service.ILocalizacionEstacionesService;
import es.gob.info.ant.util.Utilidades;

@Service
public class LocalizacionEstacionesServiceImpl implements ILocalizacionEstacionesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantesAplicacion.CONSTANTELOGGUERINFOANTENAS); 

	@Autowired
	private IEmplazamientosService emplazamientoService;

	@Autowired
	private IEstacionesService estacionesService;
	
	@Autowired
	private IMedicionesService medicioneService;

	@Autowired
	private Utilidades utilidades;
	
	@Override
	public Map<String, Object> listaEstaciones(Double latitud, Double longitud, Double radio) throws FiltroEstacionesException {
		List<Object []> emplazamientos = null;
		Map<String, Object> param = new HashMap<>();
		List<FiltradoAntenasDto> emplDto = null;
		LOGGER.info("Se procede a buscar los emplazamientos");
		try {
			emplazamientos = emplazamientoService.listaEstacionesFiltradas(latitud, longitud, radio);			
			LOGGER.info("Se han encontrado un total de {} registros", emplazamientos.size());
			LOGGER.info("Obtenemos los emplazamientos");
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
				return em;
			}).toList();
			
			utilidades.completarDatosCaracteristicaYNivelesMedios(emplDto, estacionesService, medicioneService);
			
		} catch (Exception e) {
			throw new FiltroEstacionesException(e.getMessage(), e.getCause());
		}
		param.put("emplazamientos", emplDto);
		return param;
	}

}
