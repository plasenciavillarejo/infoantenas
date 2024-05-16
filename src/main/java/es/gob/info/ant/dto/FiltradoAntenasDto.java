package es.gob.info.ant.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FiltradoAntenasDto implements Serializable {

	private String emplazamiento;

	private String direccion;

	private String localidad;

	private String municipio;

	private String provincia;

	private String latitudGhms;

	private String longitudGhms;

	private BigDecimal latitud;

	private BigDecimal longitud;

	private BigDecimal latitudCc;

	private BigDecimal longitudCc;

	private BigDecimal latitudIdee;

	private BigDecimal longitudIdee;

	private String fechaActualizacion;

	private String observaciones;

	private BigDecimal latitudEtrs;

	private BigDecimal longitudEtrs;

	private List<DatosCaracteristicasTecnicasDto> datosCaracteristicasTecnicas;
	
	private List<NivelesMediosDto> nivelesMedios;
	
	//private DatosLocalizacionDto datosLocalizacion; 
	
	private static final long serialVersionUID = 6871980887181329666L;

	public FiltradoAntenasDto(String emplazamiento, String direccion) {
		super();
		this.emplazamiento = emplazamiento;
		this.direccion = direccion;
	}

	public FiltradoAntenasDto() {
		super();
	}



	
	
}
