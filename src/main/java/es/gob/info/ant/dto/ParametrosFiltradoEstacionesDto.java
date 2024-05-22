package es.gob.info.ant.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParametrosFiltradoEstacionesDto implements Serializable {

	@NotNull(message = "Invalid field latitud")
	private Double latitud;
	
	@NotNull(message = "Invalid field longitud")
	private Double longitud;
	
	@NotNull(message = "Invalid field radio")
	private Double radio;
	
	@NotNull(message = "Invalid field zoom")
	private Integer zoom;	

	private static final long serialVersionUID = 4837991154013397636L;

}
