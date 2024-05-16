package es.gob.info.ant.constantes;

import org.springframework.stereotype.Component;

@Component
public class ConstantesAplicacion {

	private ConstantesAplicacion() {
		// Constructor vacío
	}
	
	public static final String CONFIGURACIONPAGINADOR = "Configurando el paginador";

	
	public static final String QUERYLISTADOEMPLAZAMIENTOS = "select distinct emplaza.emplazamiento, emplaza.direccion, emplaza.localidad, emplaza.municipio, emplaza.provincia,"
			+ " emplaza.latitud_ghms, emplaza.longitud_ghms,"
			+ " emplaza.latitud, emplaza.longitud, emplaza.latitudCC, emplaza.longitudCC, emplaza.latitudIDEE, emplaza.longitudIDEE,"
			+ " emplaza.fechaActualizacion, emplaza.observaciones, emplaza.latitudETRS89, emplaza.longitudETRS89"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " left join gis.CacheProvincias provin"
			+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia"
			+ " left join gis.CacheMunicipios muni"
			+ " on muni.codProvincia = provin.codProvincia"
			+ " where (:codProvincia is null or muni.codProvincia = :codProvincia)"
			+ " and (:codMunicipio is null or muni.codMunicipio = :codMunicipio)" 	
		    + " and (:direccion is null or direccion like CONCAT('%', :direccion, '%'))";
	
	public static final String QUERYLISTADOESTACINES = "select distinct (emplaza.emplazamiento), emplaza.direccion, emplaza.localidad, emplaza.municipio, emplaza.provincia,"
			+ " emplaza.latitud_ghms, emplaza.longitud_ghms,"
			+ " emplaza.latitud, emplaza.longitud, emplaza.latitudCC, emplaza.longitudCC, emplaza.latitudIDEE, emplaza.longitudIDEE,"
			+ " emplaza.fechaActualizacion, emplaza.observaciones, emplaza.latitudETRS89, emplaza.longitudETRS89"
			+ " from gis.VCNE_Emplazamientos emplaza"
			+ " left join gis.CacheProvincias provin"
			+ " on provin.nombreRegistroEntidadesLocales = emplaza.provincia" 
			+ " left join gis.CacheMunicipios muni"
			+ " on muni.codProvincia = provin.codProvincia"
			+ " where (:codProvincia is null or muni.codProvincia = :codProvincia)"
			+ " and (:codMunicipio is null or muni.codMunicipio = :codMunicipio)"
			+ " and (:direccion is null or direccion like CONCAT('%', :direccion, '%'))" 
			+ " and (:latitudIni is null or :latitudIni <= emplaza.latitud)"
			+ " and (:latitudFin is null or :latitudFin >= emplaza.latitud)"
			+ " and (:longitudIni is null or :longitudIni <= emplaza.longitud)"
			+ " and (:longitudFin is null or :longitudFin >= emplaza.longitud)"
			+ " order by emplaza.emplazamiento";
	
}
