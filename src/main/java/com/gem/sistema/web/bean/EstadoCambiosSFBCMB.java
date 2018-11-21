package com.gem.sistema.web.bean;

import java.util.HashMap;

import static com.roonin.utils.UtilDate.getLastDay;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.EstadoCambiosSFBCMBService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCambiosSFBCMB.
 */
/**
 * @author Alfredo
 *
 */
@ManagedBean(name = "estadoCambiosSFBCMB")
@ViewScoped
public class EstadoCambiosSFBCMB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The mes. */
	private Integer mes;

	/** The firmas. */
	private Firmas firmas;

	/** The conctb. */
	private Conctb conctb;

	/** The mes 2. */
	private String mes2;

	/** The firmas repository. */
	@ManagedProperty(value = "#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty(value = "#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The estado cambios SFBCMB service. */
	@ManagedProperty(value = "#{estadoCambiosSFBCMBService}")
	private EstadoCambiosSFBCMBService estadoCambiosSFBCMBService;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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
	 * Gets the estado cambios SFBCMB service.
	 *
	 * @return the estado cambios SFBCMB service
	 */
	public EstadoCambiosSFBCMBService getEstadoCambiosSFBCMBService() {
		return estadoCambiosSFBCMBService;
	}

	/**
	 * Sets the estado cambios SFBCMB service.
	 *
	 * @param estadoCambiosSFBCMBService
	 *            the new estado cambios SFBCMB service
	 */
	public void setEstadoCambiosSFBCMBService(EstadoCambiosSFBCMBService estadoCambiosSFBCMBService) {
		this.estadoCambiosSFBCMBService = estadoCambiosSFBCMBService;
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
	 * @param conctb
	 *            the new conctb
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
	 * @param conctbRepository
	 *            the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/**
	 * Gets the mes 2.
	 *
	 * @return the mes 2
	 */
	public String getMes2() {
		return mes2;
	}

	/**
	 * Sets the mes 2.
	 *
	 * @param mes2
	 *            the new mes 2
	 */
	public void setMes2(String mes2) {
		this.mes2 = mes2;
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
	 * @param tcMesRepository
	 *            the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.jasperReporteName = "EstadoCambiosSitFin";
		this.endFilename = this.jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		mes2 = StringUtils.leftPad(mes.toString(), 2, "0");
		TcMes descripMes = tcMesRepository.findByMes(mes2);

		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pNomMun", firmas.getCampo1());
		parameters.put("pLastDay", getLastDay(mes));
		parameters.put("pDescripMes", descripMes.getDescripcion());
		parameters.put("pAn", conctb.getAnoemp());
		parameters.put("pSsqlActivo",
				estadoCambiosSFBCMBService.generaQueryActivo(this.getUserDetails().getIdSector(), mes));
		parameters.put("pSsqlPasivo",
				estadoCambiosSFBCMBService.generaQueryPasivo(this.getUserDetails().getIdSector(), mes));
		parameters.put("pSsql3211",
				estadoCambiosSFBCMBService.generaQuery3211(this.getUserDetails().getIdSector(), mes));
		parameters.put("pN1", firmas.getN27());
		parameters.put("pL1", firmas.getL27());
		parameters.put("pN2", firmas.getN5());
		parameters.put("pL2", firmas.getL5());
		parameters.put("pN3", firmas.getN28());
		parameters.put("pL3", firmas.getL28());

		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.createFilePdfInline();
		}
	}
}
