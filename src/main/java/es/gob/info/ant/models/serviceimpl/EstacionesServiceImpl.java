package es.gob.info.ant.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.info.ant.dto.EstacionesDto;
import es.gob.info.ant.models.dao.IEstacionesDao;
import es.gob.info.ant.models.service.IEstacionesService;

@Service
public class EstacionesServiceImpl implements IEstacionesService {

	@Autowired
	private IEstacionesDao estacionesDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<EstacionesDto> listadoEstaciones(String emplazamiento) {
		return estacionesDao.listadoEstaciones(emplazamiento);
	}

}
