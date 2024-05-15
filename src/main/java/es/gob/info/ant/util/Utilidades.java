package es.gob.info.ant.util;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.gob.info.ant.dto.PaginadorDto;

@Component
public class Utilidades {

	public void configuracionPaginador(PaginadorDto paginador, Pageable page) {
		paginador.setCurrentPage(page.getPageNumber() + 1);
		paginador.setPageSize(page.getPageSize());
	}

}
