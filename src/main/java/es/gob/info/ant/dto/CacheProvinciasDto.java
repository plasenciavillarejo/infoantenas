package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CacheProvinciasDto implements Serializable {

	private Long codProvincia;

	private String siglasProvincia;
	
	private String nombreRegistroEntidadesLocales;

	public CacheProvinciasDto(Long codProvincia, String nombreRegistroEntidadesLocales) {
		super();
		this.codProvincia = codProvincia;
		this.nombreRegistroEntidadesLocales = nombreRegistroEntidadesLocales;
	}

	private static final long serialVersionUID = -6341724841328477522L;

}
