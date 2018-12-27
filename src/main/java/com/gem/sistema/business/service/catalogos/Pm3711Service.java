package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.Pm3711DTO;

public interface Pm3711Service {

	List<Pm3711DTO> listAll();
	
	List<Pm3711DTO> deletePm3711(Integer idRow);
}
