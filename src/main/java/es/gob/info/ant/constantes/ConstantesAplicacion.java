package es.gob.info.ant.constantes;

import org.springframework.stereotype.Component;

@Component
public class ConstantesAplicacion {

	private ConstantesAplicacion() {
		// Constructor vacío
	}
	
	public static final String CONFIGURACIONPAGINADOR = "Configurando el paginador";
	
	private static final String CRUCEGENERICO = " from gis.VCNE_Emplazamientos emplaza"
			+ " left join gis.CacheProvincias provin"
			+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia"
			+ " left join gis.CacheMunicipios muni";
	
	private static final String SELECTGENERICO = "select distinct emplaza.emplazamiento, emplaza.direccion, emplaza.localidad, emplaza.municipio, emplaza.provincia,";
	
	public static final String QUERYLISTADOEMPLAZAMIENTOS = SELECTGENERICO
			+ " emplaza.latitud, emplaza.longitud, emplaza.fechaActualizacion, emplaza.observaciones" + CRUCEGENERICO
			+ " on muni.codProvincia = provin.codProvincia"
			+ " where muni.codProvincia = :codProvincia"
			+ " and muni.codMunicipio = :codMunicipio" 	
		    + " and (coalesce(:direccion,null) is null or direccion like CONCAT('%', :direccion, '%'))";
	
	public static final String QUERYLISTADOESTACINES = "select * from("
			+ SELECTGENERICO
			+ " emplaza.latitud, emplaza.longitud, emplaza.fechaActualizacion, emplaza.observaciones,"
			+ " ( 6371 * acos(cos(radians(:latitud)) * cos(radians(emplaza.latitud)) *"
			+ " cos(radians(emplaza.longitud) - radians(:longitud)) + sin(radians(:latitud)) *"
			+ " sin(radians(emplaza.latitud)))) AS distancia"
			+ CRUCEGENERICO
			+ " on muni.codProvincia = provin.codProvincia) as estaciones"
			+ " where estaciones.distancia < :radio";

	public static final String QUERYESTACION = SELECTGENERICO
			+ " emplaza.latitud, emplaza.longitud, emplaza.fechaActualizacion, emplaza.observaciones"
			+ CRUCEGENERICO
			+ " on muni.codProvincia = provin.codProvincia"
			+ " where emplaza.emplazamiento = :emplazamiento";
	
	public static final String DIRECCION = "direccion";
	
	public static final String MUNICIPIO = "nombreRegistroEntidadesLocales";
	
	public static final String BADREQUEST= "Bad Request";
	
	public static final String FORMATOFECHA = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final String ERROR = "error";
	
	public static final int ZOOMPERMITODO = 20;
	
	public static final String CONSTANTELOGGUERINFOANTENAS = "es.gob.info.ant";

	public static final String CORRELATIONID = "50c81057-7242-41c8-8663-bf40ae675a8b";
	
}
