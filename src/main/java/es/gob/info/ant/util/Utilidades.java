package es.gob.info.ant.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import es.gob.info.ant.dto.PaginadorDto;

@Component
public class Utilidades {

	public void configuracionPaginador(PaginadorDto paginador, Pageable page) {
		paginador.setPaginaActual(page.getPageNumber() + 1);
		paginador.setTamanioPagina(page.getPageSize());
	}
	
	public Pageable configurarPageRequest(int pagina, int tamanioPagina, int orden, String campo) {
		return  PageRequest.of(pagina -1, tamanioPagina, orden == 1 ? Sort.by(campo).ascending() 
						: Sort.by(campo).descending());
	}
	
}
