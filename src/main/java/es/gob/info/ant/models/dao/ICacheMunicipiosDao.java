package es.gob.info.ant.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.CacheMunicipiosDto;
import es.gob.info.ant.models.entity.CacheMunicipios;

public interface ICacheMunicipiosDao extends JpaRepository<CacheMunicipios, Long>{

	@Query(value = "select new es.gob.info.ant.dto.CacheMunicipiosDto(muni.cacheMunicipiosPk.codProvincia, "
			+ " muni.cacheMunicipiosPk.codMunicipio, muni.nombreRegistroEntidadesLocales) from CacheMunicipios muni"
			+ " where muni.cacheMunicipiosPk.codProvincia = :codProvincia")
	public Page<CacheMunicipiosDto> listarMunicipios(Pageable pageable, @Param("codProvincia") Long codProvincia);
	
}
