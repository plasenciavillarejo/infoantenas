package es.gob.info.ant.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class DetallesAntenaDto implements Serializable {

	private String emplazamiento;

	private String codEstacion;

	private String operador;

	private String tpSistema;

	private String banda;

	private BigDecimal limite;

	private String provincia;

	private String referencia;
	
	private DatosLocalizacionDto datosLocalizacion;
	
	private DatosCaracteristicasTecnicasDto datosCaracteristicasTecnicas;
	
	//private Mediciones
	
	public DetallesAntenaDto(String emplazamiento, String codEstacion, String operador, String tpSistema, String banda,
			BigDecimal limite, String provincia, String referencia) {
		super();
		this.emplazamiento = emplazamiento;
		this.codEstacion = codEstacion;
		this.operador = operador;
		this.tpSistema = tpSistema;
		this.banda = banda;
		this.limite = limite;
		this.provincia = provincia;
		this.referencia = referencia;
	}

	public DetallesAntenaDto(String operador, String referencia, String banda,  String emplazamiento) {
		super();
		this.operador = operador;
		this.referencia = referencia;
		this.banda = banda;
		this.emplazamiento = emplazamiento;
	}

	private static final long serialVersionUID = 2123265538919145836L;

}
