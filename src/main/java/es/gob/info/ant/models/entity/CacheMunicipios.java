package es.gob.info.ant.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(schema = "GIS", name = "CacheMunicipios")
public class CacheMunicipios implements Serializable {
	
	@EmbeddedId
	private CacheMunicipiosPk cacheMunicipiosPk;
	
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
	
	@Column(length = 100)
	@NotNull
	private String nombreRegistroEntidadesLocales;
	
	private static final long serialVersionUID = -6728337971854003804L;
		
}
