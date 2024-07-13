package es.gob.info.ant.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.gob.info.ant.dto.CacheMunicipiosDto;
import es.gob.info.ant.dto.ParametrosMunicipiosDto;
import es.gob.info.ant.exception.ErrorGlobalAntenasException;

public interface IMunicipiosService {

	public Page<Object[]> listaMuncipios(Pageable page, ParametrosMunicipiosDto parametrosDto)
			throws ErrorGlobalAntenasException;

	public List<CacheMunicipiosDto> listaMuncipiosDto(Page<Object[]> listaMuncipios) throws ErrorGlobalAntenasException;

}
