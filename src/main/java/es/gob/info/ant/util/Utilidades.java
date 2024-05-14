package es.gob.info.ant.util;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class Utilidades {

	private Sort configurarOrdenacionPaginador(String campo, String orden) {
		Sort.Direction direccion = orden.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Sort sort = null;
		if (campo.equalsIgnoreCase("localidad")) {
			sort = Sort.by(direccion, "provincia");
		} else if (campo.equalsIgnoreCase("municipio")) {
			sort = Sort.by(direccion, "D_EMPRESA");
		} else if (campo.equalsIgnoreCase("vigente")) {
			sort = Sort.by(direccion, "L_VIGENTE");
		} else if (campo.equalsIgnoreCase("publico")) {
			sort = Sort.by(direccion, "L_VISIBLE");
		} else {
			sort = Sort.by(direccion, campo);
		}
		return sort;
	}

}
