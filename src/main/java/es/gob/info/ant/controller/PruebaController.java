package es.gob.info.ant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<List<CacheProvinciasDto>> listarProvincias() {
		List<CacheProvinciasDto> listaProv = provinciasCacheService.listarProvincias();
		
		listaProv.forEach(prov -> prov.setSiglasProvincia(proviciasService.siglasProvincias(prov.getNombreRegistroEntidadesLocales())));
		
		return new ResponseEntity<>(listaProv, HttpStatus.OK);
	}
	
	
}
