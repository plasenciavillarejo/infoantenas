package es.gob.info.ant.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.NivelesMediosDto;
import es.gob.info.ant.models.entity.Mediciones;

public interface IMedicionesDao extends JpaRepository<Mediciones, String> {

	@Query(value = "select new es.gob.info.ant.dto.NivelesMediosDto(me.distancia, me.acimut, me.medida,me.emplazamiento, me.provincia) "
		+ " from Mediciones me"
		+ " where me.emplazamiento = :emplazamiento")
	public List<NivelesMediosDto> listarMediciones(@Param("emplazamiento") String emplazamiento);
	
}
