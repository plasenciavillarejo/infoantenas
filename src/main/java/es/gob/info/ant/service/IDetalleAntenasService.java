package es.gob.info.ant.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.gob.info.ant.dto.DetallesAntenaDto;
import es.gob.info.ant.dto.PaginadorDto;

public interface IDetalleAntenasService {

	public Page<DetallesAntenaDto> obtenerDetalleAntenas(String codEmplazamiento, Pageable page, PaginadorDto paginador);
	
}
