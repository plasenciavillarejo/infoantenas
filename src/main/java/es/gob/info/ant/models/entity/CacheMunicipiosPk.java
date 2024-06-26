package es.gob.info.ant.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class CacheMunicipiosPk implements Serializable {

	@Column(name = "codProvincia", length = 2)
	@Min(value =1, message = "El código de la provincia como mínimo debe ser 01")
	private Long codProvincia;
	
	@Column(name = "codMunicipio", length = 3)
	@NotNull
	private Long codMunicipio;
	
	private static final long serialVersionUID = 2828109158710161961L;

}
