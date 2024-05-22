package es.gob.info.ant.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ICacheMunicipiosService {

	public Page<Object []> listarMunicipios(Pageable pageable, @Param("codProvincia") Long codProvincia);
}
