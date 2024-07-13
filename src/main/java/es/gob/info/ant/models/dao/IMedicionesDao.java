package es.gob.info.ant.models.dao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.ListaNivelesMediosDto;
import es.gob.info.ant.models.entity.VcnMediciones;

public interface IMedicionesDao extends JpaRepository<VcnMediciones, String> {

	@Query(value = "select new es.gob.info.ant.dto.ListaNivelesMediosDto(me.distancia, me.acimut, me.medida, me.provincia, me.emplazamiento) "
		+ " from VcnMediciones me"
		+ " where me.emplazamiento IN (:emplazamiento)")
	public CompletableFuture<List<ListaNivelesMediosDto>> listarMediciones(@Param("emplazamiento") List<String> emplazamiento);
	
}
