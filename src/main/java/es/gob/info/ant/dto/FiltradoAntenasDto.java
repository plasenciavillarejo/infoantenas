package es.gob.info.ant.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
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
		
	private static final long serialVersionUID = 6871980887181329666L;
	
}
