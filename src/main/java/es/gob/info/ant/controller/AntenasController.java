package es.gob.info.ant.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.CacheMunicipiosDto;
import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.dto.DetallesAntenaDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.exception.FiltroAntenasException;
import es.gob.info.ant.models.service.ICacheMunicipiosService;
import es.gob.info.ant.models.service.ICacheProvinciasService;
import es.gob.info.ant.service.IDetalleAntenasService;
import es.gob.info.ant.service.ILocalizacionAntenasService;
import es.gob.info.ant.service.ILocalizacionEstacionesService;
import es.gob.info.ant.service.IProvinciasService;
import es.gob.info.ant.util.Utilidades;

@RestController
public class AntenasController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AntenasController.class); 
	
	@Autowired
	private ICacheProvinciasService provinciasCacheService;
	
	@Autowired
	private IProvinciasService proviciasService;
	
	@Autowired
	private ICacheMunicipiosService cacheMunicipiosService;
	
	@Autowired
	private ILocalizacionAntenasService localizacionAntenasService;
	
	@Autowired
	private ILocalizacionEstacionesService localizacionEstacionesService;
	
	@Autowired
	private IDetalleAntenasService detalleAntenasService;
	
	@Autowired
	private Utilidades utilidades;
	
	@GetMapping(value = "/listadoProvincias")
	public ResponseEntity<Object> listarProvincias(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = "nombreRegistroEntidadesLocales", direction = Direction.ASC) Sort sort) {
		Map<String, Object> resultado = new LinkedHashMap<>();

		Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
		
		LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
		PaginadorDto paginador = new PaginadorDto();
		utilidades.configuracionPaginador(paginador, page);
		
		Page<CacheProvinciasDto> listaProv = provinciasCacheService.listarProvincias(page);
		
	    boolean siglasNoInicializadas = listaProv.stream().anyMatch(list -> list.getSiglasProvincia() == null);
		
		if(siglasNoInicializadas) {
			listaProv.forEach(prov -> prov.setSiglasProvincia(proviciasService.siglasProvincias(prov.getNombreRegistroEntidadesLocales())));
		}
		paginador.setInboxSize((int) listaProv.getTotalElements());
		
		LOGGER.info("Transformamos el Page de Provincias a un listado");
		resultado.put("Provincias", listaProv.stream().map(listaProvin -> listaProvin).toList());
		resultado.put("Paginador", paginador);
		
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listadoMunicipios")
	public ResponseEntity<Object> listarMunicipios(@PageableDefault(page = 1, size = 10) Pageable pageable, 
			@SortDefault(sort = "nombreRegistroEntidadesLocales", direction = Direction.ASC) Sort sort, @RequestParam(name = "codProvincia") Long codProvincia) {	
		Map<String, Object> resultado = new LinkedHashMap<>();
		Pageable pageSort = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), sort);
		LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
		PaginadorDto paginador = new PaginadorDto();
		utilidades.configuracionPaginador(paginador, pageSort);
		
		LOGGER.info("Se procede a listar los municipos asociados a las provincias");
		Page<CacheMunicipiosDto> listaMuni = cacheMunicipiosService.listarMunicipios(pageSort, codProvincia);
		paginador.setInboxSize((int) listaMuni.getTotalElements());

		LOGGER.info("Transformamos el Page de Municipios a un listado");
		resultado.put("Municipios", listaMuni.stream().map(list -> list).toList());
		resultado.put("Paginador", paginador);
	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}		
	
	@GetMapping(value = "/filtradoAntenas")
	public ResponseEntity<Object> localizarAntenas(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = {"localidad", "municipio"}, direction = Direction.ASC) Sort sort,
			@RequestParam(value = "codProvincia", required = false) String codProvincia,
			@RequestParam(value = "codMunicipio", required = false) String codMunicipio, 
			@RequestParam(value = "calle", required = false) String calle,
			@RequestParam(value = "numero", required = false) String numero) throws Exception {
		
		LOGGER.info("Recibiendo los datos para el filtrado de antenas, codProvincia: {}, codMunicipio: {}, calle: {},"
				+ " numero: {}", codProvincia, codMunicipio, calle, numero );
		Map<String, Object> resultado = null;
		try {
			Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
			LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
			PaginadorDto paginador = new PaginadorDto();
			utilidades.configuracionPaginador(paginador, page);
			
			String direccionCompleta = !calle.isEmpty() && !numero.isEmpty() ? calle.concat(", ").concat(numero) 
					: !calle.isEmpty() && numero.isEmpty()  ? calle : numero;
			
			resultado = localizacionAntenasService.listaAntenas(codProvincia.isEmpty() ? null : codProvincia , 
					codMunicipio.isEmpty() ? null : codMunicipio, direccionCompleta, page, paginador); 
		} catch (FiltroAntenasException e) {
			throw new FiltroAntenasException(e.getMessage(), e.getCause());
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detalleAntenas")
	public ResponseEntity<Object> localizarAntenas(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = "emplazamiento", direction = Direction.ASC) Sort sort,
			@RequestParam(value = "emplazamiento", required = false) String emplazamiento) {
		
		LOGGER.info("Recibiendo los datos para el filtrado de detalle antenas, emplazamiento: {}", emplazamiento);
		Page<DetallesAntenaDto> resultado = null;
		try {
			Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
			LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
			PaginadorDto paginador = new PaginadorDto();
			utilidades.configuracionPaginador(paginador, page);
			
			resultado = detalleAntenasService.obtenerDetalleAntenas(emplazamiento, page, paginador); 
		} catch (Exception e) {
			LOGGER.error("ERROR recuperando las estaciones {}", e.getMessage(), e.getCause());
			throw e;
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filtradoEstaciones")
	public ResponseEntity<Object> localizarEstaciones(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = {"localidad", "municipio"}, direction = Direction.ASC) Sort sort,
			@RequestParam(value = "codProvincia", required = false) String codProvincia,
			@RequestParam(value = "codMunicipio", required = false) String codMunicipio, 
			@RequestParam(value = "calle", required = false) String calle,
			@RequestParam(value = "numero", required = false) String numero,
			@RequestParam(value = "latitudini", required = false) Double latitudIni,
			@RequestParam(value = "latitudfin", required = false) Double latitudFin,
			@RequestParam(value = "longitudini", required = false) Double longitudIni,
			@RequestParam(value = "longitudfin", required = false) Double longitudFin,
			@RequestParam(value = "zoom", required = false) Integer zoom
			) throws Exception {
		
		LOGGER.info("Recibiendo los datos para el filtrado de estaciones, codProvincia: {}, codMunicipio: {}, calle: {},"
				+ " numero: {}, latitud inicial: {}, latitud final: {}, longitud inicial: {}, longitud final: {}, zoom: {}", codProvincia, codMunicipio, calle, numero, latitudIni, latitudFin, longitudIni, longitudFin, zoom );
		Map<String, Object> resultado = null;
		try {
			Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
			LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
			PaginadorDto paginador = new PaginadorDto();
			utilidades.configuracionPaginador(paginador, page);
			
			String direccionCompleta = !calle.isEmpty() && !numero.isEmpty() ? calle.concat(", ").concat(numero) 
					: !calle.isEmpty() && numero.isEmpty()  ? calle : numero;
			
			resultado = localizacionEstacionesService.listaEstaciones(codProvincia.isEmpty() ? null : codProvincia , 
					codMunicipio.isEmpty() ? null : codMunicipio, direccionCompleta, latitudIni, latitudFin, longitudIni, longitudFin, zoom, page, paginador); 
		
		} catch (FiltroAntenasException e) {
			throw new FiltroAntenasException(e.getMessage(), e.getCause());
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
