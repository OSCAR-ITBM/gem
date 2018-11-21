package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010116MB.
 */
@ManagedBean(name = "rF0010116MB")
@ViewScoped
public class RF0010116MB extends BaseDirectReport {

	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The firmas. */
	private Firmas firmas;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

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
		jasperReporteName = "RF0010116_EdoA";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters;
		TcMes descripMes = tcMesRepository.findByMes(mes);
		parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pDescripMun", this.getUserDetails().getMunicipio());
		parameters.put("pLastDay", getLastDay(Integer.valueOf(mes)));
		parameters.put("pDescripMes", descripMes.getDescripcion());
		parameters.put("pAnio", firmas.getCampo3());
		parameters.put("pL1", firmas.getL4());
		parameters.put("pN1", firmas.getN4());
		parameters.put("pL2", firmas.getL5());
		parameters.put("pN2", firmas.getN5());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pSsql", this.generateQuery(this.getUserDetails().getIdSector(), Integer.valueOf(mes)));
		parameters.put("pSsql2", this.generatePasivo(this.getUserDetails().getIdSector(), Integer.valueOf(mes)));
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
	 * Generate query.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generateQuery(Integer idsector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder debe1 = new StringBuilder();
		StringBuilder haber1 = new StringBuilder();

		sSql.append("SELECT ").append("T2.CUENTA,").append("T2.NAT,").append("T2.NOMBRE,").append("T2.SALDOINI,")
				.append("T2.SIHABER,").append("CASE WHEN T2.NAT <>'D'").append(" THEN T2.SIHABER-T2.DEBE2+T2.HABER2")
				.append(" ELSE 0.00 ").append(" END SALDOFINPER ").append("FROM ").append(" (SELECT ")
				.append("T1.CUENTA,").append(" T1.NAT NAT,").append("T1.NOMBRE NOMBRE,").append("T1.SALDOINI SALDOINI,")
				.append(" CASE WHEN T1.NAT <>'D'").append(" THEN  SALDOINI-T1.DEBE1+T1.HABER1").append(" ELSE 0.00 ")
				.append(" END SIHABER,").append("T1.DEBE2,").append("T1.HABER2").append(" FROM ").append("(SELECT ")
				.append(" M.NATCTA NAT,").append("C.NOMCTA NOMBRE,").append("C.CUENTA CUENTA,").append("C.SCTA  SCTA,")
				.append(" C.SSCTA SSCTA,").append("C.SSSCTA SSSCTA,").append("C.SSSSCTA SSSSCTA,")
				.append(" DECODE (C.CUENTA ,'1115',-C.SALINI,C.SALINI,C.CUENTA ,'1161',-C.SALINI, C.SALINI) SALDOINI,")
				.append(" M.NOTCTA,");

		if (mes == 1) {

			sSql.append("SUM (0.00)DEBE1,").append("SUM (0.00)HABER1,");
		} else {

			for (int i = 1; i < mes; i++) {
				debe1.append(" C.CARGOS_" + i + "+");
				haber1.append(" C.ABONOS_" + i + "+");
			}

			sSql.append("SUM(").append(debe1.substring(1, debe1.length() - 1)).append(")DEBE1,").append("SUM( ")
					.append(haber1.substring(1, haber1.length() - 1)).append(")HABER1,");
		}

		sSql.append("C.CARGOS_" + mes).append(" DEBE2,").append("C.ABONOS_" + mes).append(" HABER2")
				.append(" FROM MAYCTA M").append(" JOIN CUENTA C ON C.CUENTA=M.CUENTA")
				.append(" WHERE C.IDSECTOR=" + idsector).append(" AND C.CUENTA >='1001'")
				.append(" AND C.CUENTA <='6039'").append(" AND C.SCTA=''").append("AND C.NOTCTA=0 ")
				.append(" GROUP BY M.NATCTA,").append("C.NOMCTA,").append("C.CUENTA,").append("C.SCTA,")
				.append("C.SSCTA,").append("C.SSSCTA,").append("C.SSSSCTA,").append("C.SALINI,").append("M.NOTCTA,")
				.append("C.CARGOS_" + mes + ",").append(" C.ABONOS_" + mes).append(" ORDER BY C.CUENTA,")
				.append(" C.SCTA,").append("C.SSCTA,").append(" C.SSSCTA,").append("C.SSSSCTA)T1)T2");

		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();
	}
	
	/**
	 * Generate pasivo.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generatePasivo(Integer idsector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		for (int i = 1; i <= mes; i++) {
			cargos.append(" C.CARGOS_" + i + "+");
			abonos.append(" C.ABONOS_" + i + "+");
		}

		sSql.append("SELECT T2.CUENTA,")
	 	    .append(" T2.NOMCTA,")
	 	    .append(" T2.SIHABER,")
		    .append(" CASE WHEN T2.NATCTA <> 'D' THEN")
		    .append("  T2.SIHABER - T2.CARGOS_" + mes+ " +T2.ABONOS_" + mes)
		    .append(" ELSE  0.00 END SIHABER2")
	        .append(" FROM ( ")
	        .append(" SELECT T1.CUENTA,")
	        .append(" T1.NOMCTA, ")
	        .append(" T1.NATCTA,")
			.append(" T1.CARGOS_" + mes+",")
			.append(" T1.ABONOS_" + mes+",")
			.append(" CASE WHEN T1.NATCTA <> 'D' THEN")
			.append(" T1.SALINI")
			.append(" ELSE")
			.append(" T1.SALINI - T1.CARGOS + T1.ABONOS")
			.append(" END SIHABER")
			.append("  FROM (").append(" SELECT C.CUENTA,").append(" C.NOMCTA,")
				.append(" C.SALINI,").append(" C.SCTA,").append(" C.SSCTA,").append(" C.SSSCTA,").append(" M.NATCTA,")
				.append(" C.NOTCTA,").append(" C.CARGOS_" + mes + ",").append("  C.ABONOS_" + mes + ",")
				.append("(").append(cargos.substring(1,cargos.length()-1)).append(" )CARGOS,")
				.append("(").append(abonos.substring(1,abonos.length()-1)).append(" ) ABONOS")
				.append(" FROM   MAYCTA M")
				.append(" INNER JOIN CUENTA C ON M.CUENTA=C.CUENTA")
				.append(" WHERE  (C.CUENTA>='2000' AND C.CUENTA< '3000')")
				.append("  AND C.SCTA  = ''")
				.append(" AND C.NOTCTA = 0")
				.append(" AND C.IDSECTOR = "+idsector)
				.append(" ORDER BY C.CUENTA,")
				.append(" C.SCTA,")
				.append(" C.SSCTA,")
				.append(" C.SSSCTA,")
				.append(" C.SSSSCTA")
				.append(" ) T1) T2");

		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();
	}


	

}
