package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.dto.Pm3711DTO;

public interface Pm3711DAO {

	List<Pm3711DTO> findAll();
	
	void deletePm3711(Integer idRow);
}
