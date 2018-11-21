package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Mpbrm06MB.
 *
 * @author Alfredo
 */
@ManagedBean(name = "mpbrm06MB")
@ViewScoped
public class Mpbrm06MB extends BaseDirectReport {
	
	/** The firmas. */
	private Firmas firmas;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct PROGRAMA ANUAL DE ADQUISICIONES");

		jasperReporteName = "m_pbrm_06";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());

		paramsReport.put("imagePath1", this.getUserDetails().getPathImgCab1());
		paramsReport.put("imagePath2", this.getUserDetails().getPathImgCab2());
		paramsReport.put("year", firmas.getCampo3());
		paramsReport.put("entidadName", firmas.getCampo1());
		paramsReport.put("entidadClave",
				conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getClave());
		paramsReport.put("firmaP1", firmas.getL27());
		paramsReport.put("firmaN1", firmas.getN27());
		paramsReport.put("firmaP2", firmas.getL28());
		paramsReport.put("firmaN2", firmas.getN28());
		paramsReport.put("firmaP3", firmas.getL5());
		paramsReport.put("firmaN3", firmas.getN5());
		paramsReport.put("firmaP4", firmas.getL3());
		paramsReport.put("firmaN4", firmas.getN3());
		paramsReport.put("sector", this.getUserDetails().getIdSector());

		return paramsReport;
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

}
