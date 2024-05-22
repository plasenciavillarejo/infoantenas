package es.gob.info.ant.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParametrosAntenasDto implements Serializable {

	@NotNull(message = "Invalid field codProvincia")
	private Long codProvincia;
		
	private String direccion;
	
	private String numero;
	
	private int tamanopagina;
	
	private int pagina;
	
	private int ordenacion;
	
	private int orden;
		
	private static final long serialVersionUID = 3827730379937221915L;
	
}
