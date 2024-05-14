package es.gob.info.ant.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(schema = "GIS", name = "Cacheprovincias")
public class CacheProvincias implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codProvincia", length = 2)
	@NotNull
	private Long codProvincia;
	
	@Column(name = "latitudEsquinaInferiorIzquierda", precision = 9, scale = 6)
	private BigDecimal latitudEsquinaInferiorIzquierda;
	
	@Column(name = "longitudEsquinaInferiorIzquierda", precision = 9, scale = 6)
	private BigDecimal longitudEsquinaInferiorIzquierda;

	@Column(name = "latitudEsquinaSuperiorDerecha", precision = 9, scale = 6)
	private BigDecimal latitudEsquinaSuperiorDerecha;
	
	@Column(name = "longitudEsquinaSuperiorDerecha", precision = 9, scale = 6)
	private BigDecimal longitudEsquinaSuperiorDerecha;
	
	@Column(name = "proyeccion", length = 15)
	private String proyeccion;
	
	@Column(name = "nombreRegistroEntidadesLocales", length = 60)
	@NotNull
	private String nombreRegistroEntidadesLocales;
	
	@OneToMany(mappedBy = "cacheMunicipiosPk.codProvincia", fetch = FetchType.LAZY)
	private List<CacheMunicipios> cacheMunicipios;
	
	private static final long serialVersionUID = -6350239357738217583L;
	
}
