package es.gob.info.ant.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.gob.info.ant.dto.CacheProvinciasDto;

public interface ICacheProvinciasService {

	public Page<CacheProvinciasDto> listarProvincias(Pageable pageable);
}
