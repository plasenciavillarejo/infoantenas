package es.gob.info.ant.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ListaDatosCaracteristicasTecnicasDto implements Serializable {

	private String codEstacion;
	
	private String operador;

	private String banda;

	private String referencia;
	
	private String emplazamiento;

	private static final long serialVersionUID = 1302762004113640877L;

	public ListaDatosCaracteristicasTecnicasDto(String codEstacion, String operador, String banda, String referencia,String emplazamiento) {
		super();
		this.codEstacion = codEstacion;
		this.operador = operador;
		this.banda = banda;
		this.referencia = referencia;
		this.emplazamiento = emplazamiento;
	}

	public ListaDatosCaracteristicasTecnicasDto() {
		super();
	}

}
