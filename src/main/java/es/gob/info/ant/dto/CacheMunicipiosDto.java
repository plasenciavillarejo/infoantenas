package es.gob.info.ant.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CacheMunicipiosDto implements Serializable {

	private String codProvincia;

	private String codMunicipio;
	
	private String nombreMunicipo;
	
	private static final long serialVersionUID = 3634090982594217663L;

}
