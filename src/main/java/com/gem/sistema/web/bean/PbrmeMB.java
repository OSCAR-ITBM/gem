package com.gem.sistema.web.bean;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.StreamedContent;

// TODO: Auto-generated Javadoc
/**
 * The Class PbrmeMB.
 */
@ManagedBean
@ViewScoped
public class PbrmeMB extends BaseDirectReport {		 
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct PbRMe ");
		jasperReporteName = "PbRM01e";
		endFilename = jasperReporteName+".pdf";
	}

	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		//int year = getCurrentYear();
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		paramsReport.put("p_Idsector", getUserDetails().getIdSector()); 
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


}
