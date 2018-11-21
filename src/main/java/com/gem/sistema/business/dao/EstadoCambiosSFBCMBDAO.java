package com.gem.sistema.business.dao;

// TODO: Auto-generated Javadoc
/**
 * The Interface EstadoCambiosSFBCMBDAO.
 *
 * @author Dalia Tovar
 */
/**
 * @author buser
 *
 */
public interface EstadoCambiosSFBCMBDAO {

	/**
	 * Genera query activo.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	String generaQueryActivo(Integer idsector, Integer mes);
	
	/**
	 * Genera query pasivo.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	String generaQueryPasivo(Integer idsector, Integer mes);
	
	/**
	 * Genera query 3211.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	String generaQuery3211(Integer idsector, Integer mes);
}
