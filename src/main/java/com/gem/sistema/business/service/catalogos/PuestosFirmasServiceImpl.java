package com.gem.sistema.business.service.catalogos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.PuestosFirmasDAO;
import com.gem.sistema.business.dto.PuestosFirmasDTO;

@Service(value="puestosFirmasService")
public class PuestosFirmasServiceImpl implements PuestosFirmasService{

	@Autowired 
	private PuestosFirmasDAO puestosFirmasDAO;
	
	@Override
	public List<PuestosFirmasDTO> listFirmas(Integer idSector) {
		return puestosFirmasDAO.listPuestosFirmas(idSector);
	}

	public PuestosFirmasDAO getPuestosFirmasDAO() {
		return puestosFirmasDAO;
	}

	public void setPuestosFirmasDAO(PuestosFirmasDAO puestosFirmasDAO) {
		this.puestosFirmasDAO = puestosFirmasDAO;
	}

}
