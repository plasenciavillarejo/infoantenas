package es.gob.info.ant.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.info.ant.dto.DatosCaracteristicasTecnicasDto;
import es.gob.info.ant.dto.DetallesAntenaDto;
import es.gob.info.ant.models.dao.IEstacionesDao;
import es.gob.info.ant.models.service.IEstacionesService;

@Service
public class EstacionesServiceImpl implements IEstacionesService {

	@Autowired
	private IEstacionesDao estacionesDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DatosCaracteristicasTecnicasDto> listadoEstaciones(String emplazamiento) {
		return estacionesDao.listadoEstaciones(emplazamiento);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<DetallesAntenaDto> listadoEstacionesPageable(String emplazamiento, Pageable page) {
		return estacionesDao.listadoEstacionesPageable(emplazamiento, page);
	}

}
