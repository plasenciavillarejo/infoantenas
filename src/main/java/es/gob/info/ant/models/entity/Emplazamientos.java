package es.gob.info.ant.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "GIS", name = "VCNE_Emplazamientos")
public class Emplazamientos implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 64)
	private String emplazamiento;
	
	@Column(length = 70)
	private String direccion;
	
	@Column(length = 64)
	private String localidad;
	
	@Column(length = 70)
	private String municipio;
	
	@Column(length = 25)
	private String provincia;
	
	@Column(length = 10)
	private String latitudGhms;
	
	@Column(length = 10)
	private String longitudGhms; 
	
	@Column(precision = 9, scale = 6)
	private BigDecimal latitud;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal longitud;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal latitudCC;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal longitudCC;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal latitudIDEE;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal longitudIDEE;
	
	@Column(length = 14)
	private String fechaActualizacion;
	
	@Column(length = 255)
	private String observaciones;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal latitudETRS89;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal longitudETRS89;
		
	private static final long serialVersionUID = 224304427164547755L;
	
}
