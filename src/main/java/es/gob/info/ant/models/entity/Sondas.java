package es.gob.info.ant.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(schema = "GIS", name = "VCNE_Sondas")
public class Sondas implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	@NotNull
	private String emplazamiento;
	
	@NotNull
	private int idEstado;
	
	@NotNull
	@Column(name = "latitud_ghms", length = 10)
	private String latitudGhms;
	
	@NotNull
	@Column(name = "longitud_ghms", length = 10)
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
	
	@Column(length = 250)
	private String descripcion;
	
	@NotNull
	private int idUbicacion;
	
	@NotNull
	private int estado;

	private static final long serialVersionUID = 2322178821213244582L;

}
