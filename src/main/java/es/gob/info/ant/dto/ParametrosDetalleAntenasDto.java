package es.gob.info.ant.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParametrosDetalleAntenasDto implements Serializable {

	@NotNull(message = "Invalid field idAntena")
	private String idAntena;
	
	private static final long serialVersionUID = 8994483230624258940L;

}
