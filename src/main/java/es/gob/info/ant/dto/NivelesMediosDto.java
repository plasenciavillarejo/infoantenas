package es.gob.info.ant.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class NivelesMediosDto implements Serializable {

	private BigDecimal distancia;

	private BigDecimal acimut;

	private String medida;

	private String provincia;

	public NivelesMediosDto(BigDecimal distancia, BigDecimal acimut, String medida,String provincia) {
		super();
		this.distancia = distancia;
		this.acimut = acimut;
		this.medida = medida;
		this.provincia = provincia;
	}

	private static final long serialVersionUID = -269070475929510900L;

	
}
