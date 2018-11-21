package com.gem.sistema.web.bean;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF001017MB.
 */
@ManagedBean
@ViewScoped
public class RF001017MB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The mes. */
	private Integer mes;

	/** The cuenta inicial. */
	private Integer cuentaInicial;

	/** The cuenta final. */
	private Integer cuentaFinal;

	/** The nivel. */
	private Integer nivel;

	/** The con saldo cero. */
	private String conSaldoCero;

	/** The firmas. */
	private Firmas firmas;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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
	 * @param firmas
	 *            the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
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
	 * @param firmasRepository
	 *            the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the cuenta inicial.
	 *
	 * @return the cuenta inicial
	 */
	public Integer getCuentaInicial() {
		return cuentaInicial;
	}

	/**
	 * Sets the cuenta inicial.
	 *
	 * @param cuentaInicial
	 *            the new cuenta inicial
	 */
	public void setCuentaInicial(Integer cuentaInicial) {
		this.cuentaInicial = cuentaInicial;
	}

	/**
	 * Gets the cuenta final.
	 *
	 * @return the cuenta final
	 */
	public Integer getCuentaFinal() {
		return cuentaFinal;
	}

	/**
	 * Sets the cuenta final.
	 *
	 * @param cuentaFinal
	 *            the new cuenta final
	 */
	public void setCuentaFinal(Integer cuentaFinal) {
		this.cuentaFinal = cuentaFinal;
	}

	/**
	 * Gets the nivel.
	 *
	 * @return the nivel
	 */
	public Integer getNivel() {
		return nivel;
	}

	/**
	 * Sets the nivel.
	 *
	 * @param nivel
	 *            the new nivel
	 */
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	/**
	 * Gets the con saldo cero.
	 *
	 * @return the con saldo cero
	 */
	public String getConSaldoCero() {
		return conSaldoCero;
	}

	/**
	 * Sets the con saldo cero.
	 *
	 * @param conSaldoCero
	 *            the new con saldo cero
	 */
	public void setConSaldoCero(String conSaldoCero) {
		this.conSaldoCero = conSaldoCero;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF001017MB ");
		jasperReporteName = "m_balanzacdet";
		endFilename = jasperReporteName + ".pdf";

		cuentaInicial = 1111;
		cuentaFinal = 9999;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		if (cuentaInicial == null)
			cuentaInicial = 0;
		if (cuentaFinal == null)
			cuentaFinal = 0;

		if (cuentaInicial > cuentaFinal) {
			throw new ReportValidationException("La cuenta inicial no puede ser mayor que la cuenta final.");
		}
		Integer sector = this.getUserDetails().getIdSector();
		firmas = firmasRepository.findAllByIdsector(sector);

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		paramsReport.put("USUARIO", getUserDetails().getUsername());
		paramsReport.put("MES", mes);
		paramsReport.put("CTA_INICIO", cuentaInicial.toString());
		paramsReport.put("CTA_FIN", cuentaFinal.toString());
		paramsReport.put("NIVEL", nivel);
		paramsReport.put("C_SALDOCERO", conSaldoCero);
		paramsReport.put("name", firmas.getCampo1());
		paramsReport.put("year", firmas.getCampo3());
		paramsReport.put("pathImage", getUserDetails().getPathImgCab1());
		paramsReport.put("firmaP1", (sector == 1)? firmas.getL4() : firmas.getL4());
		paramsReport.put("firmaP2", (sector == 1)? firmas.getL5() : firmas.getL5());
		paramsReport.put("firmaP3", (sector == 1)? firmas.getL3() : firmas.getL27());
		paramsReport.put("firmaN1", (sector == 1)? firmas.getN4() : firmas.getN4());
		paramsReport.put("firmaN2", (sector == 1)? firmas.getN5() : firmas.getN5());
		paramsReport.put("firmaN3", (sector == 1)? firmas.getN3() : firmas.getN27());

		return paramsReport;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String,
		 * Object>(); paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes
	 *            the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

}
