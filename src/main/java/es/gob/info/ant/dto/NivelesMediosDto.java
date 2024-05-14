package es.gob.info.ant.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class NivelesMediosDto implements Serializable {

	private BigDecimal distancia;

	private BigDecimal acimut;

	private String medida;

	private String emplazamiento;

	private String provincia;

	public NivelesMediosDto(BigDecimal distancia, BigDecimal acimut, String medida, String emplazamiento,
			String provincia) {
		super();
		this.distancia = distancia;
		this.acimut = acimut;
		this.medida = medida;
		this.emplazamiento = emplazamiento;
		this.provincia = provincia;
	}

	private static final long serialVersionUID = -269070475929510900L;

	
}
