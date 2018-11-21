package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QActividad;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class ActividadPredicate.
 *
 * @author Mateo
 */
public class ActividadPredicate {

	/**
	 * Find actividad.
	 *
	 * @param cveDpe the cve dpe
	 * @param programa the programa
	 * @param tema the tema
	 * @return the predicate
	 */
	public static Predicate findActividad(String cveDpe, String programa, String tema) {
		return QActividad.actividad.clvdepg.eq(cveDpe).and(QActividad.actividad.cveprog.eq(programa))
				.and(QActividad.actividad.cvetemas.eq(tema));
	}

}
