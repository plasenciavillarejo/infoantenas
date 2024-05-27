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

	private BigDecimal latitud;

	private BigDecimal longitud;

	private String fechaActualizacion;

	private String observaciones;

	private List<DatosCaracteristicasTecnicasDto> datosCaracteristicasTecnicas;
	
	private List<NivelesMediosDto> nivelesMedios;
		
	private static final long serialVersionUID = 6871980887181329666L;

	public FiltradoAntenasDto(String emplazamiento, String direccion) {
		super();
		this.emplazamiento = emplazamiento;
		this.direccion = direccion;
	}

	public FiltradoAntenasDto() {
		super();
	}

	public FiltradoAntenasDto(String emplazamiento, String direccion, String localidad, String municipio, String provincia,
			BigDecimal latitud, BigDecimal longitud, String fechaActualizacion, String observaciones) {
		super();
		this.emplazamiento = emplazamiento;
		this.direccion = direccion;
		this.localidad = localidad;
		this.municipio = municipio;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
		this.fechaActualizacion = fechaActualizacion;
		this.observaciones = observaciones;
	}



	
	
}
