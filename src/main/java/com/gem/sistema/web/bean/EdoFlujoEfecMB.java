package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

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
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoFlujoEfecMB.
 */
@ManagedBean(name = "edoFlujoEfecMB")
@ViewScoped
public class EdoFlujoEfecMB extends BaseDirectReport {

	/** The mes. */
	private String mes;
	
	/** The listmes. */
	private List<TcMes> listmes;
	
	/** The conctb. */
	private Conctb conctb;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

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
	 * Gets the listmes.
	 *
	 * @return the listmes
	 */
	public List<TcMes> getListmes() {
		return listmes;
	}

	/**
	 * Sets the listmes.
	 *
	 * @param listmes the new listmes
	 */
	public void setListmes(List<TcMes> listmes) {
		this.listmes = listmes;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		listmes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listmes)) {
			mes = listmes.get(0).getMes();
		}

		jasperReporteName = "EdoFlujoEfec";
		endFilename = jasperReporteName + ".pdf";

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		TcMes descriMes = tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0"));

		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pLastDay", getLastDayByAnoEmp(Integer.valueOf(mes),firmas.getCampo3()));
		parameters.put("pDescripM", descriMes.getDescripcion());
		parameters.put("pAn", firmas.getCampo3().toString());
		parameters.put("pIdsector", this.getUserDetails().getIdSector());
		parameters.put("pMes", Integer.valueOf(mes));
		if (this.getUserDetails().getIdSector() == 2){
			parameters.put("pL27", firmas.getL4());
			parameters.put("pN27", firmas.getN4());
			parameters.put("pL4", firmas.getL5());
			parameters.put("pN4", firmas.getN5());
			parameters.put("pL5", firmas.getL27());
			parameters.put("pN5", firmas.getN27());
		}else{
			parameters.put("pL27", firmas.getL27());
			parameters.put("pN27", firmas.getN27());
			parameters.put("pL4", firmas.getL5());
			parameters.put("pN4", firmas.getN5());
			parameters.put("pL5", firmas.getL3());
			parameters.put("pN5", firmas.getN3());
		}
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

}
