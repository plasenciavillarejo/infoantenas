package es.gob.info.ant.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParametrosAntenasDto implements Serializable {

	@NotNull(message = "Invalid field codProvincia")
	private Long codProvincia;
	
	@NotNull(message = "Invalid field codMunicipio")
	private Long codMunicipio;
	
	private String direccion;
	
	private String numero;
	
	private int pagina;
	
	private int tamanioPagina;
	
	private Integer ordenacion;
	
	private int orden = 1;
		
	private static final long serialVersionUID = 3827730379937221915L;
	
}
