package es.gob.info.ant.constantes;

import org.springframework.stereotype.Component;

@Component
public class ConstantesAplicacion {

	private ConstantesAplicacion() {
		// Constructor vacío
	}
	
	public static final String CONFIGURACIONPAGINADOR = "Configurando el paginador";
	
	public static final String QUERYLISTADOEMPLAZAMIENTOS = "select distinct emplaza.emplazamiento, emplaza.direccion, emplaza.localidad, emplaza.municipio, emplaza.provincia,"
			+ " emplaza.latitud, emplaza.longitud, emplaza.fechaActualizacion, emplaza.observaciones"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " left join gis.CacheProvincias provin"
			+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia"
			+ " left join gis.CacheMunicipios muni"
			+ " on muni.codProvincia = provin.codProvincia"
			+ " where (:codProvincia is null or muni.codProvincia = :codProvincia)"
			+ " and (:codMunicipio is null or muni.codMunicipio = :codMunicipio)" 	
		    + " and (:direccion is null or direccion like CONCAT('%', :direccion, '%'))";
	
	public static final String QUERYLISTADOESTACINES = "select * from("
			+ "select distinct emplaza.emplazamiento, emplaza.direccion, emplaza.localidad, emplaza.municipio, emplaza.provincia,"
			+ "	emplaza.latitud, emplaza.longitud, emplaza.fechaActualizacion, emplaza.observaciones,"
			+ " ( 6371 * acos(cos(radians(:latitud)) * cos(radians(emplaza.latitud)) *"
			+ "	cos(radians(emplaza.longitud) - radians(:longitud)) + sin(radians(:latitud)) *"
			+ "	sin(radians(emplaza.latitud)))) AS distancia"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " left join gis.CacheProvincias provin"
			+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia" 
			+ " left join gis.CacheMunicipios muni"
			+ " on muni.codProvincia = provin.codProvincia) as estaciones"
			+ " where estaciones.distancia < :radio";

	public static final String QUERYESTACION = "select distinct emplaza.emplazamiento, emplaza.direccion, emplaza.localidad, emplaza.municipio, emplaza.provincia,"
			+ " emplaza.latitud, emplaza.longitud, emplaza.fechaActualizacion, emplaza.observaciones"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " left join gis.CacheProvincias provin"
			+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia"
			+ " left join gis.CacheMunicipios muni"
			+ " on muni.codProvincia = provin.codProvincia"
			+ " where emplaza.emplazamiento = :emplazamiento";
	
	public static final String DIRECCION = "direccion";
	
	public static final String MUNICIPIO = "nombreRegistroEntidadesLocales";
	
	public static final String BADREQUEST= "Bad Request";
	
	public static final String FORMATOFECHA = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	public static final String ERROR = "error";
	
	public static final int ZOOMPERMITODO = 20;
	
	public static final String CONSTANTELOGGUERINFOANTENAS = "es.gob.info.ant";
	
}
