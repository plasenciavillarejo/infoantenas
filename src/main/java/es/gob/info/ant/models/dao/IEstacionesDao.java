package es.gob.info.ant.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto;
import es.gob.info.ant.models.entity.Estaciones;

public interface IEstacionesDao extends PagingAndSortingRepository<Estaciones, Long> {

	@Query(value = "select new es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto(es.operador,es.referencia,es.banda, es.emplazamiento) from Estaciones es"
			+ " where es.emplazamiento = :emplazamiento")
	public List<DatosCaracteristicasTecnicasDto> listadoEstaciones(@Param("emplazamiento") String emplazamiento);
	
}
