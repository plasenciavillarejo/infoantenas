package es.gob.info.ant.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.CacheMunicipiosDto;
import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.dto.ParametrosAntenasDto;
import es.gob.info.ant.dto.ParametrosDetalleAntenasDto;
import es.gob.info.ant.dto.ParametrosFiltradoEstacionesDto;
import es.gob.info.ant.dto.ParametrosMunicipiosDto;
import es.gob.info.ant.exception.ErrorGlobalAntenasException;
import es.gob.info.ant.exception.FiltroAntenasException;
import es.gob.info.ant.exception.FiltroEstacionesException;
import es.gob.info.ant.models.service.ICacheMunicipiosService;
import es.gob.info.ant.models.service.ICacheProvinciasService;
import es.gob.info.ant.service.ILocalizacionAntenasService;
import es.gob.info.ant.service.ILocalizacionEstacionesService;
import es.gob.info.ant.service.IProvinciasService;
import es.gob.info.ant.util.Utilidades;
import jakarta.validation.Valid;

@RestController
public class AntenasController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantesAplicacion.CONSTANTELOGGUERINFOANTENAS); 
	
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
			LOGGER.info("Se procede a listar las provincias");
			List<CacheProvinciasDto> listaProvinciasDto = provinciasCacheService.listarProvincias().stream().map(list -> {
				CacheProvinciasDto provinDto = new CacheProvinciasDto();
				provinDto.setCodProvincia(Long.valueOf(String.valueOf(list[0])));
				provinDto.setNombreProvincia(String.valueOf(list[1]));
				provinDto.setSiglaProvincia(proviciasService.siglasProvincias(String.valueOf(list[1])));
				return provinDto;
			}).toList();
			LOGGER.info("Se ha completa la busqueda las provincias");
			resultado.put("Provincias INE", listaProvinciasDto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e.getCause());
			throw new ErrorGlobalAntenasException(e.getCause());
		}
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listadoMunicipios")
	public ResponseEntity<Object> listarMunicipios(@Valid @RequestBody ParametrosMunicipiosDto parametrosDto) throws ErrorGlobalAntenasException {	
		Map<String, Object> resultado = new LinkedHashMap<>();
		try {
			Pageable page = null;
			LOGGER.info("Se procede a listar los municipos asocidados a: {}", parametrosDto.getCodProvincia());
			try {
				page = utilidades.configurarPageRequest(parametrosDto.getPagina(), parametrosDto.getTamanioPagina(), 
						parametrosDto.getOrden(), ConstantesAplicacion.MUNICIPIO);						
			} catch (Exception e) {
				throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
			}
			LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
			PaginadorDto paginador = new PaginadorDto();
			utilidades.configuracionPaginador(paginador, page);
			
			Page<Object []> listaMuncipios = null;
			try {
				listaMuncipios = cacheMunicipiosService.listarMunicipios(page, parametrosDto.getCodProvincia());
				LOGGER.info("Busqueda para la página: {} se ha encontrado un total de {} municipos ", parametrosDto.getPagina(), listaMuncipios.getSize());
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e.getCause());
				throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
			}
			paginador.setRegistros((int) listaMuncipios.getTotalElements());
			List<CacheMunicipiosDto> listaMuncipiosDto = null;
			try  {
				LOGGER.info("Se procede almacenar la información dentro del dto");
				listaMuncipiosDto = listaMuncipios.stream().map(muni -> {
				CacheMunicipiosDto municiposDto = new CacheMunicipiosDto();
				municiposDto.setCodMunicipio(String.format("%03d", Long.valueOf(String.valueOf(muni[0]))));
				municiposDto.setNombreMunicipo(String.valueOf(muni[1]));
				return municiposDto;
			}).toList();
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e.getCause());
				throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
			}
			LOGGER.info("Se ha completado la busqueda de las municipios");
			resultado.put("Municipios INE", listaMuncipiosDto);
			resultado.put("Paginador", paginador);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e.getCause());
			throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
		}
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}		
	
	@GetMapping(value = "/filtradoAntenas")
	public ResponseEntity<Object> localizarAntenas(@Valid @RequestBody ParametrosAntenasDto parametrosDto) throws ErrorGlobalAntenasException {		
		LOGGER.info("Recibiendo los datos para el filtrado de antenas, codProvincia: {}, codMunicipio: {}, calle: {},"
				+ " numero: {}", parametrosDto.getCodProvincia(), parametrosDto.getCodMunicipio(), 
				parametrosDto.getDireccion(), parametrosDto.getNumero() );
		Map<String, Object> resultado = null;
		try {
			Pageable page = null;
			try {
				page = utilidades.configurarPageRequest(parametrosDto.getPagina(), parametrosDto.getTamanioPagina(), 
						parametrosDto.getOrden(), ConstantesAplicacion.DIRECCION);						
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e.getCause());
				throw new FiltroAntenasException(e.getMessage(), e.getCause());
			}
			
			LOGGER.info(ConstantesAplicacion.CONFIGURACIONPAGINADOR);
			PaginadorDto paginador = new PaginadorDto();
			utilidades.configuracionPaginador(paginador, page);
			
			String direccionCompleta = !parametrosDto.getDireccion().isEmpty() && !parametrosDto.getNumero().isEmpty() 
					? parametrosDto.getDireccion().concat(", ").concat(parametrosDto.getNumero()) 
					: !parametrosDto.getDireccion().isEmpty() && parametrosDto.getNumero().isEmpty() 
					? parametrosDto.getDireccion() : parametrosDto.getNumero();
			LOGGER.info(!direccionCompleta.isEmpty() ? "Filtrado de estaciones por la dirección: {}" : "Filtrado de estaciones sin dirección.",  direccionCompleta);
			try {
				LOGGER.info("Se procede a reliazar el filtrado");
				resultado = localizacionAntenasService.listaAntenas(parametrosDto.getCodProvincia(),parametrosDto.getCodMunicipio(),
						direccionCompleta, page, paginador);
				LOGGER.info("Se ha obtenido un total de {} registro/s", resultado.size());
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e.getCause());
				throw new FiltroAntenasException(e.getMessage(), e.getCause());
			}
		} catch (FiltroAntenasException e) {
			LOGGER.error(e.getMessage(), e.getCause());
			throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detalleAntenas")
	public ResponseEntity<Object> localizarAntenas(@Valid @RequestBody ParametrosDetalleAntenasDto parametrosDto) throws Exception {
		
		LOGGER.info("Recibiendo los datos para el filtrado de detalle antenas, emplazamiento: {}", parametrosDto.getIdAntena());
		Map<String, Object> resultado = null;
		try {
			resultado = localizacionAntenasService.obtenerDetalleEstacion(parametrosDto.getIdAntena());
		} catch (ErrorGlobalAntenasException e) {
			LOGGER.error(e.getMessage(), e.getCause());
			throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
		}
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
	@GetMapping(value = "/filtradoEstaciones")
	public ResponseEntity<Object> localizarEstaciones(@Valid @RequestBody ParametrosFiltradoEstacionesDto parametrosDto) throws FiltroEstacionesException {
		
		LOGGER.info("Recibiendo los datos para el filtrado de estaciones, latitud: {}, longitud: {}, radio: {},"
				+ " zoom: {}", parametrosDto.getLatitud(), parametrosDto.getLongitud(), parametrosDto.getRadio(), parametrosDto.getZoom());
		Map<String, Object> resultado = null;
		try {
			if(parametrosDto.getZoom() != null && parametrosDto.getZoom() <= ConstantesAplicacion.ZOOMPERMITODO) {
				// El radio nos lo pasan en metros, nosotros lo necesitamos en kilometros
				resultado = localizacionEstacionesService.listaEstaciones(parametrosDto.getLatitud(), parametrosDto.getLongitud(), parametrosDto.getRadio()/1000); 
			}else {
				LOGGER.error("El zoom indicado esta fuera del rango permitido: {}", ConstantesAplicacion.ZOOMPERMITODO);
				throw new FiltroEstacionesException("El zoom indicado esta fuera del rango permitido: " + ConstantesAplicacion.ZOOMPERMITODO);		
			}
		} catch (FiltroEstacionesException e) {
			LOGGER.error(e.getMessage(), e.getCause());
			throw new FiltroEstacionesException(e.getMessage(), e.getCause());
		}	
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
	
}
