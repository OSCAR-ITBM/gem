package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class PbrmO1dMB.
 */
@ManagedBean
@ViewScoped
public class PbrmO1dMB extends BaseDirectReport {
	
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct PbrmO1dMB ");
		//reportId = 2;
		//tcReporte = reportesRepository.findOne(reportId);
		jasperReporteName = "PbRM_01d";
		endFilename = jasperReporteName + ".pdf";
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		
		paramsReport.put("anio", firmas.getCampo3());
		paramsReport.put("imagen", this.getUserDetails().getPathImgCab1());
		paramsReport.put("firmaCargo1", firmas.getL4());
		paramsReport.put("firmaCargo2", firmas.getL25());
		paramsReport.put("firmaNombre1", firmas.getN4());
		paramsReport.put("firmaNombre2", firmas.getN25());
		
		return paramsReport;
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		paramsQuery.put("ID_REF", new Integer(0)); //FALTA
		return reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery, reporteName,type);
		*/
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}
	
	
}
