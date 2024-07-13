package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorZoomExtendidoDto implements Serializable{
	
	private String errorMessage;
	
	private String correlationId;
	
	private int errorId;
	
	private String errorAlias;
	
	private static final long serialVersionUID = 4706554041621855884L;

}
