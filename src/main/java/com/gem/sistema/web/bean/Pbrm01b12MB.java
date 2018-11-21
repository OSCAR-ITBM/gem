package com.gem.sistema.web.bean;

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

// TODO: Auto-generated Javadoc
/**
 * The Class Pbrm01b12MB.
 */
@ManagedBean
@ViewScoped
public class Pbrm01b12MB extends BaseDirectReport {		 
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	
	//Se generan getters and setters
	
	
	
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct Pbrm01b12MB ");
		jasperReporteName = "pbrm_01b12";
		endFilename = jasperReporteName+".pdf";
	}

	
	public Firmas getFirmas() {
		return firmas;
	}


	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}


	public Conctb getConctb() {
		return conctb;
	}


	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}


	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}


	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}


	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}


	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}


	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		//int year = getCurrentYear();
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		paramsReport.put("p_Img1", getUserDetails().getPathImgCab1()); 
		paramsReport.put("p_Img2", getUserDetails().getPathImgCab2()); 
		paramsReport.put("p_IdSector", getUserDetails().getIdSector()); 
		paramsReport.put("p_NomMun", firmas.getCampo1()); 
		paramsReport.put("p_NumMun", conctb.getClave()); 
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
