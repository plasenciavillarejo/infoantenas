package es.gob.info.ant.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.DatosLocalizacionDto;
import es.gob.info.ant.dto.DetallesAntenaDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.models.service.IEmplazamientosService;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.models.service.IMedicionesService;
import es.gob.info.ant.service.IDetalleAntenasService;

@Service
public class DetalleAntenasServiceImpl implements IDetalleAntenasService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DetalleAntenasServiceImpl.class); 

	@Autowired
	private IEstacionesService estacionesService;
	
	@Autowired
	private IEmplazamientosService emplazamientosService;
	
	@Autowired
	private IMedicionesService medicionesService;
	
	@Override
	public Page<DetallesAntenaDto> obtenerDetalleAntenas(String codEmplazamiento, Pageable page,
			PaginadorDto paginador) {
		Page<DetallesAntenaDto> estaciones = null;
		LOGGER.info("Se procede a buscar las estaciones por emplazamiento");
		try {
			LOGGER.info("Buscando desde la pagina: {} hasta la página: {} ", page.getPageNumber(), page.getPageSize());
			estaciones = estacionesService.listadoEstacionesPageable(codEmplazamiento, page);
			
			LOGGER.info("Configurando el tampo del paginador");
			paginador.setInboxSize((int)estaciones.getTotalElements());
			
			LOGGER.info("Rellenamos los datos de datosLocalizacion, datosCaracteristicasTecnicas y nivelesMedios");
			estaciones.forEach(estacion -> {
				DatosLocalizacionDto datosLocalizacion = new DatosLocalizacionDto();
				datosLocalizacion.setCodEstacion(estacion.getCodEstacion());
				datosLocalizacion.setEmplazamiento(codEmplazamiento);
				datosLocalizacion.setDireccion(emplazamientosService.obtenerDirecciones(codEmplazamiento));
				estacion.setDatosLocalizacion(datosLocalizacion);
				
				DatosCaracteristicasTecnicasDto datosCaracteristicasTecnicas = new DatosCaracteristicasTecnicasDto();
				datosCaracteristicasTecnicas.setEmplazamiento(estacion.getEmplazamiento());
				datosCaracteristicasTecnicas.setBanda(estacion.getBanda());
				datosCaracteristicasTecnicas.setOperador(estacion.getOperador());
				datosCaracteristicasTecnicas.setReferencia(estacion.getReferencia());
				estacion.setDatosCaracteristicasTecnicas(datosCaracteristicasTecnicas);
				
				estacion.setNivelesMedios(medicionesService.listarMediciones(estacion.getEmplazamiento()));
			});			

		} catch (Exception e) {
			LOGGER.error("ERROR en la query encargada de buscar los emplazamientos {}", e.getMessage(), e.getCause());
		}
		return estaciones;
	}

}
