package es.gob.info.ant.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.FiltradoAntenasDto;
import es.gob.info.ant.dto.ListaDatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.ListaNivelesMediosDto;
import es.gob.info.ant.dto.NivelesMediosDto;
import es.gob.info.ant.dto.PaginadorDto;
import es.gob.info.ant.dto.ParametrosAntenasDto;
import es.gob.info.ant.exception.ErrorGlobalAntenasException;
import es.gob.info.ant.exception.RuntimeGenericoException;
import es.gob.info.ant.models.service.IEstacionesService;
import es.gob.info.ant.models.service.IMedicionesService;

@Component
public class Utilidades {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantesAplicacion.CONSTANTELOGGUERINFOANTENAS);
	
	public void configuracionPaginador(PaginadorDto paginador, Pageable page) {
		paginador.setPaginaActual(page.getPageNumber() + 1);
		paginador.setTamanioPagina(page.getPageSize());
	}
	
	public Pageable configurarPageRequest(int pagina, int tamanioPagina, int orden, String campo) throws ErrorGlobalAntenasException {
		Pageable pag = null;
		try {
			pag =  PageRequest.of(pagina -1, tamanioPagina, orden == 1 ? Sort.by(campo).ascending() 
						: Sort.by(campo).descending());
		} catch (Exception e) {
			throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
		}
		  return pag;
	}

	public void completarDatosCaracteristicaYNivelesMedios(List<FiltradoAntenasDto> emplDto,IEstacionesService estacionesService,
			IMedicionesService medicioneService) {
		LOGGER.info("Rellenamos las caracteristicas tecnicas");
        CompletableFuture<CompletableFuture<List<ListaDatosCaracteristicasTecnicasDto>>> datosCaracteristicasTecnicas =
                CompletableFuture.supplyAsync(() ->
                        estacionesService.listadoEstaciones(emplDto.stream()
                                .map(FiltradoAntenasDto::getEmplazamiento).toList()));
		
		LOGGER.info("Rellenamos los niveles medios");
		CompletableFuture<CompletableFuture<List<ListaNivelesMediosDto>>> nivelesMediosDto = 
				CompletableFuture.supplyAsync(() ->
					medicioneService.listarMediciones(emplDto.stream()
							.map(em -> em.getEmplazamiento()).toList()));
				
	    CompletableFuture<Void> compFuture = datosCaracteristicasTecnicas.thenCombineAsync(nivelesMediosDto, (caracteristicasTecnicas, nivelesMedios) -> {
	    	emplDto.forEach(em -> {
	        	try {
					em.setDatosCaracteristicasTecnicas(caracteristicasTecnicas.get().stream()
						    .filter(da -> da.getEmplazamiento().equals(em.getEmplazamiento()))
						    .map(DatosCaracteristicasTecnicasDto::new)
						    .toList());
					em.setNivelesMedios(nivelesMedios.get().stream()
		    			    .filter(da -> da.getEmplazamiento().equals(em.getEmplazamiento()) && !da.getValorMedio().contains("<ERR"))
		    			    .map(NivelesMediosDto::new)
		    			    .toList());
				} catch (InterruptedException | ExecutionException e) {
					Thread.currentThread().interrupt();
					throw new RuntimeGenericoException(e.getMessage(), e.getCause());
				} catch (Exception ex) {
					throw new RuntimeGenericoException(ex.getMessage(), ex.getCause());
				}
	        });
	        return null;
	    });	    
	    // Espera a que el compFuture se complete
	    compFuture.join();
	}
	
	public String obtenerDireccionCompleta(ParametrosAntenasDto parametrosDto) {
		return parametrosDto.getDireccion() != null && parametrosDto.getNumero() != null
				 && !parametrosDto.getDireccion().isEmpty() && !parametrosDto.getNumero().isEmpty() 
			? parametrosDto.getDireccion().concat(", ").concat(parametrosDto.getNumero()) 
			: contiDirecCompleta(parametrosDto);
	}
	
	public String contiDirecCompleta(ParametrosAntenasDto parametrosDto) {
		return parametrosDto.getDireccion() != null  && !parametrosDto.getDireccion().isEmpty() 
				&& (parametrosDto.getNumero() == null || parametrosDto.getNumero().isEmpty())
				? parametrosDto.getDireccion() : parametrosDto.getNumero();
	}
}
