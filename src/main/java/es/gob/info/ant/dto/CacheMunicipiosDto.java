package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CacheMunicipiosDto implements Serializable {

	private String codMunicipio;
	
	private String nombreMunicipo;
	
	private static final long serialVersionUID = 3634090982594217663L;

}
