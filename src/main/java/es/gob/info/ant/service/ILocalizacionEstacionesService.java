package es.gob.info.ant.service;

import java.util.Map;

import es.gob.info.ant.exception.FiltroEstacionesException;

public interface ILocalizacionEstacionesService {

	public Map<String, Object> listaEstaciones(Double latitud, Double longitud, Double radio)
			throws FiltroEstacionesException;

}
