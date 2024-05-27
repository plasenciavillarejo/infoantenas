package es.gob.info.ant.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ListaNivelesMediosDto implements Serializable {

	private BigDecimal distancia;

	private BigDecimal acimut;

	private String valorMedio;

	private String provincia;
	
	private String emplazamiento;

	public ListaNivelesMediosDto(BigDecimal distancia, BigDecimal acimut, String valorMedio,String provincia, String emplazamiento) {
		super();
		this.distancia = distancia;
		this.acimut = acimut;
		this.valorMedio = valorMedio;
		this.provincia = provincia;
		this.emplazamiento = emplazamiento;
	}

	private static final long serialVersionUID = -269070475929510900L;

	
}
