package es.gob.info.ant.serviceimpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.FiltradoAntenasDto;
import es.gob.info.ant.dto.ListaDatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.ListaNivelesMediosDto;
import es.gob.info.ant.dto.NivelesMediosDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.exception.ErrorGlobalAntenasException;
import es.gob.info.ant.exception.FiltroAntenasException;
import es.gob.info.ant.models.service.IEmplazamientosService;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.models.service.IMedicionesService;
import es.gob.info.ant.service.ILocalizacionAntenasService;
import es.gob.info.ant.util.Utilidades;

@Service
public class LocalizacionAntenasServiceImpl implements ILocalizacionAntenasService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantesAplicacion.CONSTANTELOGGUERINFOANTENAS);

	@Autowired
	private IEmplazamientosService emplazamientoService;

	@Autowired
	private IEstacionesService estacionesService;

	@Autowired
	private IMedicionesService medicioneService;

	@Autowired
	private Utilidades utilidades;
	
	@Override
	public Map<String, Object> listaAntenas(Long codProvincia, Long codMunicipio, String calle, Pageable page,
			PaginadorDto paginador) throws FiltroAntenasException, ErrorGlobalAntenasException {		
		Page<Object []> emplazamientos = null;
		Map<String, Object> param = new HashMap<>();
		List<FiltradoAntenasDto> emplDto = null;
		LOGGER.info("Se procede a buscar los emplazamientos");
		try {
			LOGGER.info("Buscando desde la pagina: {} hasta la página: {} ", page.getPageNumber(), page.getPageSize());
			emplazamientos = emplazamientoService.listaEmplazamientos(codProvincia, codMunicipio,calle, page);
			
			LOGGER.info("Se han encontrado un total de {} registros", emplazamientos.getNumberOfElements());
			
			LOGGER.info("Configurando el tampo del paginador");
			paginador.setRegistros((int)emplazamientos.getTotalElements());
			param.put("Paginador", paginador);
			
			LOGGER.info("Obtenemos los emplazamientos");
			emplDto = emplazamientos.stream().map(empl -> {
				FiltradoAntenasDto em = new FiltradoAntenasDto();							
				em.setEmplazamiento(empl[0] != null ? String.valueOf(empl[0]): "");
				em.setDireccion(empl[1] != null ? String.valueOf(empl[1]): "");
				em.setLocalidad(empl[2] != null ? String.valueOf(empl[2]): "");
				em.setMunicipio(empl[3] != null ? String.valueOf(empl[3]): "");
				em.setProvincia(empl[4] != null ? String.valueOf(empl[4]): "");
				em.setLatitud(empl[5] != null ?  new BigDecimal(String.valueOf(empl[5])) : null);
				em.setLongitud(empl[6] != null ?  new BigDecimal(String.valueOf(empl[6])) : null);
				completarFiltradoAntenas(em, empl);
				return em;
			}).toList();
			
			utilidades.completarDatosCaracteristicaYNivelesMedios(emplDto, estacionesService, medicioneService);
			
		} catch (Exception e) {
			throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
		}
		param.put("emplazamientos", emplDto);
		return param;
	}
	
	private void completarFiltradoAntenas(FiltradoAntenasDto em, Object [] empl) {
		em.setFechaActualizacion(empl[7] != null ? String.valueOf(empl[7]): "");
		em.setObservaciones(!String.valueOf(empl[8]).trim().isEmpty() ? String.valueOf(empl[8]).trim() : "");	
	}
	
	@Override
	public Map<String, Object> obtenerDetalleEstacion(String emplazamiento) throws Exception {		
		Object emplazamientos = null;
		Map<String, Object> param = new HashMap<>();
		AtomicReference<FiltradoAntenasDto> emplDto = new AtomicReference<>(new FiltradoAntenasDto());
		LOGGER.info("Se procede a buscar los emplazamientos");
		try {
			emplazamientos = emplazamientoService.obtenerDetalleEstacion(emplazamiento);
			Object[] empl = (Object[]) emplazamientos;
			
			if(empl != null) {
				LOGGER.info("Se han encontrado un elemento con id emplazamiento {}", emplazamiento);
				emplDto.get().setEmplazamiento(empl[0] != null ? String.valueOf(empl[0]): "");
				emplDto.get().setDireccion(empl[1] != null ? String.valueOf(empl[1]): "");
				emplDto.get().setLocalidad(empl[2] != null ? String.valueOf(empl[2]): "");
				emplDto.get().setMunicipio(empl[3] != null ? String.valueOf(empl[3]): "");
				completarDestalleEstacion(emplDto, empl);

				LOGGER.info("Se procede a recuperar las Características Técnicas asociada a las estaciones "); 
				CompletableFuture<CompletableFuture<List<ListaDatosCaracteristicasTecnicasDto>>> datosCaracteristicasTecnicas =
			                CompletableFuture.supplyAsync(() ->
			                        estacionesService.listadoEstaciones(Arrays.asList(String.valueOf(empl[0]))));
				 
				LOGGER.info("Se procede a recuperar los Niveles Medios");
				CompletableFuture<CompletableFuture<List<ListaNivelesMediosDto>>> nivelesMediosDto = 
						CompletableFuture.supplyAsync(() ->
							medicioneService.listarMediciones(Arrays.asList(String.valueOf(empl[0]))));
				
				 // Esperar a que ambas consultas se completen
				CompletableFuture<Void> compFuture = datosCaracteristicasTecnicas.thenCombine(nivelesMediosDto,
		                (caracteristicasTecnicas, nivelesMedios) -> {
		                    try {
								emplDto.get().setDatosCaracteristicasTecnicas(caracteristicasTecnicas.get().stream()
								        .map(DatosCaracteristicasTecnicasDto::new).toList());
					            emplDto.get().setNivelesMedios(nivelesMedios.get().stream()
					            		.filter(niv -> !niv.getValorMedio().contains("<ERR"))
			                            .map(NivelesMediosDto::new).toList());
							} catch (InterruptedException | ExecutionException e) {
								throw new ThreadDeath();
							}
		                    return null;
		                });
				compFuture.join();
			}else {
				LOGGER.info("No se ha encontrado el emplazamiento con id emplazamiento: {}", emplazamiento);
			}
		} catch (Exception e) {
			throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
		}
		param.put("emplazamientos", emplDto);
		return param;
	}

	private void completarDestalleEstacion(AtomicReference<FiltradoAntenasDto> emplDto,Object[] empl) {
		emplDto.get().setProvincia(empl[4] != null ? String.valueOf(empl[4]): "");
		emplDto.get().setLatitud(empl[5] != null ?  new BigDecimal(String.valueOf(empl[5])) : null);
		emplDto.get().setLongitud(empl[6] != null ?  new BigDecimal(String.valueOf(empl[6])) : null);
		emplDto.get().setFechaActualizacion(empl[7] != null ? String.valueOf(empl[7]): "");
		emplDto.get().setObservaciones(!String.valueOf(empl[8]).trim().isEmpty() ? String.valueOf(empl[8]).trim() : "");
	}
	
}
