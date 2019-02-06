package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte26MB.
 */
@ManagedBean(name = "reporte26MB")
@ViewScoped
public class Reporte26MB extends BaseDirectReport {

	/** The sem. */
	private Integer sem;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

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
	 * Gets the sem.
	 *
	 * @return the sem
	 */
	public Integer getSem() {
		return sem;
	}

	/**
	 * Sets the sem.
	 *
	 * @param sem the new sem
	 */
	public void setSem(Integer sem) {
		this.sem = sem;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte26";
		endFilename = jasperReporteName + ".pdf";

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new HashMap<String, Object>();
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());

		parametrs.put("pSector", this.getUserDetails().getIdSector());

		if (sem == Integer.valueOf(1)) {
			parametrs.put("pMesIni", 1);
			parametrs.put("pMesFin", 6);
		} else if (sem == Integer.valueOf(2)) {
			parametrs.put("pMesIni", 1);
			parametrs.put("pMesFin", 12);
		}

		parametrs.put("pNomMunicipio", firmas.getCampo1());
		parametrs.put("pNumMunicipio", conctb.getClave());
		parametrs.put("pL1", firmas.getL1());
		parametrs.put("pL2", firmas.getL2());
		parametrs.put("pL3", firmas.getL3());
		parametrs.put("pL4", firmas.getL16());
		parametrs.put("pL5", firmas.getL28());
		parametrs.put("pN1", firmas.getN1());
		parametrs.put("pN2", firmas.getN2());
		parametrs.put("pN3", firmas.getN3());
		parametrs.put("pN4", firmas.getN16());
		parametrs.put("pN5", firmas.getN28());
		parametrs.put("pImgMuni", this.getUserDetails().getPathImgCab1());

		return parametrs;
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
