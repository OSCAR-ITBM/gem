package com.gem.sistema.business.bs;

import java.util.List;

import com.gem.sistema.business.dto.Pm3711DTO;

public interface Pm3711BS {

	List<Pm3711DTO> findAll();
	
	void deletePm3711(Integer idRow);
}