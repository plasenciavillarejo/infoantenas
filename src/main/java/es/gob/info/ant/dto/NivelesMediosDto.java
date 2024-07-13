package es.gob.info.ant.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class NivelesMediosDto implements Serializable {

	private BigDecimal distancia;

	private BigDecimal acimut;

	private String valorMedio;

	private String provincia;

	public NivelesMediosDto(ListaNivelesMediosDto dato) {
		super();
		this.distancia = dato.getDistancia();
		this.acimut = dato.getAcimut();
		this.valorMedio = dato.getValorMedio();
		this.provincia = dato.getProvincia();
	}

	public NivelesMediosDto() {
		super();
	}

	private static final long serialVersionUID = -4653948116315639407L;

}
