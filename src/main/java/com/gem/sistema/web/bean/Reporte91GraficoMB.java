package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte91GraficoMB.
 */
@ManagedBean(name = "reporte91GraficoMB")
@ViewScoped
public class Reporte91GraficoMB extends BaseDirectReport {

	/** The mes ini. */
	private String mesIni;

	/** The mes fin. */
	private String mesFin;

	/** The cuenta. */
	private String cuenta;

	/** The partida inicial. */
	private String partidaInicial;

	/** The partida final. */
	private String partidaFinal;

	/** The num firmas. */
	private Integer numFirmas;

	/** The firmas. */
	private Firmas firmas;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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
		jasperReporteName=(this.getUserDetails().getIdSector()==1)?"cuentaPartidaMun":"cuentaxpartida";
		//jasperReporteName = "cuentaxpartida";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mesIni = listMes.get(0).getMes();
			mesFin = listMes.get(0).getMes();
		}
		cuenta = "1111";
		partidaInicial = "1111";
		partidaFinal = "9999";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());

		parametrs.put("year", firmas.getCampo3());
		parametrs.put("lastDayMonthF", getLastDayByAnoEmp(Integer.valueOf(mesFin), firmas.getCampo3()));
		parametrs.put("nameMonthI", tcMesRepository.findByMes(mesIni).getDescripcion());
		parametrs.put("nameMonthF", tcMesRepository.findByMes(mesFin).getDescripcion());
		parametrs.put("entidadName", firmas.getCampo1());
		parametrs.put("imagePath", this.getUserDetails().getPathImgCab1());
		parametrs.put("cuenta", cuenta);
		parametrs.put("mesI", Integer.valueOf(mesIni));
		parametrs.put("mesF", Integer.valueOf(mesFin));
		parametrs.put("partidaI", partidaInicial);
		parametrs.put("partidaF", partidaFinal);
		parametrs.put("idSector", this.getUserDetails().getIdSector());
		if (this.getUserDetails().getIdSector() == 1) {
			parametrs.put("firmaL1", firmas.getL27());
			parametrs.put("firmaN1", firmas.getN27());
			parametrs.put("firmaL2", firmas.getL28());
			parametrs.put("firmaN2", firmas.getN28());
			parametrs.put("firmaL3", firmas.getL5());
			parametrs.put("firmaN3", firmas.getN5());
			parametrs.put("firmaL4", firmas.getL3());
			parametrs.put("firmaN4", firmas.getN3());
		} else {
			parametrs.put("firmaL1", firmas.getL4());
			parametrs.put("firmaN1", firmas.getN4());
			parametrs.put("firmaL2", firmas.getL5());
			parametrs.put("firmaN2", firmas.getN5());
			parametrs.put("firmaL3", firmas.getL27());
			parametrs.put("firmaN3", firmas.getN27());
		}
		parametrs.put("nFirmas", numFirmas);

		return parametrs;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#createFilePdfInline()
	 */
	@Override
	public void createFilePdfInline() {
		if (isValid()) {
			super.createFilePdfInline();
		}
	}

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		if (Integer.valueOf(mesIni) > Integer.valueOf(mesFin)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"El mes inicial no puede ser mayor al mes final.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		} else if (Integer.valueOf(partidaInicial) > Integer.valueOf(partidaFinal)) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"La partida inicial no puede ser mayor a la partida final.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Gets the mes ini.
	 *
	 * @return the mes ini
	 */
	public String getMesIni() {
		return mesIni;
	}

	/**
	 * Sets the mes ini.
	 *
	 * @param mesIni
	 *            the new mes ini
	 */
	public void setMesIni(String mesIni) {
		this.mesIni = mesIni;
	}

	/**
	 * Gets the mes fin.
	 *
	 * @return the mes fin
	 */
	public String getMesFin() {
		return mesFin;
	}

	/**
	 * Sets the mes fin.
	 *
	 * @param mesFin
	 *            the new mes fin
	 */
	public void setMesFin(String mesFin) {
		this.mesFin = mesFin;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the partida inicial.
	 *
	 * @return the partida inicial
	 */
	public String getPartidaInicial() {
		return partidaInicial;
	}

	/**
	 * Sets the partida inicial.
	 *
	 * @param partidaInicial
	 *            the new partida inicial
	 */
	public void setPartidaInicial(String partidaInicial) {
		this.partidaInicial = partidaInicial;
	}

	/**
	 * Gets the partida final.
	 *
	 * @return the partida final
	 */
	public String getPartidaFinal() {
		return partidaFinal;
	}

	/**
	 * Sets the partida final.
	 *
	 * @param partidaFinal
	 *            the new partida final
	 */
	public void setPartidaFinal(String partidaFinal) {
		this.partidaFinal = partidaFinal;
	}

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
	 * @param listMes
	 *            the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	public void validateMonthInit() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mesIni), null, this.getUserDetails().getIdSector());
	}

	public void validateMonthEnd() {
		this.validatePolicyService.isOpenMonth(Integer.valueOf(mesFin), null, this.getUserDetails().getIdSector());
	}

	public void viewPdf() throws ReportValidationException {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mesIni), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mesFin), null,
					this.getUserDetails().getIdSector()) == Boolean.TRUE) {
				this.createFilePdfInline();
			}
		}
	}

}
