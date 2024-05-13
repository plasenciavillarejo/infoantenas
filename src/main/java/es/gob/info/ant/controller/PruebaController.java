package es.gob.info.ant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.models.service.ICacheProvinciasService;
import es.gob.info.ant.service.IProvinciasService;

@RestController
public class PruebaController {

	@Autowired
	private ICacheProvinciasService provinciasCacheService;
	
	@Autowired
	private IProvinciasService proviciasService;
	
	@GetMapping(value = "/listadoProvincias")
	public ResponseEntity<Slice<CacheProvinciasDto>> listarProvincias(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = "nombreRegistroEntidadesLocales", direction = Direction.ASC) Sort sort) {
		Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
		
		Slice<CacheProvinciasDto> listaProv = provinciasCacheService.listarProvincias(page);
		
	    boolean siglasNoInicializadas = listaProv.stream().anyMatch(list -> list.getSiglasProvincia() == null);
		
		if(siglasNoInicializadas) {
			listaProv.forEach(prov -> prov.setSiglasProvincia(proviciasService.siglasProvincias(prov.getNombreRegistroEntidadesLocales())));
		}
		
		return new ResponseEntity<>(listaProv, HttpStatus.OK);
	}
	
	
}
