package es.gob.info.ant.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.info.ant.dto.NivelesMediosDto;
import es.gob.info.ant.models.dao.IMedicionesDao;
import es.gob.info.ant.models.service.IMedicionesService;

@Service
public class MedicionesServiceImpl implements IMedicionesService {

	@Autowired
	private IMedicionesDao medicionesDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<NivelesMediosDto> listarMediciones(String emplazamiento) {
		return medicionesDao.listarMediciones(emplazamiento);
	}

}
