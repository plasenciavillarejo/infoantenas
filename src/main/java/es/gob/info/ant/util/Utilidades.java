package es.gob.info.ant.util;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.FiltradoAntenasDto;
import es.gob.info.ant.dto.ListaDatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.ListaNivelesMediosDto;
import es.gob.info.ant.dto.NivelesMediosDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.models.service.IMedicionesService;

@Component
public class Utilidades {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantesAplicacion.CONSTANTELOGGUERINFOANTENAS);
	
	public void configuracionPaginador(PaginadorDto paginador, Pageable page) {
		paginador.setPaginaActual(page.getPageNumber() + 1);
		paginador.setTamanioPagina(page.getPageSize());
	}
	
	public Pageable configurarPageRequest(int pagina, int tamanioPagina, int orden, String campo) {
		return  PageRequest.of(pagina -1, tamanioPagina, orden == 1 ? Sort.by(campo).ascending() 
						: Sort.by(campo).descending());
	}

	public List<FiltradoAntenasDto> completarDatosCaracteristicaYNivelesMedios(List<FiltradoAntenasDto> emplDto,IEstacionesService estacionesService,
			IMedicionesService medicioneService) {
		LOGGER.info("Rellenamos las caracteristicas tecnicas");
		List<ListaDatosCaracteristicasTecnicasDto> datosCaracteristicasTecnicas = estacionesService.listadoEstaciones(emplDto.stream()
				.map(em -> em.getEmplazamiento()).toList());
		
		LOGGER.info("Rellenamos los niveles medios");
		List<ListaNivelesMediosDto> nivelesMediosDto = medicioneService.listarMediciones(emplDto.stream()
				.map(em -> em.getEmplazamiento()).toList());
		
		emplDto.forEach(em -> {
			  em.setDatosCaracteristicasTecnicas(datosCaracteristicasTecnicas.stream()
			    .filter(da -> da.getEmplazamiento().equals(em.getEmplazamiento()))
			    .map(DatosCaracteristicasTecnicasDto::new)
			    .toList());

			  em.setNivelesMedios(nivelesMediosDto.stream()
			    .filter(da -> da.getEmplazamiento().equals(em.getEmplazamiento()))
			    .map(NivelesMediosDto::new)
			    .toList());
			});
		return emplDto;
	}
	
}
