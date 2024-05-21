package es.gob.info.ant.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PruebaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3827730379937221915L;
	@NotNull(message = "El código de la provincia no puede estar vacío")
	private Long codProvincia;
	
}
