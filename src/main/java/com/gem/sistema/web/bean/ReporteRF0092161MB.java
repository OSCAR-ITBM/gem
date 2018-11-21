package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
// TODO: Auto-generated Javadoc

/**
 * The Class ReporteRF0092161MB.
 */
@ManagedBean(name = "reporteRF0092161MB")
@ViewScoped
public class ReporteRF0092161MB extends BaseDirectReport {

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_RF0092161";
	
	/** The txt. */
	private StreamedContent txt;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
    protected ExecutePlService executePlService;
	
	/**
	 * Gets the execute pl service.
	 *
	 * @return the execute pl service
	 */
	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	/**
	 * Sets the execute pl service.
	 *
	 * @param executePlService the new execute pl service
	 */
	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
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
	
	/** The out. */
	Map<String, Object> out;
	
	/** The stream. */
	InputStream stream = null;
	
	/**
	 * Gets the txt.
	 *
	 * @return the txt
	 */
	public StreamedContent getTxt() {
		
MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());
		
		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);
		
		if(Integer.valueOf(out.get("O_COD_ERROR").toString())>0){
            try {
                stream = new FileInputStream(
                  new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
               } catch (FileNotFoundException e) {
                
                e.printStackTrace();
               }
            txt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
               generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
        }else{
        	generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
        }
		
		return txt;
	}

	/**
	 * Sets the txt.
	 *
	 * @param txt the new txt
	 */
	public void setTxt(StreamedContent txt) {
		this.txt = txt;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "RF009_2_1_6_1";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		firmas = this.getFirmasRepository().findAllByIdsector(this.getUserDetails().getIdSector());
		
		parameters.put("anio", firmas.getCampo3());
		parameters.put("municipio", firmas.getCampo1());
		parameters.put("pathImg", this.getUserDetails().getPathImgCab1());
		parameters.put("idSector", this.getUserDetails().getIdSector());
		if(this.getUserDetails().getIdSector() == 1){
			parameters.put("firmaP1", firmas.getL27());
			parameters.put("firmaP2", firmas.getL5());
			parameters.put("firmaP3", firmas.getL3());
			parameters.put("firmaN1", firmas.getN27());
			parameters.put("firmaN2", firmas.getN5());
			parameters.put("firmaN3", firmas.getN3());
		}else{
			parameters.put("firmaP1", firmas.getL4());
			parameters.put("firmaP2", firmas.getL5());
			parameters.put("firmaP3", firmas.getL27());
			parameters.put("firmaN1", firmas.getN4());
			parameters.put("firmaN2", firmas.getN5());
			parameters.put("firmaN3", firmas.getN27());
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
