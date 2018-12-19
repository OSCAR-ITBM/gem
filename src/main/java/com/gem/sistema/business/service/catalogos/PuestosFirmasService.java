package com.gem.sistema.business.service.catalogos;

import java.util.List;

import com.gem.sistema.business.dto.PuestosFirmasDTO;

public interface PuestosFirmasService {

	List<PuestosFirmasDTO> listFirmas(Integer idSector);
}
