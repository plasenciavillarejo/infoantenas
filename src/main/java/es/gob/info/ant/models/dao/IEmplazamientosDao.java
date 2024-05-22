package es.gob.info.ant.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.models.entity.VcnEmplazamientos;

public interface IEmplazamientosDao extends PagingAndSortingRepository<VcnEmplazamientos, String> {

	@Query(value = ConstantesAplicacion.QUERYLISTADOEMPLAZAMIENTOS,
			countQuery = ConstantesAplicacion.QUERYLISTADOEMPLAZAMIENTOS, nativeQuery = true)
		public Page<Object []> listaEmplazamientos(@Param("codProvincia") Long codProvincia, @Param("codMunicipio") Long codMunicipio,
				@Param("direccion") String direccion, Pageable pageable);
		
	@Query(value = "select emplaza.direccion"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " where emplaza.emplazamiento = :emplazamiento ", nativeQuery = true)
	public String obtenerDireccion(@Param("emplazamiento") String emplazamiento);

	@Query(value = ConstantesAplicacion.QUERYLISTADOESTACINES,
			countQuery = ConstantesAplicacion.QUERYLISTADOESTACINES,
			nativeQuery = true)
	public List<Object[]> listaEstacionesFiltradas(@Param("latitud") Double latitud, @Param("longitud") Double longitud,
			@Param("radio") Double radio);

	@Query(value = ConstantesAplicacion.QUERYESTACION,
			countQuery = ConstantesAplicacion.QUERYESTACION,
			nativeQuery = true)
	public Object obtenerDetalleEstacion(@Param("emplazamiento") String emplazamiento);

}
