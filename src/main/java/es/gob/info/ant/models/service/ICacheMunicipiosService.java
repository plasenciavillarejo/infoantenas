package es.gob.info.ant.models.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.CacheMunicipiosDto;

public interface ICacheMunicipiosService {

	public Slice<CacheMunicipiosDto> listarMunicipios(Pageable pageable, @Param("codProvincia") Long codProvincia);
}
