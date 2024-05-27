package es.gob.info.ant.models.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.ListaDatosCaracteristicasTecnicasDto;

public interface IEstacionesService {

	public List<ListaDatosCaracteristicasTecnicasDto> listadoEstaciones(@Param("emplazamiento") List<String> list);
		
}
