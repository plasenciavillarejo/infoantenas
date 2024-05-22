package es.gob.info.ant.models.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface IEmplazamientosService {

	public Page<Object[]> listaEmplazamientos(@Param("codProvincia") Long codProvincia,
			@Param("codMunicipio") Long codMunicipio, @Param("direccion") String direccion, Pageable pageable);

	public String obtenerDirecciones(@Param("emplazamiento") String emplazamiento);

	public Page<Object[]> listaEstacionesFiltradas(@Param("latitud") Double latitud,
			@Param("longitud") Double longitud, @Param("radio") Double radio,
			Pageable pageable);

	public Object obtenerDetalleEstacion(@Param("emplazamiento") String emplazamiento);

}
