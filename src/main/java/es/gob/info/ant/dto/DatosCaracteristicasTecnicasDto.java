package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DatosCaracteristicasTecnicasDto implements Serializable {

	private String codEstacion;
	
	private String operador;

	private String banda;

	private String referencia;

	public DatosCaracteristicasTecnicasDto() {
		super();
	}

	public DatosCaracteristicasTecnicasDto(ListaDatosCaracteristicasTecnicasDto dato) {
		super();
		this.codEstacion = dato.getCodEstacion();
		this.operador = dato.getOperador();
		this.banda = dato.getBanda();
		this.referencia = dato.getReferencia();
	}

	private static final long serialVersionUID = 1854192814865115793L;

}
