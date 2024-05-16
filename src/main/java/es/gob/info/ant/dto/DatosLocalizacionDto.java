package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DatosLocalizacionDto implements Serializable {

	private String codEstacion;

	private String direccion;
		
	private static final long serialVersionUID = 3634090982594217663L;

}
