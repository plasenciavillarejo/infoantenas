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
	@Column(name="emplazamiento", length = 64)
	private String emplazamiento;
	
	@Column(name="direccion", length = 70)
	private String direccion;
	
	@Column(name="localidad", length = 64)
	private String localidad;
	
	@Column(name="municipio", length = 70)
	private String municipio;
	
	@Column(name="provincia", length = 25)
	private String provincia;
	
	@Column(name="latitud_ghms", length = 10)
	private String latitudGhms;
	
	@Column(name="longitud_ghms", length = 10)
	private String longitudGhms; 
	
	@Column(name="latitud", precision = 9, scale = 6)
	private BigDecimal latitud;
	
	@Column(name="longitud", precision = 9, scale = 6)
	private BigDecimal longitud;
	
	@Column(name="latitudCC", precision = 9, scale = 6)
	private BigDecimal latitudCc;
	
	@Column(name="longitudCC", precision = 9, scale = 6)
	private BigDecimal longitudCc;
	
	@Column(name="latitudIDEE", precision = 9, scale = 6)
	private BigDecimal latitudIdee;
	
	@Column(name="longitudIDEE", precision = 9, scale = 6)
	private BigDecimal longitudIdee;
	
	@Column(name="fechaActualizacion", length = 14)
	private String fechaActualizacion;
	
	@Column(name="observaciones", length = 255)
	private String observaciones;
	
	@Column(name="latitudETRS89", precision = 9, scale = 6)
	private BigDecimal latitudEtrs;
	
	@Column(name="longitudETRS89", precision = 9, scale = 6)
	private BigDecimal longitudEtrs;
		
	private static final long serialVersionUID = 224304427164547755L;
	
}
