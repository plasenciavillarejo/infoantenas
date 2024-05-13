package es.gob.info.ant.models.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.info.ant.dto.CacheProvinciasDto;
import es.gob.info.ant.models.dao.ICacheProvinciasDao;
import es.gob.info.ant.models.service.ICacheProvinciasService;

@Service
public class CacheProvinciasServiceImpl implements ICacheProvinciasService {

	@Autowired
	private ICacheProvinciasDao provinciasDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CacheProvinciasDto> listarProvincias() {
		return provinciasDao.listarProvincias();
	}

}
