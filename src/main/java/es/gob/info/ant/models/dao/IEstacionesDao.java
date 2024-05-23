package es.gob.info.ant.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto;
import es.gob.info.ant.models.entity.VcnEstaciones;

public interface IEstacionesDao extends PagingAndSortingRepository<VcnEstaciones, String> {

	@Query(value = "select new es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto(es.codEstacion,es.operador,es.banda,es.referencia) "
			+ " from VcnEstaciones es where es.emplazamiento = :emplazamiento")
	public List<DatosCaracteristicasTecnicasDto> listadoEstaciones(@Param("emplazamiento") String emplazamiento);
	
}
