package es.gob.info.ant.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IEmplazamientosService {

	public Page<Object []> listaEmplazamientos(@Param("codProvincia") String codProvincia, @Param("codMunicipio") String codMunicipio,
			@Param("direccion") String direccion, Pageable pageable);

	public String obtenerDirecciones(@Param("emplazamiento")String emplazamiento);
	
}
