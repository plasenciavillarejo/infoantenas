package es.gob.info.ant.models.dao;

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
		public Page<Object []> listaEmplazamientos(@Param("codProvincia") String codProvincia, @Param("codMunicipio") String codMunicipio,
				@Param("direccion") String direccion, Pageable pageable);
		
	@Query(value = "select emplaza.direccion"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " where emplaza.emplazamiento = :emplazamiento ", nativeQuery = true)
	public String obtenerDireccion(@Param("emplazamiento") String emplazamiento);

	@Query(value = ConstantesAplicacion.QUERYLISTADOESTACINES,
			countQuery = ConstantesAplicacion.QUERYLISTADOESTACINES,
			nativeQuery = true)
	public Page<Object[]> listaEstacionesFiltradas(@Param("codProvincia") String codProvincia,
			@Param("codMunicipio") String codMunicipio, @Param("direccion") String direccion,
			@Param("latitudIni") Double latitudIni, @Param("latitudFin") Double latitudFin,
			@Param("longitudIni") Double longitudIni, @Param("longitudFin") Double longitudFin,
			@Param("zoom") Integer zoom,
			Pageable pageable);

}
