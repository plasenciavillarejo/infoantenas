package es.gob.info.ant.models.dao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.ListaDatosCaracteristicasTecnicasDto;
import es.gob.info.ant.models.entity.VcnEstaciones;

public interface IEstacionesDao extends PagingAndSortingRepository<VcnEstaciones, String> {

	@Query(value = "select new es.gob.info.ant.dto.ListaDatosCaracteristicasTecnicasDto(es.codEstacion,es.operador,es.banda,es.referencia, es.emplazamiento) "
			+ " from VcnEstaciones es where es.emplazamiento IN (:emplazamiento)")
	public CompletableFuture<List<ListaDatosCaracteristicasTecnicasDto>> listadoEstaciones(@Param("emplazamiento") List<String> emplazamiento);
	
}
