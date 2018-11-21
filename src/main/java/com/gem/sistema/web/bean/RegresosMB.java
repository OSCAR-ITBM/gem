package com.gem.sistema.web.bean;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.repository.catalogs.ConctbRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RegresosMB.
 */
@ManagedBean
@ViewScoped
public class RegresosMB extends BaseDirectReport {

	/** The definitivo. */
	private String definitivo;
	
	/** The contb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository contbRepository;

	/**
	 * Gets the definitivo.
	 *
	 * @return the definitivo
	 */
	public String getDefinitivo() {
		return definitivo;
	}

	/**
	 * Sets the definitivo.
	 *
	 * @param definitivo the new definitivo
	 */
	public void setDefinitivo(String definitivo) {
		this.definitivo = definitivo;
	}

	/**
	 * Gets the contb repository.
	 *
	 * @return the contb repository
	 */
	public ConctbRepository getContbRepository() {
		return contbRepository;
	}

	/**
	 * Sets the contb repository.
	 *
	 * @param contbRepository the new contb repository
	 */
	public void setContbRepository(ConctbRepository contbRepository) {
		this.contbRepository = contbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RegresosMB ");
		jasperReporteName = "R_egresos";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		if ("Proyecto".equals(definitivo)) {
			paramsReport.put("PROYECTO", "X");
		} else {
			paramsReport.put("DEFINITIVO", "X");
		}
		paramsReport.put("IMG_MUN", getUserDetails().getPathImgCab1());
		paramsReport.put("IMG_ENT", getUserDetails().getPathImgCab2());
		paramsReport.put("IDSECTOR", getUserDetails().getIdSector());
		paramsReport.put("NUMMUN", contbRepository.findByIdsector(this.getUserDetails().getIdSector()).getClave());
		return paramsReport;
	}

	/* (non-Javadoc)
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

}
