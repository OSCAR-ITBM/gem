package com.gem.sistema.web.bean;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009120MB.
 */
@ManagedBean
@ViewScoped
public class RF009120MB extends BaseDirectReport {
	
	/** The mes inicial. */
	private Integer mesInicial;
	
	/** The mes final. */
	private Integer mesFinal;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/**
	 * Gets the mes inicial.
	 *
	 * @return the mes inicial
	 */
	public Integer getMesInicial() {
		return mesInicial;
	}

	/**
	 * Sets the mes inicial.
	 *
	 * @param mesInicial the new mes inicial
	 */
	public void setMesInicial(Integer mesInicial) {
		this.mesInicial = mesInicial;
	}

	/**
	 * Gets the mes final.
	 *
	 * @return the mes final
	 */
	public Integer getMesFinal() {
		return mesFinal;
	}

	/**
	 * Sets the mes final.
	 *
	 * @param mesFinal the new mes final
	 */
	public void setMesFinal(Integer mesFinal) {
		this.mesFinal = mesFinal;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009120MB ");
		jasperReporteName = "GlobalRangoMeses";
		endFilename = jasperReporteName+".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		 
		paramsReport.put("mesInicial", mesInicial); 
		paramsReport.put("mesFinal", mesFinal); 
		paramsReport.put("year", firmas.getCampo3());
		paramsReport.put("pathImage", this.getUserDetails().getPathImgCab1());
		if(this.getUserDetails().getIdSector()== 2){
			paramsReport.put("firmaP1", firmas.getL4());
			paramsReport.put("firmaP2", firmas.getL5());
			paramsReport.put("firmaP3", firmas.getL27());
			paramsReport.put("firmaN1", firmas.getN4());
			paramsReport.put("firmaN2", firmas.getN5());
			paramsReport.put("firmaN3", firmas.getN27());
		}else{
			paramsReport.put("firmaP1", firmas.getL1());
			paramsReport.put("firmaP2", firmas.getL2());
			paramsReport.put("firmaP3", firmas.getL3());
			paramsReport.put("firmaN1", firmas.getN1());
			paramsReport.put("firmaN2", firmas.getN2());
			paramsReport.put("firmaN3", firmas.getN3());
		}
		paramsReport.put("entidadName", firmas.getCampo1());
		paramsReport.put("entidadRfc", firmas.getCampo2());
		paramsReport.put("idSector", this.getUserDetails().getIdSector());
		
		return paramsReport;
	}	
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#createFilePdfInline()
	 */
	@Override
	public void createFilePdfInline() {
		if(isValid()){			
			super.createFilePdfInline();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getFileXls()
	 */
	@Override
	public StreamedContent getFileXls() {
		if(isValid()){			
			return super.getFileXls();
		}else{
			return null;
		}
	}	
	
	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		if(mesInicial > mesFinal){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, "El mes inicial no puede ser mayor al mes final.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return false;
		}else{
			return true;
		}
		
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
}
