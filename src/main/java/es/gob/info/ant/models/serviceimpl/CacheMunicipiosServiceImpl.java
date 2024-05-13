package es.gob.info.ant.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.info.ant.dto.CacheMunicipiosDto;
import es.gob.info.ant.models.dao.ICacheMunicipiosDao;
import es.gob.info.ant.models.service.ICacheMunicipiosService;

@Service
public class CacheMunicipiosServiceImpl implements ICacheMunicipiosService {

	@Autowired
	private ICacheMunicipiosDao municipiosDao;
	
	@Override
	@Cacheable(value = "municipiosCacheados")
	@Transactional(readOnly = true)
	public Slice<CacheMunicipiosDto> listarMunicipios(Pageable pageable, Long codProvincia) {
		return municipiosDao.listarMunicipios(pageable, codProvincia);
	}

}
