package com.gem.sistema.business.dao;

import java.util.List;

import com.gem.sistema.business.dto.PuestosFirmasDTO;

public interface PuestosFirmasDAO {

	List<PuestosFirmasDTO> listPuestosFirmas(Integer idSector);
}
