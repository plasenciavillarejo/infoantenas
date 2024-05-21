package es.gob.info.ant.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import es.gob.info.ant.dto.ErrorZoomDto;
import es.gob.info.ant.dto.ErrorZoomExtendidoDto;
import es.gob.info.ant.dto.PaginadorDto;

@Component
public class Utilidades {

	public void configuracionPaginador(PaginadorDto paginador, Pageable page) {
		paginador.setPaginaActual(page.getPageNumber() + 1);
		paginador.setTamanioPagina(page.getPageSize());
	}

	public Map<String, Object> errorGlobal(boolean zoom) {
		Map<String, Object> errorRespuesta = new HashMap<>();
		ErrorZoomDto errorZoom = new ErrorZoomDto();
		errorZoom.setErrorCode(zoom ? HttpStatus.UNAUTHORIZED.value() : HttpStatus.BAD_REQUEST.value() );
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		errorZoom.setError_date_time(formatter.format(new Date()));
		errorZoom.setError_type("Bad Request");
		errorZoom.setError_description(zoom ? "Zoom Invalid" : "Invalid field codProvincia");
		errorZoom.setError_extended_info(new ErrorZoomExtendidoDto());			
		errorZoom.getError_extended_info().setError_message(zoom ? "Zoom Invalid" : "Invalid field codProvincia");
		errorZoom.getError_extended_info().setCorrelation_id("50c81057-7242-41c8-8663-bf40ae675a8b");
		errorZoom.getError_extended_info().setError_id(zoom ? HttpStatus.UNAUTHORIZED.value() : HttpStatus.BAD_REQUEST.value());
		errorZoom.getError_extended_info().setError_alias("");		
		errorRespuesta.put("error", errorZoom);
		return errorRespuesta;
	}

	
}
