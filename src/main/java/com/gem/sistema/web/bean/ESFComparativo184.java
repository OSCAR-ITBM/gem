package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ESFComparativo184.
 */
@ManagedBean(name = "eSFComparativo184MB")
@ViewScoped
public class ESFComparativo184 extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "ESFComparativo184";
		endFilename = jasperReporteName + ".pdf";

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pNomMun",firmas.getCampo1());
		parameters.put("pAnio", firmas.getCampo3());
		parameters.put("pL1", firmas.getL27());
		parameters.put("pN1", firmas.getN27());
		parameters.put("pL2", firmas.getL5());
		parameters.put("pN2", firmas.getN5());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pSsql1", this.generaActivo(this.getUserDetails().getIdSector(), 12));
		parameters.put("pSsql2", this.generaPasivo(this.getUserDetails().getIdSector(), 12));
		parameters.put("pSsql3", this.generaPatrimonio(this.getUserDetails().getIdSector(), 12));

		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Genera activo.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generaActivo(Integer idsector, Integer mes) {

		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder sSql1 = new StringBuilder();

		sSql1.append("SELECT T3.GRUPO,").append("T3.CUENTA,").append("T3.NOMBRE,").append("T3.NATCTA,")
				.append(" DECIMAL(T3.SALINI,24,2) SALINI,").append("DECIMAL(T3.SF,24,2) SF,")
				.append("DECIMAL((T3.SALINI)-(T3.SF),24,2) VARIACION").append(" FROM (SELECT T2.GRUPO,")
				.append("T2.CUENTA,").append("T2.NOMBRE,").append(" T2.NATCTA,")
				.append("CASE WHEN T2.NATCTA ='A' THEN (T2.SALINI*-1)").append(" ELSE T2.SALINI").append(" END SALINI,")
				.append("CASE WHEN T2.NATCTA ='A' THEN (T2.SF*-1)").append(" ELSE T2.SF").append(" END SF")
				.append(" FROM ").append(" (SELECT T1.GRUPO,").append("T1.CUENTA,").append(" T1.NOMBRE,")
				.append("T1.NATCTA,").append("T1.SALINI,")
				.append(" CASE WHEN T1.NATCTA ='D' THEN (T1.SALINI+T1.CARGOS-T1.ABONOS)")
				.append(" ELSE (T1.SALINI-T1.CARGOS+T1.ABONOS) ").append("  END SF ")
				.append("  FROM (SELECT SUBSTR(C.CUENTA,1,2) GRUPO,").append("C.CUENTA  CUENTA,")
				.append("C.NOMCTA NOMBRE,").append(" M.NATCTA NATCTA,").append(" SUM(C.SALINI) SALINI,");

		for (int i = 1; i <= mes; i++) {
			cargos.append(" C.CARGOS_" + i + "+");
			abonos.append(" C.ABONOS_" + i + "+");
		}
		sSql1.append(" SUM(").append(cargos.substring(1, cargos.length() - 1)).append(")CARGOS,").append(" SUM(")
				.append(abonos.substring(1, abonos.length() - 1)).append(" )ABONOS").append(" FROM CUENTA C ")
				.append(" JOIN MAYCTA M ON M.CUENTA=C.CUENTA ").append(" WHERE C.IDSECTOR =" + idsector)
				.append(" AND SCTA = ''").append("AND C.CUENTA BETWEEN '1111' AND '1399' ")
				.append(" AND SUBSTR(C.CUENTA,3,2)<>'00'").append(" AND SUBSTR(C.CUENTA,3,2)<>'10'")
				.append(" GROUP BY SUBSTR(C.CUENTA,1,2),").append("  C.CUENTA,").append("C.NOMCTA,")
				.append("  M.NATCTA").append(" ORDER BY C.CUENTA)T1)T2)T3");

		LOGGER.info("QUERY1,{}", sSql1.toString());
		return sSql1.toString();

	}

	/**
	 * Genera pasivo.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generaPasivo(Integer idsector, Integer mes) {
		StringBuilder cargos_p = new StringBuilder();
		StringBuilder abonos_p = new StringBuilder();
		StringBuilder sSql2 = new StringBuilder();

		sSql2.append("SELECT T2.GRUPO,").append("T2.CUENTA,").append(" T2.NOMBRE,").append("T2.NATCTA,")
				.append("DECIMAL(T2.SALINI,24,2) SALINI,").append("DECIMAL(T2.SF,24,2) SF,")
				.append("DECIMAL((T2.SALINI)-(T2.SF),24,2) VARIACION").append(" FROM (SELECT T1.GRUPO,")
				.append("T1.CUENTA,").append(" T1.NOMBRE,").append("T1.NATCTA,").append("T1.SALINI,")
				.append(" CASE WHEN T1.NATCTA='D' THEN (T1.SALINI+T1.CARGOS-T1.ABONOS)")
				.append(" ELSE (T1.SALINI-T1.CARGOS+T1.ABONOS)").append("END SF")
				.append(" FROM (SELECT SUBSTR(C.CUENTA,1,2) GRUPO,").append(" C.CUENTA  CUENTA,")
				.append(" C.NOMCTA NOMBRE,").append(" M.NATCTA NATCTA,").append("SUM(C.SALINI) SALINI,");

		for (int i = 1; i <= mes; i++) {
			cargos_p.append(" C.CARGOS_" + i + "+");
			abonos_p.append(" C.ABONOS_" + i + "+");
		}
		sSql2.append(" SUM(").append(cargos_p.substring(1, cargos_p.length() - 1)).append(")CARGOS,").append(" SUM(")
				.append(abonos_p.substring(1, abonos_p.length() - 1)).append(" )ABONOS").append(" FROM CUENTA C ")
				.append(" JOIN MAYCTA M ON M.CUENTA=C.CUENTA ").append("  WHERE C.IDSECTOR =" + idsector)
				.append(" AND SCTA = ''").append(" AND C.CUENTA BETWEEN '2111'  AND  '2399' ")
				.append(" AND SUBSTR(C.CUENTA,3,2)<>'00'").append(" AND SUBSTR(C.CUENTA,3,2)<>'10'")
				.append(" GROUP BY SUBSTR(C.CUENTA,1,2),").append(" C.CUENTA,").append("C.NOMCTA,").append("M.NATCTA")
				.append(" ORDER BY C.CUENTA)T1)T2");

		LOGGER.info("QUERY2,{}", sSql2.toString());

		return sSql2.toString();

	}

	/**
	 * Genera patrimonio.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generaPatrimonio(Integer idsector, Integer mes) {
		StringBuilder cargos_pat = new StringBuilder();
		StringBuilder abonos_pat = new StringBuilder();
		StringBuilder sSql3 = new StringBuilder();

		for (int i = 1; i <= mes; i++) {
			cargos_pat.append(" C.CARGOS_" + i + "+");
			abonos_pat.append(" C.ABONOS_" + i + "+");
		}

		sSql3.append("SELECT TG.CUENTA,").append("TG.NOMBRE,").append("TG.NATCTA,").append(" TG.SALINI,")
				.append("TG.SF,").append(" (TG.SALINI-TG.SF) VARIACION").append(" FROM(SELECT T2.CUENTA,")
				.append(" T2.NOMBRE,").append(" T2.NATCTA,")
				.append(" CASE WHEN T2.CUENTA='3211' THEN (T14.RAV11-T155.RAV22)").append(" ELSE T2.SALINI")
				.append(" END SALINI,").append(" CASE WHEN T2.CUENTA='3211' THEN (T14.RAV1-T155.RAV2)")
				.append(" ELSE T2.SF").append(" END SF").append(" FROM (SELECT T2.CUENTA,").append("T2.NOMBRE,")
				.append("T2.NATCTA,").append(" CASE WHEN T2.CUENTA='3241' THEN ")
				.append(" CASE WHEN T2.SALINI < 0 THEN ABS(T2.SALINI)").append(" ELSE (T2.SALINI*-1)").append(" END")
				.append(" ELSE T2.SALINI").append(" END SALINI,").append(" CASE WHEN T2.CUENTA='3241' THEN ")
				.append("CASE WHEN T2.SF < 0 THEN ABS(T2.SF)").append(" ELSE (T2.SF*-1)").append(" END")
				.append(" ELSE T2.SF").append(" END SF").append(" FROM (SELECT T1.CUENTA,").append(" T1.NOMBRE,")
				.append(" T1.NATCTA,").append(" T1.SALINI,").append(" CASE WHEN T1.NATCTA='D'").append(" THEN")
				.append(" CASE WHEN T1.CUENTA='3241' THEN (T1.SALINI-T1.CARGOS+T1.ABONOS)")
				.append(" ELSE (T1.SALINI+T1.CARGOS-T1.ABONOS)").append(" END ")
				.append(" ELSE  (T1.SALINI-T1.CARGOS+T1.ABONOS)").append(" END SF")
				.append(" FROM (SELECT C.CUENTA  CUENTA,").append(" C.NOMCTA NOMBRE,").append("M.NATCTA NATCTA,")
				.append(" SUM(C.SALINI) SALINI,").append(" SUM(")
				.append(cargos_pat.substring(1, cargos_pat.length() - 1)).append(")CARGOS,").append(" SUM(")
				.append(abonos_pat.substring(1, abonos_pat.length() - 1)).append(" )ABONOS").append(" FROM CUENTA C ")
				.append(" JOIN MAYCTA M ON M.CUENTA=C.CUENTA ").append(" WHERE C.IDSECTOR =" + idsector)
				.append(" AND SCTA = ''").append(" AND C.CUENTA BETWEEN '3111' AND '3321'")
				.append(" GROUP BY C.CUENTA,").append(" C.NOMCTA,").append(" M.NATCTA")
				.append(" ORDER BY C.CUENTA)T1)T2)T2,").append(" ( SELECT (T14.SALINI+T14.ABONOS-T14.CARGOS) RAV1,")
				.append(" T14. SALINI RAV11").append(" FROM (SELECT SUM(C.SALINI) SALINI,").append(" SUM(")
				.append(cargos_pat.substring(1, cargos_pat.length() - 1)).append(")CARGOS,").append(" SUM(")
				.append(abonos_pat.substring(1, abonos_pat.length() - 1)).append(" )ABONOS").append(" FROM CUENTA C ")
				.append(" WHERE C.IDSECTOR =" + idsector).append(" AND SCTA = ''")
				.append(" AND C.CUENTA BETWEEN '4100' AND '4300' )T14)T14,")
				.append("(SELECT (T155.SALINI+T155.CARGOS-T155.ABONOS) RAV2,").append(" T155.SALINI RAV22")
				.append(" FROM (SELECT").append(" SUM(C.SALINI) SALINI,").append(" SUM(")
				.append(cargos_pat.substring(1, cargos_pat.length() - 1)).append(")CARGOS,").append(" SUM(")
				.append(abonos_pat.substring(1, abonos_pat.length() - 1)).append(" )ABONOS").append(" FROM CUENTA C ")
				.append(" WHERE C.CUENTA IN('5500')").append(" AND C.IDSECTOR =" + idsector).append(" AND C.SCTA <> ''")
				.append("  AND C.SSCTA='' )T155)T155)TG");
		LOGGER.info("QUERY3,{}", sSql3.toString());
		return sSql3.toString();

	}

}
