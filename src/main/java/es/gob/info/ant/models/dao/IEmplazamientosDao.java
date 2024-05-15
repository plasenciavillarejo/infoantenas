package es.gob.info.ant.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.models.entity.VcnEmplazamientos;

public interface IEmplazamientosDao extends PagingAndSortingRepository<VcnEmplazamientos, String> {

	@Query(value = "select distinct emplaza.emplazamiento, emplaza.direccion, emplaza.localidad, emplaza.municipio, emplaza.provincia,"
			+ " emplaza.latitud_ghms, emplaza.longitud_ghms,"
			+ " emplaza.latitud, emplaza.longitud, emplaza.latitudCC, emplaza.longitudCC, emplaza.latitudIDEE, emplaza.longitudIDEE,"
			+ " emplaza.fechaActualizacion, emplaza.observaciones, emplaza.latitudETRS89, emplaza.longitudETRS89"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " left join gis.CacheProvincias provin"
			+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia"
			+ " left join gis.CacheMunicipios muni"
			+ " on muni.codProvincia = provin.codProvincia"
			+ " where (:codProvincia is null or muni.codProvincia = :codProvincia)"
			+ " and (:codMunicipio is null or muni.codMunicipio = :codMunicipio)" 	
		    + " and (:direccion is null or direccion like CONCAT('%', :direccion, '%'))",nativeQuery = true)
		public Page<Object []> listaEmplazamientos(@Param("codProvincia") String codProvincia, @Param("codMunicipio") String codMunicipio,
				@Param("direccion") String direccion, Pageable pageable);
		
	@Query(value = "select emplaza.direccion"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " where emplaza.emplazamiento = :emplazamiento ", nativeQuery = true)
	public String obtenerDireccion(@Param("emplazamiento") String emplazamiento);

	@Query(value = "select disctint(emplaza.emplazamiento) ,*"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " left join gis.CacheProvincias provin"
			+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia" 
			+ " left join gis.CacheMunicipios muni"
			+ " on muni.codProvincia = provin.codProvincia"
			+ " where (:codProvincia is null or muni.codProvincia = :codProvincia)"
			+ " and (:codMunicipio is null or muni.codMunicipio = :codMunicipio)"
			+ " and (:direccion is null or direccion like CONCAT('%', :direccion, '%'))" 
			+ " and (:latitudIni is null or :latitudIni >= provin.latitudEsquinaInferiorIzquierda)"
			+ " and (:latitudFin is null or :latitudFin <= provin.latitudEsquinaSuperiorDerecha)"
			+ " and (:longitudIni is null or :longitudIni >= provin.longitudEsquinaInferiorIzquierda)"
			+ " and (:longitudFin is null or :longitudFin <= provin.longitudEsquinaSuperiorDerecha)"
			+ " and (:zoom is null or :zoom = :zoom)"
			+ " group by emplaza.emplazamiento", nativeQuery = true)
	public Page<Object[]> listaEstacionesFiltradas(@Param("codProvincia") String codProvincia,
			@Param("codMunicipio") String codMunicipio, @Param("direccion") String direccion,
			@Param("latitudIni") Double latitudIni, @Param("latitudFin") Double latitudFin,
			@Param("longitudIni") Double longitudIni, @Param("longitudFin") Double longitudFin,
			@Param("zoom") Integer zoom,
			Pageable pageable);

}
