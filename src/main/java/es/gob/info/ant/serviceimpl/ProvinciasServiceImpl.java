package es.gob.info.ant.serviceimpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

import es.gob.info.ant.service.IProvinciasService;

@Service
public class ProvinciasServiceImpl implements IProvinciasService {

	@Override
	public String siglasProvincias(String nombreProvincia) {
		Map<String, String> listadoProvincias = new HashMap<>();
		listadoProvincias.put("VI", "Araba/Álava");
		listadoProvincias.put("AB", "Albacete");
		listadoProvincias.put("A", "Alicante/Alacant");
		listadoProvincias.put("AL",	"Almería");
		listadoProvincias.put("O", "Asturias");
		listadoProvincias.put("AV", "Ávila");
		listadoProvincias.put("BA", "Badajoz");
		listadoProvincias.put("IB","Illes Balears");
		listadoProvincias.put("B","Barcelona");
		listadoProvincias.put("BU","Burgos");
		listadoProvincias.put("CC","Cáceres");
		listadoProvincias.put("CA","Cádiz");
		listadoProvincias.put("CS","Castellón/Castelló");
		listadoProvincias.put("CR","Ciudad Real");
		listadoProvincias.put("CO","Córdoba");
		listadoProvincias.put("C","A Coruña");
		listadoProvincias.put("CU","Cuenca");
		listadoProvincias.put("GI","Girona");
		listadoProvincias.put("GR","Granada");
		listadoProvincias.put("GU","Guadalajara");
		listadoProvincias.put("SS","Gipuzkoa");
		listadoProvincias.put("H","Huelva");
		listadoProvincias.put("HU","Huesca");
		listadoProvincias.put("J","Jaén");
		listadoProvincias.put("LE","León");
		listadoProvincias.put("L","Lleida");
		listadoProvincias.put("LO","La Rioja");
		listadoProvincias.put("LU","Lugo");
		listadoProvincias.put("MA","Málaga");
		listadoProvincias.put("MU","Murcia");
		listadoProvincias.put("NA","Navarra");
		listadoProvincias.put("OU","Ourense");
		listadoProvincias.put("P","Palencia");
		listadoProvincias.put("GC","Las Palmas");
		listadoProvincias.put("PO","Pontevedra");
		listadoProvincias.put("SA","Salamanca");
		listadoProvincias.put("TF","Santa Cruz de Tenerife");
		listadoProvincias.put("S","Cantabria");
		listadoProvincias.put("SG","Segovia");
		listadoProvincias.put("SE","Sevilla");
		listadoProvincias.put("SO","Soria");
		listadoProvincias.put("T","Tarragona");
		listadoProvincias.put("TE","Teruel");
		listadoProvincias.put("TO","Toledo");
		listadoProvincias.put("V","Valencia/València");
		listadoProvincias.put("VA","Valladolid");
		listadoProvincias.put("BI","Bizkaia");
		listadoProvincias.put("ZA","Zamora");
		listadoProvincias.put("Z","Zaragoza");
		listadoProvincias.put("CE","Ceuta");
		listadoProvincias.put("ML","Melilla");
		
		AtomicReference<String> provinciaRecuperada = new AtomicReference<>();
				
		listadoProvincias.forEach((key, value) -> {
			if(value.equalsIgnoreCase(nombreProvincia)) {
				provinciaRecuperada.set(key);
			}
		});
		
		return provinciaRecuperada.get();
	}

}
