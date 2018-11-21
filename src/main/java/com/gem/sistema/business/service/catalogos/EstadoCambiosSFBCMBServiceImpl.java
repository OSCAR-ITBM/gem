package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.EstadoCambiosSFBCMBDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCambiosSFBCMBServiceImpl.
 */
@Service(value ="estadoCambiosSFBCMBService")
public class EstadoCambiosSFBCMBServiceImpl implements EstadoCambiosSFBCMBService{

	/** The estado cambios SFBCMBDAO. */
	@Autowired
	private  EstadoCambiosSFBCMBDAO  estadoCambiosSFBCMBDAO;
	
	
	
	/**
	 * Gets the estado cambios SFBCMBDAO.
	 *
	 * @return the estado cambios SFBCMBDAO
	 */
	public EstadoCambiosSFBCMBDAO getEstadoCambiosSFBCMBDAO() {
		return estadoCambiosSFBCMBDAO;
	}

	/**
	 * Sets the estado cambios SFBCMBDAO.
	 *
	 * @param estadoCambiosSFBCMBDAO the new estado cambios SFBCMBDAO
	 */
	public void setEstadoCambiosSFBCMBDAO(EstadoCambiosSFBCMBDAO estadoCambiosSFBCMBDAO) {
		this.estadoCambiosSFBCMBDAO = estadoCambiosSFBCMBDAO;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EstadoCambiosSFBCMBService#generaQueryActivo(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generaQueryActivo(Integer idsector, Integer mes) {
		
		return this.estadoCambiosSFBCMBDAO.generaQueryActivo(idsector, mes);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EstadoCambiosSFBCMBService#generaQueryPasivo(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generaQueryPasivo(Integer idsector, Integer mes) {
		
		return this.estadoCambiosSFBCMBDAO.generaQueryPasivo(idsector, mes);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EstadoCambiosSFBCMBService#generaQuery3211(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String generaQuery3211(Integer idsector, Integer mes) {
		
		return this.estadoCambiosSFBCMBDAO.generaQuery3211(idsector, mes);
	}
 

}
