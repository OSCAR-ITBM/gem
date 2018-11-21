package com.gem.sistema.business.predicates;

import com.gem.sistema.business.domain.QFinalidad;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class FinalidadPredicates.
 */
public class FinalidadPredicates {

	/**
	 * Instantiates a new finalidad predicates.
	 */
	private FinalidadPredicates() {
	}

	/**
	 * Exists finalidad.
	 *
	 * @param clvdepg the clvdepg
	 * @param cveprog the cveprog
	 * @param cvetemas the cvetemas
	 * @return the predicate
	 */
	public static Predicate existsFinalidad(String clvdepg, String cveprog, String cvetemas) {
		return QFinalidad.finalidad.clvdepg.eq(clvdepg).and(QFinalidad.finalidad.cveprog.eq(cveprog))
				.and(QFinalidad.finalidad.cvetemas.eq(cvetemas));
	}

	/**
	 * Find by prog and tema and final.
	 *
	 * @param cveDpg the cve dpg
	 * @param programa the programa
	 * @param tema the tema
	 * @return the predicate
	 */
	public static Predicate findByProgAndTemaAndFinal(String cveDpg, String programa, String tema) {
		return QFinalidad.finalidad.clvdepg.eq(cveDpg).and(QFinalidad.finalidad.cveprog.eq(programa))
				.and(QFinalidad.finalidad.cvetemas.eq(tema));
	}
}
