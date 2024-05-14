package es.gob.info.ant.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.info.ant.models.dao.IEmplazamientosDao;
import es.gob.info.ant.models.service.IEmplazamientosService;

@Service
public class EmplazamientosServiceImpl implements IEmplazamientosService {

	@Autowired
	private IEmplazamientosDao emplazamientosDao;
	
	@Override
	@Transactional(readOnly = true)
	public Page<Object []> listaEmplazamientos(String codProvincia, String codMunicipio,
			 String direccion, Pageable pageable) {
		return emplazamientosDao.listaEmplazamientos(codProvincia, codMunicipio, direccion, pageable);
	}

	@Override
	public String obtenerDirecciones(String emplazamiento) {
		return emplazamientosDao.obtenerDireccion(emplazamiento);
	}

}
