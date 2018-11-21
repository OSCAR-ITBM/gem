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
 * The Class ECPIngresos212MB.
 */
@ManagedBean(name = "eCPIngresos212MB")
@ViewScoped
public class ECPIngresos212MB extends BaseDirectReport {

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
		jasperReporteName = "ECPIngresos212";
		endFilename = jasperReporteName + ".pdf";

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		parameters.put("pImgMun", this.getUserDetails().getPathImgCab1());
		parameters.put("pImgDep", this.getUserDetails().getPathImgCab2());
		parameters.put("pNomMun", firmas.getCampo1());
		parameters.put("pAnio", firmas.getCampo3());
		parameters.put("pL1", firmas.getL1());
		parameters.put("pN1", firmas.getN1());
		parameters.put("pL2", firmas.getL2());
		parameters.put("pN2", firmas.getN2());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pSsql",this.generaQuery(this.getUserDetails().getIdSector(), 12));

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
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder sSql = new StringBuilder();

		for (int i = 1; i <= xmes; i++) {
			cargos.append(" C.CARGOS_" + i + "+");
			abonos.append(" C.ABONOS_" + i + "+");
		}

		sSql.append("SELECT TF.CUENTA,").append(" TF.NOMBRE,").append(" TF.SALINI PRESUPUESTADO,")
		.append(" TF.XSF AJUSTE,").append(" TF.WSFX MODIFICADO,").append(" ABS (TF. WSFX - TF.WSF) RECAUDADO,")
		.append(" (TF.SALINI)-(ABS (TF. WSFX - TF.WSF)) ABSOLUTA,")
		.append(" CASE WHEN (TF.SALINI)-(ABS (TF. WSFX - TF.WSF)) <>0 AND TF.SALINI<>0")
		.append(" THEN (((TF.SALINI)-(ABS (TF. WSFX - TF.WSF)))*100.0000)/TF.SALINI").append(" ELSE 0.00 ")
		.append(" END  PORCENTAJE").append(" FROM  (SELECT '8150'||' '|| SUBSTR(T1.SCTA,7,4)")
		.append(" ||' '|| SUBSTR(T1.SSCTA,13,3) ").append(" ||' '|| SUBSTR(T1.SSSCTA,2,3) ")
		.append(" ||' '|| T1.SSSSCTA CUENTA,").append(" T1.NOMCTA NOMBRE,").append(" T1.SALINI SALINI,")
		.append(" (T1.SALINI - T1.TCAR + T1.TABO) XSF,").append(" (T1.SALINI-T2.WCAR+ T2.WABO) WSF,")
		.append(" (T1.SALINI - T3.WCARX + WABOX) WSFX").append("  FROM (SELECT  C.CUENTA,").append(" C.SCTA,")
		.append(" C.SSCTA,").append(" C.SSSCTA,").append(" C.SSSSCTA,").append(" C.NOMCTA,")
		.append(" C.SALINI,").append(" SUM(").append(cargos.substring(1, cargos.length() - 1))
		.append(" )TCAR, ").append(" SUM(").append(abonos.substring(1, abonos.length() - 1)).append(" )TABO")
		.append(" FROM CUENTA C ").append(" WHERE C.CUENTA = '8110' ").append(" AND C.SCTA <> '' ")
		.append(" AND C.IDSECTOR=" + idsector).append(" GROUP BY  C.CUENTA,").append(" C.SCTA, ")
		.append(" C.SSCTA, ").append(" C.SSSCTA,").append(" C.SSSSCTA,").append(" C.NOMCTA,")
		.append(" C.SALINI)T1,").append(" (SELECT C.CUENTA,").append(" C.SCTA,").append(" C.SSCTA,")
		.append(" C.SSSCTA,").append(" C.SSSSCTA,").append(" SUM(")
		.append(cargos.substring(1, cargos.length() - 1)).append(" )WCAR,").append(" SUM(")
		.append(abonos.substring(1, abonos.length() - 1)).append(" )WABO").append(" FROM CUENTA C ")
		.append(" WHERE C.CUENTA = '8150'").append(" AND C.IDSECTOR = " + idsector)
		.append(" GROUP BY  C.CUENTA,").append(" C.SCTA,").append(" C.SSCTA,").append(" C.SSSCTA,")
		.append(" C.SSSSCTA)T2,").append(" (SELECT C.CUENTA,").append(" C.SCTA,").append(" C.SSCTA,")
		.append(" C.SSSCTA,").append(" C.SSSSCTA,").append(" SUM(")
		.append(cargos.substring(1, cargos.length() - 1)).append(" )WCARX,").append(" SUM(")
		.append(abonos.substring(1, abonos.length() - 1)).append(" )WABOX").append("  FROM CUENTA C ")
		.append(" WHERE C.CUENTA = '8110'").append(" AND C.IDSECTOR = " + idsector)
		.append(" GROUP BY  C.CUENTA,").append(" C.SCTA,").append(" C.SSCTA,").append(" C.SSSCTA,")
		.append(" C.SSSSCTA )T3").append(" WHERE T1.SCTA = T2.SCTA").append(" AND T1.SSCTA = T2.SSCTA")
		.append(" AND T1.SSSCTA = T2.SSSCTA").append(" AND T1.SSSSCTA = T2.SSSSCTA")
		.append(" AND T1.SCTA = T3.SCTA").append(" AND T1.SSCTA = T3.SSCTA")
		.append(" AND T1.SSSCTA = T3.SSSCTA").append(" AND T1.SSSSCTA = T3.SSSSCTA")
		.append(" ORDER BY T1.CUENTA,").append(" T1.SCTA,").append(" T1.SSCTA,").append(" T1.SSSCTA,")
		.append(" T1.SSSSCTA)TF").append(" WHERE TF.SALINI <>0").append(" AND  TF.XSF <>0")
		.append(" AND  TF.XSF >= 0").append(" AND  TF.WSF <>0");


		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();

	}

}
