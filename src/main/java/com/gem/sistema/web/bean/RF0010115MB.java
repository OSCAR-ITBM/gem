package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

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
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010115MB.
 */
@ManagedBean
@ViewScoped
public class RF0010115MB extends BaseDirectReport{
	
	/** The mes inicial. */
	private String mesInicial;
	
	/** The mes final. */
	private String mesFinal;
	
	/** The list mes ini. */
	private List<TcMes> listMesIni;
	
	/** The list mes fin. */
	private List<TcMes> listMesFin;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/**Repositorio para mensajes de validacion**/
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;


	/**
	 * Gets the list mes ini.
	 *
	 * @return the list mes ini
	 */
	public List<TcMes> getListMesIni() {
		return listMesIni;
	}
	

	/**
	 * Gets the mes inicial.
	 *
	 * @return the mes inicial
	 */
	public String getMesInicial() {
		return mesInicial;
	}


	/**
	 * Sets the mes inicial.
	 *
	 * @param mesInicial the new mes inicial
	 */
	public void setMesInicial(String mesInicial) {
		this.mesInicial = mesInicial;
	}


	/**
	 * Gets the mes final.
	 *
	 * @return the mes final
	 */
	public String getMesFinal() {
		return mesFinal;
	}


	/**
	 * Sets the mes final.
	 *
	 * @param mesFinal the new mes final
	 */
	public void setMesFinal(String mesFinal) {
		this.mesFinal = mesFinal;
	}


	/**
	 * Gets the list mes fin.
	 *
	 * @return the list mes fin
	 */
	public List<TcMes> getListMesFin() {
		return listMesFin;
	}


	/**
	 * Sets the list mes fin.
	 *
	 * @param listMesFin the new list mes fin
	 */
	public void setListMesFin(List<TcMes> listMesFin) {
		this.listMesFin = listMesFin;
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
	 * Sets the list mes ini.
	 *
	 * @param listMesIni the new list mes ini
	 */
	public void setListMesIni(List<TcMes> listMesIni) {
		this.listMesIni = listMesIni;
	}
	
	
	
	

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		jasperReporteName = "RF0010115";
		endFilename = jasperReporteName + ".pdf";

		listMesIni = tcMesRepository.findAll();
		listMesFin = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMesIni)) {
			mesInicial = listMesIni.get(0).getMes();
		}
		if (!CollectionUtils.isEmpty(listMesFin)) {
			mesFinal = listMesFin.get(0).getMes();
		}
	}
	
	
	/**Getters and setters de validacion de meses*/
	
	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}


	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/** The parametros. */
	Map<String, Object> parametros;
	
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parametros = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		firmas = firmasRepository.findAllByIdsector(sector);

		parametros.put("mesInicial", Integer.parseInt(mesInicial));
		parametros.put("mesFinal", Integer.parseInt(mesFinal));
		parametros.put("lastdayMesFinal", getLastDayByAnoEmp(Integer.parseInt(mesFinal),firmas.getCampo3()));
		parametros.put("year", firmas.getCampo3());
		parametros.put("idSector", sector);
		parametros.put("municipioName", firmas.getCampo1());
		parametros.put("imagePath", this.getUserDetails().getPathImgCab1());
		parametros.put("firmaP1", (sector == 1)? firmas.getL27() : firmas.getL4());
		parametros.put("firmaP2", (sector == 1)? firmas.getL28() : firmas.getL5());
		parametros.put("firmaP3", (sector == 1)? firmas.getL3() : firmas.getL27());
		parametros.put("firmaN1", (sector == 1)? firmas.getN27() : firmas.getN4());
		parametros.put("firmaN2", (sector == 1)? firmas.getN28() : firmas.getN5());
		parametros.put("firmaN3", (sector == 1)? firmas.getN3() : firmas.getN3());
		return parametros;
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
	 * On change valida meses.
	 */
	public void onChangeValidaMeses() {
		if (Integer.valueOf(mesInicial) > Integer.valueOf(mesFinal)) {
			generateNotificationFront(SEVERITY_INFO, "Info", "El Mes Inical debe ser menor o igual al Mes Final");
		}
	}
	
	
	
	/**Metodo para validacion del anio aperturado*/
	
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mesInicial), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			this.createFilePdfInline();
			;
		}

		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mesFinal), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			this.createFilePdfInline();
			;
		}
	}
}
