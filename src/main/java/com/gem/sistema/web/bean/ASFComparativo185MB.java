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
 * The Class ASFComparativo185MB.
 */
@ManagedBean(name = "aSFComparativo185MB")
@ViewScoped
public class ASFComparativo185MB extends BaseDirectReport {

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
		jasperReporteName = "ASFComparativo185";
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
		parameters.put("pNomMun", firmas.getCampo1());
		parameters.put("pAnio", firmas.getCampo3().toString());
		parameters.put("pL1", firmas.getL27());
		parameters.put("pN1", firmas.getN27());
		parameters.put("pL2", firmas.getL5());
		parameters.put("pN2", firmas.getN5());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pSsql", this.generaQuery(this.getUserDetails().getIdSector(), 12));

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
	 * Genera query.
	 *
	 * @param idsector the idsector
	 * @param xmes the xmes
	 * @return the string
	 */
	public String generaQuery(Integer idsector, Integer xmes) {

		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder sSql = new StringBuilder();

		sSql.append("SELECT (T1.CUENTA||' '||NVL(T1.SCTA,'')||' '||NVL(T1.SSCTA,'')||' '||NVL(T1.SSSCTA,'')||' '||NVL(T1.SSSSCTA,'')) CUENTA,")
				.append("T1.NOMBRE,").append("T1.NATCTA,").append(" T1.SALINI,").append(" T1.CARGOS,")
				.append(" T1.ABONOS,").append(" CASE WHEN T1.NATCTA ='D' THEN (T1.SALINI+T1.CARGOS-T1.ABONOS)")
				.append(" ELSE (T1.SALINI-T1.CARGOS+T1.ABONOS)").append(" END XSF")
				.append(" FROM (SELECT C.CUENTA CUENTA,").append(" C.SCTA SCTA,").append(" C.SSCTA SSCTA,")
				.append("C.SSSCTA SSSCTA,").append("C.SSSSCTA SSSSCTA,").append("C.NOMCTA NOMBRE,")
				.append(" M.NATCTA NATCTA,").append(" C.SALINI SALINI,");

		for (int i = 1; i <= xmes; i++) {

			cargos.append(" C.CARGOS_" + i + "+");
			abonos.append(" C.ABONOS_" + i + "+");

		}
		sSql.append(" SUM(").append(cargos.substring(1, cargos.length() - 1)).append(")CARGOS,").append(" SUM(")
				.append(abonos.substring(1, abonos.length() - 1)).append(" )ABONOS").append(" FROM CUENTA C")
				.append(" JOIN MAYCTA M ON M.CUENTA = C.CUENTA").append(" WHERE C.IDSECTOR=" + idsector)
				.append(" AND C.CUENTA>='1101' AND C.CUENTA<='3019'").append(" GROUP BY C.CUENTA,").append(" C.SCTA,")
				.append(" C.SSCTA,").append(" C.SSSCTA,").append(" C.SSSSCTA,").append("C.NOMCTA,").append(" M.NATCTA,")
				.append(" C.SALINI").append(" ORDER BY C.CUENTA,").append(" C.SCTA,").append(" C.SSCTA,")
				.append(" C.SSSCTA,").append(" C.SSSSCTA ASC)T1");

		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();

	}

}
