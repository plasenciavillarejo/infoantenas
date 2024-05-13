package es.gob.info.ant.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.models.entity.CacheProvincias;

public interface ICacheProvinciasDao extends JpaRepository<CacheProvincias, Long> {
	
	@Query(value = "select new es.gob.info.ant.dto.CacheProvinciasDto(prov.codProvincia, "
			+ " prov.nombreRegistroEntidadesLocales) from CacheProvincias prov")
	public List<CacheProvinciasDto> listarProvincias();
}
