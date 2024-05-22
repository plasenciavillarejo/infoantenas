package es.gob.info.ant.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParametrosMunicipiosDto implements Serializable {
	
	@NotNull(message = "Invalid field codProvincia")
	private Long codProvincia;
	
	private int pagina;
	
	private int tamanioPagina;
	
	private int ordenacion;
	
	private int orden;
	
	private static final long serialVersionUID = 5348629082797540388L;

}
