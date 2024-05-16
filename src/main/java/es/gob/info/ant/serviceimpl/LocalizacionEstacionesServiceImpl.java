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

import es.gob.info.ant.dto.DatosLocalizacionDto;
import es.gob.info.ant.dto.FiltradoAntenasDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.exception.FiltroAntenasException;
import es.gob.info.ant.models.service.IEmplazamientosService;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.models.service.IMedicionesService;
import es.gob.info.ant.service.ILocalizacionAntenasService;
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
	public Map<String, Object> listaEstaciones(String codProvincia, String codMunicipio, String calle,
			Double latitudIni, Double latitudFin, Double longitudIni, Double longitudFin, Pageable page,
			PaginadorDto paginador) throws FiltroAntenasException {
		Page<Object []> emplazamientos = null;
		Map<String, Object> param = new HashMap<>();
		List<FiltradoAntenasDto> emplDto = null;
		LOGGER.info("Se procede a buscar los emplazamientos");
		try {
			LOGGER.info("Buscando desde la pagina: {} hasta la página: {} ", page.getPageNumber(), page.getPageSize());
			emplazamientos = emplazamientoService.listaEstacionesFiltradas(codProvincia, codMunicipio,calle, latitudIni, latitudFin, longitudIni, longitudFin, page);

			LOGGER.info("Se han encontrado un total de {} registros", emplazamientos.getNumberOfElements());
			
			LOGGER.info("Configurando el tampo del paginador");
			paginador.setInboxSize((int)emplazamientos.getTotalElements());
			param.put("Paginador", paginador);
			
			emplDto = emplazamientos.stream().map(empl -> {
				FiltradoAntenasDto em = new FiltradoAntenasDto();							
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
				LOGGER.info("Se procede a recuperar las Características Técnicas asociada a las estaciones ");
				em.setDatosCaracteristicasTecnicas(estacionesService.listadoEstaciones(String.valueOf(empl[0])));
				LOGGER.info("Se procede a recuperar los Niveles Medios");
				em.setNivelesMedios(medicioneService.listarMediciones(String.valueOf(empl[0])));
				LOGGER.info("Se procede a recuperar los Datos de Localización");
				// PLASENCIA - DUDA POR QUE ESTE DATO ES IGUAL QUE LOS RECOGIDOS EN DATOS CARACTERISTICAS TÉCNICAS SOLO QUE FALTARÍA EL CÓDIGO DE ESTACION
				DatosLocalizacionDto datosLocalizacion = new DatosLocalizacionDto();
				datosLocalizacion.setCodEstacion(null);
				datosLocalizacion.setDireccion(String.valueOf(empl[1]));
				em.setDatosLocalizacion(datosLocalizacion);
				return em;
			}).toList();
		} catch (Exception e) {
			throw new FiltroAntenasException("Error en la obtención de las query para el filtrado de las Antenas: "+  e.getCause() + " " + e.getCause());
		}
		param.put("Emplazamiento", emplDto);
		return param;
	}

}
