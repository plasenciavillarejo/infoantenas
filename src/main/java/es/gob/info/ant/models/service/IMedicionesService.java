package es.gob.info.ant.models.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.NivelesMediosDto;

public interface IMedicionesService {

	public List<NivelesMediosDto> listarMediciones(@Param("emplazamiento") String emplazamiento);
	
}
