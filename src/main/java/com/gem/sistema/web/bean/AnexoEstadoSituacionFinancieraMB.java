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

// TODO: Auto-generated Javadoc
/**
 * The Class AnexoEstadoSituacionFinancieraMB.
 */
@ManagedBean
@ViewScoped
public class AnexoEstadoSituacionFinancieraMB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/** The mes. */
	private Integer mes;

	/** The cuenta inicial. */
	private Integer cuentaInicial;

	/** The cuenta final. */
	private Integer cuentaFinal;

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

		LOGGER.info(":: En postconsruct AnexoEstadoSituacionFinancieraMB ");
		// reportId = 2;
		jasperReporteName = "AnexoEstadoSituacionFinanciera";
		endFilename = jasperReporteName + ".pdf";

		cuentaInicial = 1000;
		cuentaFinal = 9130;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		parameters.put("P_SECTOR", getUserDetails().getIdSector());
		parameters.put("P_MES", mes);
		parameters.put("P_IMG", getUserDetails().getPathImgCab1());

		parameters.put("P_CAMPO1", firmas.getCampo1());
		parameters.put("P_CAMPO3", firmas.getCampo3().toString());
		parameters.put("P_CUENTA_INICIO", cuentaInicial.toString());
		parameters.put("P_CUENTA_FIN", cuentaFinal.toString());
		parameters.put("P_L3", firmas.getL27());
		parameters.put("P_N3", firmas.getN27());
		parameters.put("P_L4", firmas.getL5());
		parameters.put("P_N4", firmas.getN5());
		parameters.put("P_L5", firmas.getL3());
		parameters.put("P_N5", firmas.getN3());
		return parameters;
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

}
