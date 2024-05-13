package es.gob.info.ant.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
	@Cacheable(value = "provinciasCacheadas")
	@Transactional(readOnly = true)
	public Slice<CacheProvinciasDto> listarProvincias(Pageable pageable) {
		return provinciasDao.listarProvincias(pageable);
	}

}
