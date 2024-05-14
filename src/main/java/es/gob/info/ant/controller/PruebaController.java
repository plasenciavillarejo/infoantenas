package es.gob.info.ant.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.gob.info.ant.dto.CacheMunicipiosDto;
import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.dto.EmplazamientosDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.models.entity.Emplazamientos;
import es.gob.info.ant.models.service.ICacheMunicipiosService;
import es.gob.info.ant.models.service.ICacheProvinciasService;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.service.IDetalleAntenasService;
import es.gob.info.ant.service.ILocalizacionAntenasService;
import es.gob.info.ant.service.IProvinciasService;

@RestController
public class PruebaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PruebaController.class); 
	
	@Autowired
	private ICacheProvinciasService provinciasCacheService;
	
	@Autowired
	private IProvinciasService proviciasService;
	
	@Autowired
	private ICacheMunicipiosService cacheMunicipiosService;
	
	@Autowired
	private ILocalizacionAntenasService localizacionAntenasService;
	
	@Autowired
	private IDetalleAntenasService detalleAntenasService;
	
	@Autowired
	private IEstacionesService estacionesService;
	
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
	
	@GetMapping(value = "/listadoMunicipios")
	public ResponseEntity<Slice<CacheMunicipiosDto>> listarMunicipios(@PageableDefault(page = 1, size = 10) Pageable pageable, 
			@SortDefault(sort = "nombreRegistroEntidadesLocales", direction = Direction.ASC) Sort sort, @RequestParam(name = "codProvincia") Long codProvincia) {	
		Pageable pageSort = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), sort);
		return new ResponseEntity<>(cacheMunicipiosService.listarMunicipios(pageSort, codProvincia), HttpStatus.OK);
	}		
	
	@GetMapping(value = "/filtradoAntenas")
	public ResponseEntity<Object> localizarAntenas(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = "localidad", direction = Direction.ASC) Sort sort,
			@RequestParam(value = "codProvincia", required = false) String codProvincia,
			@RequestParam(value = "codMunicipio", required = false) String codMunicipio, 
			@RequestParam(value = "calle", required = false) String calle,
			@RequestParam(value = "numero", required = false) String numero) {
		
		LOGGER.info("Recibiendo los datos para el filtrado de antenas, codProvincia: {}, codMunicipio: {}, calle: {},"
				+ " numero: {}", codProvincia, codMunicipio, calle, numero );
		Map<String, Object> resultado = null;
		try {
			Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
			LOGGER.info("Configurando el paginador");
			PaginadorDto paginador = new PaginadorDto();
			paginador.setCurrentPage(page.getPageNumber() + 1);
			paginador.setPageSize(page.getPageSize());
			
			String direccionCompleta = !calle.isEmpty() && !numero.isEmpty() ? calle.concat(", ").concat(numero) 
					: !calle.isEmpty() && numero.isEmpty()  ? calle : numero;
			
			resultado = localizacionAntenasService.listaAntenas(codProvincia, codMunicipio, direccionCompleta, page, paginador); 
		} catch (Exception e) {
			LOGGER.error("ERROR recuperando los emplazamientos {}", e.getMessage(), e.getCause());
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detalleAntenas")
	public ResponseEntity<Object> localizarAntenas(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = "emplazamiento", direction = Direction.ASC) Sort sort,
			@RequestParam(value = "emplazamiento", required = false) String emplazamiento) {
		
		LOGGER.info("Recibiendo los datos para el filtrado de detalle antenas, emplazamiento: {}", emplazamiento);
		Map<String, Object> resultado = null;
		try {
			Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
			LOGGER.info("Configurando el paginador");
			PaginadorDto paginador = new PaginadorDto();
			paginador.setCurrentPage(page.getPageNumber() + 1);
			paginador.setPageSize(page.getPageSize());
			
			resultado = detalleAntenasService.obtenerDetalleAntenas(emplazamiento, page, paginador); 
		} catch (Exception e) {
			LOGGER.error("ERROR recuperando las estaciones {}", e.getMessage(), e.getCause());
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
