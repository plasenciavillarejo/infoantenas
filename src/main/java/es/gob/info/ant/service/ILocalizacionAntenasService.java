package es.gob.info.ant.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import es.gob.info.ant.dto.FiltradoAntenasDto;
import es.gob.info.ant.dto.PaginadorDto;

public interface ILocalizacionAntenasService {

	public List<FiltradoAntenasDto> listaAntenas(String codProvincia, String codMunicipio, String calle, Pageable page,
			PaginadorDto paginador) throws Exception;
	
}
