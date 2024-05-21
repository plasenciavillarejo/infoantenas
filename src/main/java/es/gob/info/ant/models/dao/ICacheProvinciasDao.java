package es.gob.info.ant.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.gob.info.ant.models.entity.CacheProvincias;

public interface ICacheProvinciasDao extends PagingAndSortingRepository<CacheProvincias, Long> {
	
	@Query(value = "select prov.codProvincia, prov.nombreRegistroEntidadesLocales from CacheProvincias prov"
			+ " order by nombreRegistroEntidadesLocales asc")
	public List<Object []> listarProvincias();
}
