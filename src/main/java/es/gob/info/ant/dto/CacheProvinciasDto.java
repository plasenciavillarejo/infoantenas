package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CacheProvinciasDto implements Serializable {

	private Long codProvincia;

	private String siglaProvincia;
	
	private String nombreProvincia;

	private static final long serialVersionUID = -6341724841328477522L;

}
