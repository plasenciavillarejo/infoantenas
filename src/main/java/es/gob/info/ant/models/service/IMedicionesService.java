package es.gob.info.ant.models.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.repository.query.Param;

import es.gob.info.ant.dto.ListaNivelesMediosDto;

public interface IMedicionesService {

	public CompletableFuture<List<ListaNivelesMediosDto>> listarMediciones(@Param("emplazamiento") List<String> list);

}
