package es.gob.info.ant.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.EstacionesDto;
import es.gob.info.ant.models.entity.Estaciones;

public interface IEstacionesDao extends PagingAndSortingRepository<Estaciones, Long> {

	@Query(value = "select new es.gob.info.ant.dto.EstacionesDto(es.emplazamiento, es.codEstacion, es.operador, es.tpSistema, es.banda,"
			+ " es.limite, es.provincia, es.referencia) from Estaciones es"
			+ " where es.emplazamiento = :emplazamiento")
	public List<EstacionesDto> listadoEstaciones(@Param("emplazamiento") String emplazamiento);
	
}
