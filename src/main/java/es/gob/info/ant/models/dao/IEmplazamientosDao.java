package es.gob.info.ant.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.models.entity.Emplazamientos;

public interface IEmplazamientosDao extends PagingAndSortingRepository<Emplazamientos, String> {

	@Query(value = "select *"
		+ " from gis.VCNE_Emplazamientos emplaza"
		+ " left join gis.CacheProvincias provin"
		+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia"
		+ " left join gis.CacheMunicipios muni"
		+ " on muni.codProvincia = provin.codProvincia"
		+ " where (:codProvincia is null or muni.codProvincia = :codProvincia )"
		+ " and (:codMunicipio is null or muni.codMunicipio = :codMunicipio)" 	
	    + " and (:direccion is null or direccion like CONCAT('%', :direccion, '%'))", nativeQuery = true)
	public Page<Object []> listaEmplazamientos(@Param("codProvincia") String codProvincia, @Param("codMunicipio") String codMunicipio,
			@Param("direccion") String direccion, Pageable pageable);
	
}
