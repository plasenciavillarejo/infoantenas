package es.gob.info.ant.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.ErrorZoomDto;
import es.gob.info.ant.dto.ErrorZoomExtendidoDto;

@ControllerAdvice
public class InfoAntenasExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    	
    	Map<String, Object> errorRespuesta = new HashMap<>();
		ErrorZoomDto errorZoom = new ErrorZoomDto();
		errorZoom.setErrorCode(HttpStatus.BAD_REQUEST.value() );
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantesAplicacion.FORMATOFECHA);
		errorZoom.setError_date_time(formatter.format(new Date()));
		errorZoom.setError_type(ConstantesAplicacion.BADREQUEST);
		errorZoom.setError_description(ex.getBindingResult().getFieldError().getDefaultMessage());
		errorZoom.setError_extended_info(new ErrorZoomExtendidoDto());			
		errorZoom.getError_extended_info().setError_message(ex.getBindingResult().getFieldError().getDefaultMessage());
		errorZoom.getError_extended_info().setCorrelation_id("50c81057-7242-41c8-8663-bf40ae675a8b");
		errorZoom.getError_extended_info().setError_id(HttpStatus.BAD_REQUEST.value());
		errorZoom.getError_extended_info().setError_alias("");		
		errorRespuesta.put(ConstantesAplicacion.ERROR, errorZoom);
    	
        return new ResponseEntity<>(errorRespuesta,HttpStatus.BAD_REQUEST);
    }
	   
    @ExceptionHandler(FiltroEstacionesException.class)
    public ResponseEntity<Object> handleControlExceptions(FiltroEstacionesException ex) {        
		Map<String, Object> errorRespuesta = new HashMap<>();
		ErrorZoomDto errorZoom = new ErrorZoomDto();
		errorZoom.setErrorCode(HttpStatus.UNAUTHORIZED.value());
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantesAplicacion.FORMATOFECHA);
		errorZoom.setError_date_time(formatter.format(new Date()));
		errorZoom.setError_type("Bad Request");
		errorZoom.setError_description("Zoom Invalid");
		errorZoom.setError_extended_info(new ErrorZoomExtendidoDto());			
		errorZoom.getError_extended_info().setError_message("Zoom Invalid" );
		errorZoom.getError_extended_info().setCorrelation_id("50c81057-7242-41c8-8663-bf40ae675a8b");
		errorZoom.getError_extended_info().setError_id(HttpStatus.UNAUTHORIZED.value());
		errorZoom.getError_extended_info().setError_alias("");		
		errorRespuesta.put(ConstantesAplicacion.ERROR, errorZoom);
    	return new ResponseEntity<>(errorRespuesta,HttpStatus.UNAUTHORIZED);
    }
	
    @ExceptionHandler(ErrorGlobalAntenasException.class)
    public ResponseEntity<Object> handleErrorGlobalExceptions(ErrorGlobalAntenasException ex) {
    	Map<String, Object> errorRespuesta = new HashMap<>();
		ErrorZoomDto errorZoom = new ErrorZoomDto();
		errorZoom.setErrorCode(HttpStatus.BAD_REQUEST.value());
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantesAplicacion.FORMATOFECHA);
		errorZoom.setError_date_time(formatter.format(new Date()));
		errorZoom.setError_type("Bad Request");
		errorZoom.setError_description(ex.getMessage());
		errorZoom.setError_extended_info(new ErrorZoomExtendidoDto());			
		errorZoom.getError_extended_info().setError_message(ex.getMessage());
		errorZoom.getError_extended_info().setCorrelation_id("50c81057-7242-41c8-8663-bf40ae675a8b");
		errorZoom.getError_extended_info().setError_id(HttpStatus.BAD_REQUEST.value());
		errorZoom.getError_extended_info().setError_alias("");		
		errorRespuesta.put(ConstantesAplicacion.ERROR, errorZoom);
    	return new ResponseEntity<>(errorRespuesta,HttpStatus.BAD_REQUEST);
    }

}
