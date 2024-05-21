package es.gob.info.ant.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class ErrorZoomDto implements Serializable {
	
	private int errorCode;
	
	private String error_date_time;
	
	private String error_type;
	
	private String error_description;
	
	private ErrorZoomExtendidoDto error_extended_info;
	
	private static final long serialVersionUID = 2930853595657197569L;

}
