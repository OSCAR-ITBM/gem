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
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF001028MB.
 */
@ManagedBean(name = "rF001028MB")
@ViewScoped
public class RF001028MB extends BaseDirectReport {

	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

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
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "RF001028_EdoAn";
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
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pNomMun", firmas.getCampo1());
		parameters.put("pClveMun", conctb.getClave());
		parameters.put("pL1", firmas.getL1());
		parameters.put("pN1", firmas.getN1());
		parameters.put("pL2", firmas.getL28());
		parameters.put("pN2", firmas.getN28());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pDescripMes", descripMes.getDescripcion());
		parameters.put("pLastDay", getLastDay(Integer.valueOf(mes)));
		parameters.put("pAn", firmas.getCampo3());
		parameters.put("pSsql", this.generaQuery(this.getUserDetails().getIdSector(), Integer.valueOf(mes)));
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
	 * @param mes the mes
	 * @return the string
	 */
	public String generaQuery(Integer idsector, Integer mes) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder cargos8110 = new StringBuilder();
		StringBuilder abonos8110 = new StringBuilder();
		StringBuilder cargos8140 = new StringBuilder();
		StringBuilder abonos8140 = new StringBuilder();
		StringBuilder cargos8150 = new StringBuilder();
		StringBuilder abonos8150 = new StringBuilder();

		sSql.append("SELECT ").append(" T2.NOMBRE,").append("T2.ESTIMADO ESTIMADO,").append("T2.TOT8110 AMPLIACIONES,")
				.append("(T2.ESTIMADO+T2.TOT8110) MODIFICADO,").append("T2.DEVENGADO DEVENGADO,")
				.append("T2.RECAUDADO RECAUDADO,").append("(T2.ESTIMADO+T2.TOT8110)-(T2.RECAUDADO)DIFERENCIA")
				.append(" FROM").append(" (SELECT ").append(" T1.NOMBRE,").append("T1.ESTIMADO,")
				.append("(T1.ABONOS8110-T1.CARGOS8110) TOT8110,").append("(T1.ABONOS8140-T1.CARGOS8140) DEVENGADO,")
				.append("(T1.ABONOS8150-T1.CARGOS8150) RECAUDADO").append(" FROM (SELECT ").append(" C.SCTA SCTA,")
				.append("C.SSCTA SSCTA,").append("C.SSSCTA SSSCTA,").append("C.SSSSCTA SSSSCTA,")
				.append("MAX(C.NOMCTA)  NOMBRE,").append(" SUM(DECODE(C.CUENTA,'8110',C.SALINI,0.00)) ESTIMADO,");

		for (int i = 1; i <= mes; i++) {
			cargos8110.append(" C.CARGOS_" + i + "+");
			abonos8110.append(" C.ABONOS_" + i + "+");
			cargos8140.append(" C.CARGOS_" + i + "+");
			abonos8140.append(" C.ABONOS_" + i + "+");
			cargos8150.append(" C.CARGOS_" + i + "+");
			abonos8150.append(" C.ABONOS_" + i + "+");
		}

		sSql.append(" SUM(DECODE(C.CUENTA,'8110',(").append(cargos8110.substring(1, cargos8110.length() - 1))
				.append("),0.00)) CARGOS8110,").append(" SUM(DECODE(C.CUENTA,'8110',(")
				.append(abonos8110.substring(1, abonos8110.length() - 1)).append("),0.00)) ABONOS8110,")
				.append(" SUM(DECODE(C.CUENTA,'8140',(").append(cargos8140.substring(1, cargos8140.length() - 1))
				.append("),0.00)) CARGOS8140,").append(" SUM(DECODE(C.CUENTA,'8140',(")
				.append(abonos8140.substring(1, abonos8140.length() - 1)).append("),0.00)) ABONOS8140,")
				.append(" SUM(DECODE(C.CUENTA,'8150',(").append(cargos8150.substring(1, cargos8150.length() - 1))
				.append("),0.00)) CARGOS8150,").append(" SUM(DECODE(C.CUENTA,'8150',(")
				.append(abonos8150.substring(1, abonos8150.length() - 1)).append("),0.00)) ABONOS8150")
				.append(" FROM CUENTA C").append(" WHERE C.IDSECTOR=" + idsector)
				.append(" AND C.CUENTA IN ('8110', '8140', '8150')").append(" AND C.NOTCTA=0  AND C.SSCTA='' ")
				.append(" GROUP BY ").append("  C.SCTA, ").append("C.SSCTA,").append("C.SSSCTA,").append(" C.SSSSCTA")
				.append(" ORDER BY C.SCTA,").append(" C.SSCTA,").append("C.SSSCTA,").append("C.SSSSCTA ASC)T1)T2");

		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();

	}
	
	public void validateMonth() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) ;
	}

}
