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

import es.gob.info.ant.dto.ErrorZoomDto;
import es.gob.info.ant.dto.ErrorZoomExtendidoDto;

@ControllerAdvice
public class InfoAntenasExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Error de validación: ".concat(ex.getBindingResult().getFieldError().getDefaultMessage()));
    }
	   
    @ExceptionHandler(FiltroEstacionesException.class)
    public ResponseEntity<Object> handleControlExceptions(FiltroEstacionesException ex) {        
		Map<String, Object> errorRespuesta = new HashMap<>();
		ErrorZoomDto errorZoom = new ErrorZoomDto();
		errorZoom.setErrorCode(HttpStatus.UNAUTHORIZED.value());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		errorZoom.setError_date_time(formatter.format(new Date()));
		errorZoom.setError_type("Bad Request");
		errorZoom.setError_description("Zoom Invalid");
		errorZoom.setError_extended_info(new ErrorZoomExtendidoDto());			
		errorZoom.getError_extended_info().setError_message("Zoom Invalid" );
		errorZoom.getError_extended_info().setCorrelation_id("50c81057-7242-41c8-8663-bf40ae675a8b");
		errorZoom.getError_extended_info().setError_id(HttpStatus.UNAUTHORIZED.value());
		errorZoom.getError_extended_info().setError_alias("");		
		errorRespuesta.put("error", errorZoom);
    	return new ResponseEntity<>(errorRespuesta,HttpStatus.UNAUTHORIZED);
    }
	
    @ExceptionHandler(ErrorGlobalAntenasException.class)
    public ResponseEntity<Object> handleErrorGlobalExceptions(ErrorGlobalAntenasException ex) {
    	Map<String, Object> errorRespuesta = new HashMap<>();
		ErrorZoomDto errorZoom = new ErrorZoomDto();
		errorZoom.setErrorCode(HttpStatus.BAD_REQUEST.value() );
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		errorZoom.setError_date_time(formatter.format(new Date()));
		errorZoom.setError_type("Bad Request");
		errorZoom.setError_description("Invalid field codProvincia");
		errorZoom.setError_extended_info(new ErrorZoomExtendidoDto());			
		errorZoom.getError_extended_info().setError_message("Invalid field codProvincia");
		errorZoom.getError_extended_info().setCorrelation_id("50c81057-7242-41c8-8663-bf40ae675a8b");
		errorZoom.getError_extended_info().setError_id(HttpStatus.BAD_REQUEST.value());
		errorZoom.getError_extended_info().setError_alias("");		
		errorRespuesta.put("error", errorZoom);
    	return new ResponseEntity<>(errorRespuesta,HttpStatus.BAD_REQUEST);
    }

}
