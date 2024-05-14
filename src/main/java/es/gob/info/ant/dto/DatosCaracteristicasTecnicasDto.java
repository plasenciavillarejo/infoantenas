package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DatosCaracteristicasTecnicasDto implements Serializable {

	private String emplazamiento;

	private String operador;

	private String banda;

	private String referencia;

	private static final long serialVersionUID = 1302762004113640877L;

	public DatosCaracteristicasTecnicasDto(String emplazamiento, String operador, String banda, String referencia) {
		super();
		this.emplazamiento = emplazamiento;
		this.operador = operador;
		this.banda = banda;
		this.referencia = referencia;
	}

	public DatosCaracteristicasTecnicasDto() {
		super();
	}

}
