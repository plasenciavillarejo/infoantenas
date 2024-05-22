package es.gob.info.ant.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.exception.FiltroAntenasException;
import es.gob.info.ant.exception.FiltroEstacionesException;

public interface ILocalizacionEstacionesService {

	public Map<String, Object> listaEstaciones(Double latitud, Double longitud, Double radio) throws FiltroEstacionesException;
	
}
