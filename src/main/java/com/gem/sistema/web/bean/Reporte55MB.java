package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte55MB.
 */
@ManagedBean(name = "reporte55MB")
@ViewScoped
public class Reporte55MB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The mes. */
	private String mes;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The secretaria. */
	private String secretaria;

	/** The list clvdep. */
	private List<String> listClvdep;

	/** The firmas. */
	private Firmas firmas;

	/** The tcmes. */
	private TcMes tcmes;

	/** The xcatpro. */
	private Xcatpro xcatpro;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
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

	/**
	 * Sets the list mes.
	 *
	 * @param listMes
	 *            the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
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
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the secretaria.
	 *
	 * @return the secretaria
	 */
	public String getSecretaria() {
		return secretaria;
	}

	/**
	 * Sets the secretaria.
	 *
	 * @param secretaria
	 *            the new secretaria
	 */
	public void setSecretaria(String secretaria) {
		this.secretaria = secretaria;
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
	 * Gets the tcmes.
	 *
	 * @return the tcmes
	 */
	public TcMes getTcmes() {
		return tcmes;
	}

	/**
	 * Sets the tcmes.
	 *
	 * @param tcmes
	 *            the new tcmes
	 */
	public void setTcmes(TcMes tcmes) {
		this.tcmes = tcmes;
	}

	/**
	 * Gets the xcatpro.
	 *
	 * @return the xcatpro
	 */
	public Xcatpro getXcatpro() {
		return xcatpro;
	}

	/**
	 * Sets the xcatpro.
	 *
	 * @param xcatpro
	 *            the new xcatpro
	 */
	public void setXcatpro(Xcatpro xcatpro) {
		this.xcatpro = xcatpro;
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
	 * Gets the list clvdep.
	 *
	 * @return the list clvdep
	 */
	public List<String> getListClvdep() {
		return listClvdep;
	}

	/**
	 * Sets the list clvdep.
	 *
	 * @param listClvdep
	 *            the new list clvdep
	 */
	public void setListClvdep(List<String> listClvdep) {
		this.listClvdep = listClvdep;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository
	 *            the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "reporte55";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}

		listClvdep = catdepRepository.getClvdep3ByIdsector(this.getUserDetails().getIdSector());
		if (!CollectionUtils.isEmpty(listClvdep)) {
			secretaria = listClvdep.get(0);
		}
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

		parametrs.put("imagen", this.getUserDetails().getPathImgCab1());
		parametrs.put("firmaP1", firmas.getL4());
		parametrs.put("firmaP2", firmas.getL5());
		parametrs.put("firmaP3", firmas.getL27());
		parametrs.put("firmaN1", firmas.getN4());
		parametrs.put("firmaN2", firmas.getN5());
		parametrs.put("firmaN3", firmas.getN27());
		parametrs.put("mes", Integer.valueOf(mes));
		parametrs.put("sector", this.getUserDetails().getIdSector());
		parametrs.put("secretaria", secretaria);
		parametrs.put("anio", conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp());
		parametrs.put("No.Firmas", 3);
		parametrs.put("nombreEntidad", firmas.getCampo1());

		return parametrs;

	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			this.createFilePdfInline();
			;
		}
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
