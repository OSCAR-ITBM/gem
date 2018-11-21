package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.dao.EdoSF3211DAO;
import com.gem.sistema.business.dto.EdoSF3211DTO;
// TODO: Auto-generated Javadoc

/**
 * The Class EdoSF3211ServiceImpl.
 */
@Service(value="edoSF3211Service")
public class EdoSF3211ServiceImpl implements EdoSF3211Service {
	
	/** The edo SF 3211 DAO. */
	@Autowired
	private EdoSF3211DAO  edoSF3211DAO;
	

	/**
	 * Gets the edo SF 3211 DAO.
	 *
	 * @return the edo SF 3211 DAO
	 */
	public EdoSF3211DAO getEdoSF3211DAO() {
		return edoSF3211DAO;
	}


	/**
	 * Sets the edo SF 3211 DAO.
	 *
	 * @param edoSF3211DAO the new edo SF 3211 DAO
	 */
	public void setEdoSF3211DAO(EdoSF3211DAO edoSF3211DAO) {
		this.edoSF3211DAO = edoSF3211DAO;
	}


	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.catalogos.EdoSF3211Service#executeQuery(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public EdoSF3211DTO executeQuery(Integer idsector, Integer mes) {

		return this.getEdoSF3211DAO().executeQuery(idsector, mes);
	}

}
