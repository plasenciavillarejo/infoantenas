package es.gob.info.ant.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class CacheMunicipiosPk implements Serializable {

	@Column(length = 2)
	@NotNull
	private Long codProvincia;
	
	@Column(length = 3)
	@NotNull
	private Long codMunicipio;
	
	private static final long serialVersionUID = 2828109158710161961L;

}
