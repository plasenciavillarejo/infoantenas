package es.gob.info.ant.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.CacheMunicipiosDto;
import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.dto.ParametrosAntenasDto;
import es.gob.info.ant.exception.ErrorGlobalAntenasException;
import es.gob.info.ant.exception.FiltroAntenasException;
import es.gob.info.ant.exception.FiltroEstacionesException;
import es.gob.info.ant.models.service.ICacheMunicipiosService;
import es.gob.info.ant.models.service.ICacheProvinciasService;
import es.gob.info.ant.service.ILocalizacionAntenasService;
import es.gob.info.ant.service.ILocalizacionEstacionesService;
import es.gob.info.ant.service.IProvinciasService;
import es.gob.info.ant.util.Utilidades;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

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
	private Utilidades utilidades;
	
	@GetMapping(value = "/listadoProvincias")
	public ResponseEntity<Object> listarProvincias() throws ErrorGlobalAntenasException {
		Map<String, Object> resultado = new LinkedHashMap<>();
		try {
			List<CacheProvinciasDto> listaProvinciasDto = provinciasCacheService.listarProvincias().stream().map(list -> {
				CacheProvinciasDto provinDto = new CacheProvinciasDto();
				provinDto.setCodProvincia(Long.valueOf(String.valueOf(list[0])));
				provinDto.setNombreProvincia(String.valueOf(list[1]));
				provinDto.setSiglaProvincia(proviciasService.siglasProvincias(String.valueOf(list[1])));
				return provinDto;
			}).toList();	
			resultado.put("Provincias INE", listaProvinciasDto);
		} catch (Exception e) {
			throw new ErrorGlobalAntenasException();
		}
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listadoMunicipios")
	public ResponseEntity<Object> listarMunicipios(@Valid @RequestBody ParametrosAntenasDto parametrosDto) throws ErrorGlobalAntenasException {	
		Map<String, Object> resultado = new LinkedHashMap<>();
		try {
			Pageable page = PageRequest.of(parametrosDto.getPagina() -1, parametrosDto.getTamanopagina(),parametrosDto.getOrden() == 1 
					? Sort.by(ConstantesAplicacion.MUNICIPIO).ascending() 
							: Sort.by(ConstantesAplicacion.MUNICIPIO).descending());
			LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
			PaginadorDto paginador = new PaginadorDto();
			utilidades.configuracionPaginador(paginador, page);
			
			LOGGER.info("Se procede a listar los municipos asociados a las provincias");
			Page<Object []> listaMuncipios = cacheMunicipiosService.listarMunicipios(page, parametrosDto.getCodProvincia());
			
			paginador.setRegistros((int) listaMuncipios.getTotalElements());
			
			List<CacheMunicipiosDto> listaMuncipiosDto = listaMuncipios.stream().map(muni -> {
				CacheMunicipiosDto municiposDto = new CacheMunicipiosDto();
				/* Nos aseguramos de que cualquier número se formatee con al menos 2 dígitos para provincias y 3 para municipios,
					rellenando con ceros a la izquierda si es necesario. */
				municiposDto.setCodProvincia(String.format("%02d", Long.valueOf(String.valueOf(muni[0]))));
				municiposDto.setCodMunicipio(String.format("%03d", Long.valueOf(String.valueOf(muni[1]))));
				municiposDto.setNombreMunicipo(String.valueOf(muni[2]));
				return municiposDto;
			}).toList();
			resultado.put("Municipios INE", listaMuncipiosDto);
			resultado.put("Paginador", paginador);
		} catch (Exception e) {
			throw new ErrorGlobalAntenasException();
		}
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}		
	
	@GetMapping(value = "/filtradoAntenas")
	public ResponseEntity<Object> localizarAntenas(
			@RequestParam(value = "codProvincia", required = true) String codProvincia,
			@RequestParam(value = "codMunicipio", required = true) String codMunicipio, 
			@RequestParam(value = "direccion", required = false) String direccion,
			@RequestParam(value = "numero", required = false) String numero,
			@RequestParam(value = "pagina") int pagina,
			@RequestParam(value = "tamanioPagina") int tamanioPagina,
			@RequestParam(value = "ordenacion", required = true) int ordenacion,
			@RequestParam(value = "orden", required = true) int orden) throws ErrorGlobalAntenasException {
		
		LOGGER.info("Recibiendo los datos para el filtrado de antenas, codProvincia: {}, codMunicipio: {}, calle: {},"
				+ " numero: {}", codProvincia, codMunicipio, direccion, numero );
		Map<String, Object> resultado = null;
		try {
					
			Pageable page = PageRequest.of(pagina -1, tamanioPagina,orden == 1 ? Sort.by("direccion").ascending(): Sort.by("direccion").descending());
			LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
			
			PaginadorDto paginador = new PaginadorDto();
			utilidades.configuracionPaginador(paginador, page);
			
			String direccionCompleta = !direccion.isEmpty() && !numero.isEmpty() ? direccion.concat(", ").concat(numero) 
					: !direccion.isEmpty() && numero.isEmpty()  ? direccion : numero;
			
			resultado = localizacionAntenasService.listaAntenas(codProvincia , codMunicipio, direccionCompleta, page, paginador); 
		} catch (FiltroAntenasException e) {
			throw new ErrorGlobalAntenasException();
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detalleAntenas")
	public ResponseEntity<Object> localizarAntenas(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = "emplazamiento", direction = Direction.ASC) Sort sort,
			@RequestParam(value = "idAntena", required = true) String emplazamiento) throws ErrorGlobalAntenasException {
		
		LOGGER.info("Recibiendo los datos para el filtrado de detalle antenas, emplazamiento: {}", emplazamiento);
		Map<String, Object> resultado = null;
		try {
			Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
			LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
			PaginadorDto paginador = new PaginadorDto();
			utilidades.configuracionPaginador(paginador, page);
			
			resultado = localizacionAntenasService.obtenerDetalleEstacion(emplazamiento); 
		} catch (ErrorGlobalAntenasException e) {
			throw new ErrorGlobalAntenasException();
		}
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filtradoEstaciones")
	public ResponseEntity<Object> localizarEstaciones(@PageableDefault(page = 1, size = 10) Pageable pageable,
			@SortDefault(sort = {"localidad", "municipio"}, direction = Direction.ASC) Sort sort,
			@RequestParam(value = "lat", required = false) Double latitud,
			@RequestParam(value = "long", required = false) Double longitud,
			@RequestParam(value = "radio", required = false) Double radio,
			@RequestParam(value = "zoom", required = false) Integer zoom,
			HttpServletResponse response) throws FiltroEstacionesException {
		
		LOGGER.info("Recibiendo los datos para el filtrado de estaciones, latitud: {}, longitud: {}, radio: {},"
				+ " zoom: {}", latitud, longitud, radio, zoom);
		Map<String, Object> resultado = null;
		try {
			if(zoom != null && zoom <= 20) {
				Pageable page = PageRequest.of(pageable.getPageNumber() -1, pageable.getPageSize(), sort);
				LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
				PaginadorDto paginador = new PaginadorDto();
				utilidades.configuracionPaginador(paginador, page);
				// El radio nos lo pasan en metros, nosotros lo necesitamos en kilometros
				resultado = localizacionEstacionesService.listaEstaciones(latitud, longitud, radio/1000, page, paginador); 
			}else {
				LOGGER.error("El zoom indicado esta fuera del rango permitido");
				throw new FiltroEstacionesException("El zoom indicado esta fuera del rango permitido");		
			}
		} catch (FiltroEstacionesException e) {
			throw new FiltroEstacionesException(e.getMessage(), e.getCause());
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
}
