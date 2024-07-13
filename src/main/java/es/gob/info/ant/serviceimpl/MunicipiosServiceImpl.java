package es.gob.info.ant.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.gob.info.ant.constantes.ConstantesAplicacion;
import es.gob.info.ant.dto.CacheMunicipiosDto;
import es.gob.info.ant.dto.ParametrosMunicipiosDto;
import es.gob.info.ant.exception.ErrorGlobalAntenasException;
import es.gob.info.ant.models.service.ICacheMunicipiosService;
import es.gob.info.ant.service.IMunicipiosService;

@Service
public class MunicipiosServiceImpl implements IMunicipiosService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantesAplicacion.CONSTANTELOGGUERINFOANTENAS);
	
	@Autowired
	private ICacheMunicipiosService cacheMunicipiosService;

	@Override
	public Page<Object[]> listaMuncipios(Pageable page, ParametrosMunicipiosDto parametrosDto)
			throws ErrorGlobalAntenasException {
		try {
			return cacheMunicipiosService.listarMunicipios(page, parametrosDto.getCodProvincia());
		} catch (Exception e) {
			throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public List<CacheMunicipiosDto> listaMuncipiosDto(Page<Object []> listaMuncipios) throws ErrorGlobalAntenasException {
		List<CacheMunicipiosDto> listaMuncipiosDto = null;
		try  {
			LOGGER.info("Se procede almacenar la información dentro del dto");
			listaMuncipiosDto = listaMuncipios.stream().map(muni -> {
			CacheMunicipiosDto municiposDto = new CacheMunicipiosDto();
			municiposDto.setCodMunicipio(String.format("%03d", Long.valueOf(String.valueOf(muni[0]))));
			municiposDto.setNombreMunicipo(String.valueOf(muni[1]));
			return municiposDto;
		}).toList();
		} catch (Exception e) {
			throw new ErrorGlobalAntenasException(e.getMessage(), e.getCause());
		}
		return listaMuncipiosDto;
	}

}
