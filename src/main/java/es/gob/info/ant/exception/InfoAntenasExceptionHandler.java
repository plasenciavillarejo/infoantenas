package es.gob.info.ant.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
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
		errorZoom.setErrorDateTime(formatter.format(new Date()));
		errorZoom.setErrorType(ConstantesAplicacion.BADREQUEST);
		FieldError fieldError = ex.getBindingResult().getFieldError();
		if (fieldError != null) {
		    errorZoom.setErrorDescription(fieldError.getDefaultMessage());
		} else {
		    errorZoom.setErrorDescription("Error desconocido");
		}
		errorZoom.setErrorExtendedInfo(new ErrorZoomExtendidoDto());	
		if (fieldError != null) {
		    errorZoom.getErrorExtendedInfo().setErrorMessage(fieldError.getDefaultMessage());
		} else {
		    errorZoom.getErrorExtendedInfo().setErrorMessage("Error desconocido");
		}
		errorZoom.getErrorExtendedInfo().setCorrelationId(ConstantesAplicacion.CORRELATIONID);
		errorZoom.getErrorExtendedInfo().setErrorId(HttpStatus.BAD_REQUEST.value());
		errorZoom.getErrorExtendedInfo().setErrorAlias("");		
		errorRespuesta.put(ConstantesAplicacion.ERROR, errorZoom);
    	
        return new ResponseEntity<>(errorRespuesta,HttpStatus.BAD_REQUEST);
    }
	   
    @ExceptionHandler(FiltroEstacionesException.class)
    public ResponseEntity<Object> handleControlExceptions(FiltroEstacionesException ex) {        
		Map<String, Object> errorRespuesta = new HashMap<>();
		ErrorZoomDto errorZoom = new ErrorZoomDto();
		errorZoom.setErrorCode(HttpStatus.UNAUTHORIZED.value());
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantesAplicacion.FORMATOFECHA);
		errorZoom.setErrorDateTime(formatter.format(new Date()));
		errorZoom.setErrorType("Bad Request");
		errorZoom.setErrorDescription("Zoom Invalid");
		errorZoom.setErrorExtendedInfo(new ErrorZoomExtendidoDto());			
		errorZoom.getErrorExtendedInfo().setErrorMessage("Zoom Invalid" );
		errorZoom.getErrorExtendedInfo().setCorrelationId(ConstantesAplicacion.CORRELATIONID);
		errorZoom.getErrorExtendedInfo().setErrorId(HttpStatus.UNAUTHORIZED.value());
		errorZoom.getErrorExtendedInfo().setErrorAlias("");		
		errorRespuesta.put(ConstantesAplicacion.ERROR, errorZoom);
    	return new ResponseEntity<>(errorRespuesta,HttpStatus.UNAUTHORIZED);
    }
	
    @ExceptionHandler(ErrorGlobalAntenasException.class)
    public ResponseEntity<Object> handleErrorGlobalExceptions(ErrorGlobalAntenasException ex) {
    	Map<String, Object> errorRespuesta = new HashMap<>();
		ErrorZoomDto errorZoom = new ErrorZoomDto();
		errorZoom.setErrorCode(HttpStatus.BAD_REQUEST.value());
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantesAplicacion.FORMATOFECHA);
		errorZoom.setErrorDateTime(formatter.format(new Date()));
		errorZoom.setErrorType("Bad Request");
		errorZoom.setErrorDescription(ex.getMessage());
		errorZoom.setErrorExtendedInfo(new ErrorZoomExtendidoDto());			
		errorZoom.getErrorExtendedInfo().setErrorMessage(ex.getMessage());
		errorZoom.getErrorExtendedInfo().setCorrelationId(ConstantesAplicacion.CORRELATIONID);
		errorZoom.getErrorExtendedInfo().setErrorId(HttpStatus.BAD_REQUEST.value());
		errorZoom.getErrorExtendedInfo().setErrorAlias("");		
		errorRespuesta.put(ConstantesAplicacion.ERROR, errorZoom);
    	return new ResponseEntity<>(errorRespuesta,HttpStatus.BAD_REQUEST);
    }

}
