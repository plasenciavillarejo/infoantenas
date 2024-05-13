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
@Table(schema = "GIS", name = "CacheProvincias")
public class CacheProvincias implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 2)
	@NotNull
	private Long codProvincia;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal latitudEsquinaInferiorIzquierda;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal longitudEsquinaInferiorIzquierda;

	@Column(precision = 9, scale = 6)
	private BigDecimal latitudEsquinaSuperiorDerecha;
	
	@Column(precision = 9, scale = 6)
	private BigDecimal longitudEsquinaSuperiorDerecha;
	
	@Column(length = 15)
	private String proyeccion;
	
	@Column(length = 60)
	@NotNull
	private String nombreRegistroEntidadesLocales;
	
	private static final long serialVersionUID = -6350239357738217583L;
	
}
