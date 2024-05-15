package es.gob.info.ant.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.models.entity.CacheProvincias;

public interface ICacheProvinciasDao extends PagingAndSortingRepository<CacheProvincias, Long> {
	
	@Query(value = "select new es.gob.info.ant.dto.CacheProvinciasDto(prov.codProvincia, "
			+ " prov.nombreRegistroEntidadesLocales) from CacheProvincias prov")
	public Page<CacheProvinciasDto> listarProvincias(Pageable pageable);
}
