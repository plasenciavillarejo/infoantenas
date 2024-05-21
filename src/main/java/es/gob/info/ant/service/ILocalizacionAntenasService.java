package es.gob.info.ant.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.exception.FiltroAntenasException;

public interface ILocalizacionAntenasService {

	public Map<String, Object> listaAntenas(String codProvincia, String codMunicipio, String calle, Pageable page,
			PaginadorDto paginador) throws FiltroAntenasException;

	public Map<String, Object> obtenerDetalleEstacion(String emplazamiento) throws FiltroAntenasException;
	
}
