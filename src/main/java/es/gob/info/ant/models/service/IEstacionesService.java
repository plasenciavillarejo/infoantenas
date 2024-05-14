package es.gob.info.ant.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.DetallesAntenaDto;

public interface IEstacionesService {

	public List<DatosCaracteristicasTecnicasDto> listadoEstaciones(@Param("emplazamiento") String emplazamiento);
	
	public Page<DetallesAntenaDto> listadoEstacionesPageable(@Param("emplazamiento") String emplazamiento, Pageable page);
	
}
