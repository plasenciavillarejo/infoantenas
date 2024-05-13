package es.gob.info.ant.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import es.gob.info.ant.dto.CacheProvinciasDto;

public interface ICacheProvinciasService {

	public Slice<CacheProvinciasDto> listarProvincias(Pageable pageable);
}
