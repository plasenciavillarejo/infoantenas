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
@Table(schema = "GIS", name = "VCNE_Estaciones")
public class Estaciones implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 64)
	private String emplazamiento;
	
	@Column(length = 20)
	private String codEstacion;
	
	@Column(length = 50)
	private String operador;
	
	@Column(length = 7)
	private String tpSistema;
	
	@Column(length = 100)
	private String banda;
	
	@Column(precision = 6, scale = 2)
	private BigDecimal limite; 
	
	@Column(length = 25)
	private String provincia;
	
	@Column(length = 16)
	private String referencia;
	
	private static final long serialVersionUID = 7210237755315834957L;

}
