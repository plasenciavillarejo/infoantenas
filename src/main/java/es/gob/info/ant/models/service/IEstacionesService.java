package es.gob.info.ant.models.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.EstacionesDto;

public interface IEstacionesService {

	public List<EstacionesDto> listadoEstaciones(@Param("emplazamiento") String emplazamiento);
	
}
