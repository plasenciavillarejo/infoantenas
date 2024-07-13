package es.gob.info.ant.models.serviceimpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.info.ant.dto.ListaNivelesMediosDto;
import es.gob.info.ant.models.dao.IMedicionesDao;
import es.gob.info.ant.models.service.IMedicionesService;

@Service
public class MedicionesServiceImpl implements IMedicionesService {

	@Autowired
	private IMedicionesDao medicionesDao;
	
	@Override
	@Transactional(readOnly = true)
	public CompletableFuture<List<ListaNivelesMediosDto>> listarMediciones(List<String> emplazamiento) {
		return medicionesDao.listarMediciones(emplazamiento).thenApply(nivelesMedios -> {
			nivelesMedios.stream().forEach(nivel -> {
				if (!nivel.getValorMedio().contains("<ERR")) {
	            	nivel.setValorMedio(nivel.getValorMedio().contains("<") 
	                    ? "<".concat(String.valueOf(Math.sqrt((Double.valueOf((nivel.getValorMedio().replace("<", "")))*376.73)/100))) 
	                    : String.valueOf(Math.sqrt((Double.valueOf((nivel.getValorMedio()))*376.73)/100)));
	            }
			});
	        return nivelesMedios;
	    });
	}
}
