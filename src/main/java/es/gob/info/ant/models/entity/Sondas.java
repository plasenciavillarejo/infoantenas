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
	@Column(name = "emplazamiento", length = 20)
	@NotNull
	private String emplazamiento;
	
	@Column(name = "idEstado")
	@NotNull
	private int idEstado;
	
	@NotNull
	@Column(name = "latitud_ghms", length = 10)
	private String latitudGhms;
	
	@NotNull
	@Column(name = "longitud_ghms", length = 10)
	private String longitudGhms;
	
	@Column(name = "latitud", precision = 9, scale = 6)
	private BigDecimal latitud;

	@Column(name = "longitud", precision = 9, scale = 6)
	private BigDecimal longitud;
		
	@Column(name = "latitudCC", precision = 9, scale = 6)
	private BigDecimal latitudCc;
	
	@Column(name = "longitudCC", precision = 9, scale = 6)
	private BigDecimal longitudCc;
	
	@Column(name = "latitudIDEE", precision = 9, scale = 6)
	private BigDecimal latitudIdee;
	
	@Column(name = "longitudIDEE", precision = 9, scale = 6)
	private BigDecimal longitudIdee;
	
	@Column(name = "descripcion", length = 250)
	private String descripcion;
	
	@Column(name = "idUbicacion")
	@NotNull
	private int idUbicacion;
	
	@Column(name = "estado")
	@NotNull
	private int estado;

	private static final long serialVersionUID = 2322178821213244582L;

}
