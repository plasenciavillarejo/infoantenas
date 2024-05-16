package es.gob.info.ant.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IEmplazamientosService {

	public Page<Object[]> listaEmplazamientos(@Param("codProvincia") String codProvincia,
			@Param("codMunicipio") String codMunicipio, @Param("direccion") String direccion, Pageable pageable);

	public String obtenerDirecciones(@Param("emplazamiento") String emplazamiento);

	public Page<Object[]> listaEstacionesFiltradas(@Param("codProvincia") String codProvincia,
			@Param("codMunicipio") String codMunicipio, @Param("direccion") String direccion,
			@Param("latitudIni") Double latitudIni, @Param("latitudFin") Double latitudFin,
			@Param("longitudIni") Double longitudIni, @Param("longitudFin") Double longitudFin,
			Pageable pageable);

}
