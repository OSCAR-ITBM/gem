package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.Pbrm08DetailService;

// TODO: Auto-generated Javadoc
/**
 * The Class MPbrm08bMB.
 */
@ManagedBean
@ViewScoped
public class MPbrm08bMB extends BaseDirectReport {
	
	/** The p trim. */
	private String pTrim;
	
	/** The firmas. */
	private Firmas firmas;

	/** The pbrm 08 detail service. */
	@ManagedProperty("#{pbrm08DetailService}")
	private Pbrm08DetailService pbrm08DetailService;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/**
	 * Gets the pbrm 08 detail service.
	 *
	 * @return the pbrm 08 detail service
	 */
	public Pbrm08DetailService getPbrm08DetailService() {
		return pbrm08DetailService;
	}

	/**
	 * Sets the pbrm 08 detail service.
	 *
	 * @param pbrm08DetailService the new pbrm 08 detail service
	 */
	public void setPbrm08DetailService(Pbrm08DetailService pbrm08DetailService) {
		this.pbrm08DetailService = pbrm08DetailService;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct MPbrm08bMB ");
		// reportId = 2;
		// tcReporte = reportesRepository.findOne(reportId);
		jasperReporteName = "Pbrm08b";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		paramsReport.put("pSsql1",
				pbrm08DetailService.executeQueryHead(this.getUserDetails().getIdSector(), Integer.valueOf(pTrim)));
		paramsReport.put("pSsql2",
				pbrm08DetailService.executeQueryDetail(this.getUserDetails().getIdSector(), Integer.valueOf(pTrim)));
		paramsReport.put("pImg1", this.getUserDetails().getPathImgCab1());
		paramsReport.put("pImg2", this.getUserDetails().getPathImgCab2());
		paramsReport.put("pAn", firmas.getCampo3());
		paramsReport.put("pIdsector", this.getUserDetails().getIdSector());
		paramsReport.put("pTrim", Integer.valueOf(pTrim));
		paramsReport.put("pL4", firmas.getL4());
		paramsReport.put("pN4", firmas.getN4());
		paramsReport.put("pL5", firmas.getL5());
		paramsReport.put("pN5", firmas.getN5());
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

	/**
	 * Gets the p trim.
	 *
	 * @return the p trim
	 */
	public String getpTrim() {
		return pTrim;
	}

	/**
	 * Sets the p trim.
	 *
	 * @param pTrim the new p trim
	 */
	public void setpTrim(String pTrim) {
		this.pTrim = pTrim;
	}
}
