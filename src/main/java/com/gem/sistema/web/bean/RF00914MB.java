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
 * The Class RF00914MB.
 */
@ManagedBean
@ViewScoped
public class RF00914MB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The mes. */
	private Integer mes;

	/** The firmas. */
	private Firmas firmas;

	/** The saldo 0. */
	private Integer saldo0;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF00914MB ");
		jasperReporteName = "RF009.1.4";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		firmas = firmasRepository.findAllByIdsector(sector);

		paramsReport.put("year", firmas.getCampo3());
		paramsReport.put("imagePath", this.getUserDetails().getPathImgCab1());
		paramsReport.put("entidadName", firmas.getCampo1());
		paramsReport.put("mes", mes);
		paramsReport.put("idSector", sector);
		paramsReport.put("where", this.generateWhere(sector, mes, saldo0));
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
	 * Generate where.
	 *
	 * @param idSector
	 *            the id sector
	 * @param mes
	 *            the mes
	 * @param saldos
	 *            the saldos
	 * @return the string
	 */
	public String generateWhere(Integer idSector, Integer mes, Integer saldos) {
		String str = "";
		if (saldos == 0) {
			for (int i = 1; i <= mes; i++) {
				str = str + "ABONOS_" + i + " <> 0 OR CARGOS_" + i + " <> 0 OR ";
			}
			str = " AND (" + str + " SALINI <> 0)";
		}

		str = "IDSECTOR = " + idSector + str;

		return str;
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
	 * Gets the saldo 0.
	 *
	 * @return the saldo 0
	 */
	public Integer getSaldo0() {
		return saldo0;
	}

	/**
	 * Sets the saldo 0.
	 *
	 * @param saldo0
	 *            the new saldo 0
	 */
	public void setSaldo0(Integer saldo0) {
		this.saldo0 = saldo0;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
