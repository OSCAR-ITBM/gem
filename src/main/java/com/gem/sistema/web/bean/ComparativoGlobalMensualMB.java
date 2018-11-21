package com.gem.sistema.web.bean;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class ComparativoGlobalMensualMB.
 */
@ManagedBean
@ViewScoped
public class ComparativoGlobalMensualMB extends BaseDirectReport {
	
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	/** The mes. */
	private Integer mes;

	/** The num firmas. */
	private Integer numFirmas;

	/** The firmas. */
	private Firmas firmas;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/**
	 * Gets the num firmas.
	 *
	 * @return the num firmas
	 */
	public Integer getNumFirmas() {
		return numFirmas;
	}

	/**
	 * Sets the num firmas.
	 *
	 * @param numFirmas
	 *            the new num firmas
	 */
	public void setNumFirmas(Integer numFirmas) {
		this.numFirmas = numFirmas;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ComparativoGlobalMensualMB ");
		jasperReporteName = "ReporteComparativo_GlobalMensual";
		endFilename = jasperReporteName + ".pdf";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		firmas = firmasRepository.findAllByIdsector(getUserDetails().getIdSector());
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		GemUser user = this.getUserDetails();
		paramsReport.put("mes", mes);
		paramsReport.put("sector", user.getIdSector());
		paramsReport.put("numeroFirmas", numFirmas);
		paramsReport.put("anio", firmas.getCampo3().toString());
		paramsReport.put("campo1", firmas.getCampo1());
		paramsReport.put("L1", firmas.getL4());// ELABORO
		paramsReport.put("N1", firmas.getN4());
		paramsReport.put("L2", firmas.getL5());// REVISO
		paramsReport.put("N2", firmas.getN5());
		paramsReport.put("L3", firmas.getL27());// AUTORIZO
		paramsReport.put("N3", firmas.getN27());
		paramsReport.put("L4", firmas.getL28());// SINDICO
		paramsReport.put("N4", firmas.getN28());
		// L4, N4, L5, N5, L27 y N27

		return paramsReport;
	}

	public void downloadTxt() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
