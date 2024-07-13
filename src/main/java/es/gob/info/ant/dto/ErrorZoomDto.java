package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ErrorZoomDto implements Serializable {
	
	private int errorCode;
	
	private String errorDateTime;
	
	private String errorType;
	
	private String errorDescription;
	
	private ErrorZoomExtendidoDto errorExtendedInfo;
	
	private static final long serialVersionUID = 2930853595657197569L;

}
