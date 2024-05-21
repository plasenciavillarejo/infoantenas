package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorZoomExtendidoDto implements Serializable{
	
	private String error_message;
	
	private String correlation_id;
	
	private int error_id;
	
	private String error_alias;
	
	private static final long serialVersionUID = 4706554041621855884L;

}
