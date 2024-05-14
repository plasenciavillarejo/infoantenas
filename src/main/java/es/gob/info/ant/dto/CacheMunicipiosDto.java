package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CacheMunicipiosDto implements Serializable {

	private Long codProvincia;

	private Long codMunicipio;
	
	private String nombreRegistroEntidadesLocales;

	public CacheMunicipiosDto(Long codProvincia, Long codMunicipio, String nombreRegistroEntidadesLocales) {
		super();
		this.codProvincia = codProvincia;
		this.codMunicipio = codMunicipio;
		this.nombreRegistroEntidadesLocales = nombreRegistroEntidadesLocales;
	}
	
	private static final long serialVersionUID = 3634090982594217663L;

}
