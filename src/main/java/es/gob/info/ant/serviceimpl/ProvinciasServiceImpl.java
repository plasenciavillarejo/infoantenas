package es.gob.info.ant.serviceimpl;

import java.util.Map;

import org.springframework.stereotype.Service;

import es.gob.info.ant.service.IProvinciasService;

@Service
public class ProvinciasServiceImpl implements IProvinciasService {

	@Override
	public String siglasProvincias(String nombreProvincia) {
		Map<String, String> listadoProvincias = Map.ofEntries(
		Map.entry("VI", "Araba/Álava"),
		Map.entry("AB", "Albacete"),
		Map.entry("A", "Alicante/Alacant"),
		Map.entry("AL",	"Almería"),
		Map.entry("O", "Asturias"),
		Map.entry("AV", "Ávila"),
		Map.entry("BA", "Badajoz"),
		Map.entry("IB","Illes Balears"),
		Map.entry("B","Barcelona"),
		Map.entry("BU","Burgos"),
		Map.entry("CC","Cáceres"),
		Map.entry("CA","Cádiz"),
		Map.entry("CS","Castellón/Castelló"),
		Map.entry("CR","Ciudad Real"),
		Map.entry("CO","Córdoba"),
		Map.entry("C","A Coruña"),
		Map.entry("CU","Cuenca"),
		Map.entry("GI","Girona"),
		Map.entry("GR","Granada"),
		Map.entry("GU","Guadalajara"),
		Map.entry("SS","Gipuzkoa"),
		Map.entry("H","Huelva"),
		Map.entry("HU","Huesca"),
		Map.entry("J","Jaén"),
		Map.entry("LE","León"),
		Map.entry("L","Lleida"),
		Map.entry("LO","La Rioja"),
		Map.entry("LU","Lugo"),
		Map.entry("MA","Málaga"),
		Map.entry("MU","Murcia"),
		Map.entry("NA","Navarra"),
		Map.entry("OU","Ourense"),
		Map.entry("P","Palencia"),
		Map.entry("GC","Las Palmas"),
		Map.entry("PO","Pontevedra"),
		Map.entry("SA","Salamanca"),
		Map.entry("TF","Santa Cruz de Tenerife"),
		Map.entry("S","Cantabria"),
		Map.entry("SG","Segovia"),
		Map.entry("SE","Sevilla"),
		Map.entry("SO","Soria"),
		Map.entry("T","Tarragona"),
		Map.entry("TE","Teruel"),
		Map.entry("TO","Toledo"),
		Map.entry("V","Valencia/València"),
		Map.entry("VA","Valladolid"),
		Map.entry("BI","Bizkaia"),
		Map.entry("ZA","Zamora"),
		Map.entry("Z","Zaragoza"),
		Map.entry("CE","Ceuta"),
		Map.entry("ML","Melilla"));
		
		return 	listadoProvincias.entrySet().stream().filter(value -> value.getValue().equalsIgnoreCase(nombreProvincia))
				.map(Map.Entry::getKey)
				.findFirst().orElse("No se ha encontrado la Provincia a buscar");		
	}

}
