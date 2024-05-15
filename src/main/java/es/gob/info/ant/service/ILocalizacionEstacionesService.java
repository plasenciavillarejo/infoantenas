package es.gob.info.ant.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.exception.FiltroAntenasException;

public interface ILocalizacionEstacionesService {

	public Map<String, Object> listaEstaciones(String codProvincia, String codMunicipio, String calle,
			Double latitudIni, Double latitudFin, Double longitudIni, Double longitudFin, Integer zoom, Pageable page,
			PaginadorDto paginador) throws FiltroAntenasException;
	
}
