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
@Table(schema = "GIS", name = "VCNE_Mediciones")
public class Mediciones implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emplazamiento", length = 64)
	private String emplazamiento;
	
	@Column(name = "distancia", precision = 5, scale = 1)
	private BigDecimal distancia; 

	@Column(name = "acimut", precision = 5, scale = 1)
	private BigDecimal acimut; 

	@Column(name = "medida", length = 25)
	private String medida;
	
	@Column(name = "provincia", length = 25)
	private String provincia;
	
	@Column(name = "año")
	private int anio;

	private static final long serialVersionUID = 7391139507261305898L;
 
	
}
