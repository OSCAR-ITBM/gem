package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.callsp.ParamsRF009118DTO;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009118MB.
 */
@ManagedBean
@ViewScoped
public class RF009118MB extends BaseDirectReport {
	
	/** The mes. */
	private Integer mes;

	/** The firmas. */
	private Integer firmas;
	
	/** The tcfirmas. */
	private Firmas tcfirmas;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The report by PL. */
	@ManagedProperty(value = "#{baseDirectReportByPL}")
	private BaseDirectReportByPL reportByPL;

	/**
	 * Gets the report by PL.
	 *
	 * @return the report by PL
	 */
	public BaseDirectReportByPL getReportByPL() {
		return reportByPL;
	}

	/**
	 * Sets the report by PL.
	 *
	 * @param reportByPL the new report by PL
	 */
	public void setReportByPL(BaseDirectReportByPL reportByPL) {
		this.reportByPL = reportByPL;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009118MB ");
		jasperReporteName = "RF009_1_18";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		GemUser user = this.getUserDetails();
		tcfirmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		
		paramsReport.put("USUARIO", user.getUsername());
		paramsReport.put("mes", mes);
		paramsReport.put("nFirmas", firmas);
		paramsReport.put("imagePath", this.getUserDetails().getPathImgCab1());
		if(this.getUserDetails().getIdSector() == 2){
			paramsReport.put("firmaP1", tcfirmas.getL4());
			paramsReport.put("firmaP2", tcfirmas.getL5());
			paramsReport.put("firmaP3", tcfirmas.getL27());
			paramsReport.put("firmaP4", ""); // falta indicar que firma ira aqui
			paramsReport.put("firmaN1", tcfirmas.getN4());
			paramsReport.put("firmaN2", tcfirmas.getN5());
			paramsReport.put("firmaN3", tcfirmas.getN27());
			paramsReport.put("firmaN4", ""); // falta indicar que firma ira aqui
		}else{
			paramsReport.put("firmaP1", tcfirmas.getL1());
			paramsReport.put("firmaP2", tcfirmas.getL3());
			paramsReport.put("firmaP3", tcfirmas.getL4());
			paramsReport.put("firmaP4", ""); // falta indicar que firma ira aqui
			paramsReport.put("firmaN1", tcfirmas.getN1());
			paramsReport.put("firmaN2", tcfirmas.getN3());
			paramsReport.put("firmaN3", tcfirmas.getN4());
			paramsReport.put("firmaN4", ""); // falta indicar que firma ira aqui
		}
		paramsReport.put("entidadName", tcfirmas.getCampo1());
		paramsReport.put("entidadRfc", tcfirmas.getCampo2());
		paramsReport.put("year", tcfirmas.getCampo3());
		
		return paramsReport;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Gets the file txt using link.
	 *
	 * @return the file txt using link
	 */
	public StreamedContent getFileTxtUsingLink() {
		StreamedContent streamedContent = null;
		try {
			GemUser user = getUserDetails();

			ParamsRF009118DTO params = new ParamsRF009118DTO();
			params.setUser(user.getUsername());
			params.setFirmas(firmas);
			params.setMes(mes);

			reportByPL.callSpService.getFile("SP_GENERA_TXT_RF_009_1_18", params);

			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				streamedContent = reportByPL.sendFileByStream(params.getResultFile());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
		}
		return streamedContent;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Integer getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Integer firmas) {
		this.firmas = firmas;
	}

	/**
	 * Gets the tcfirmas.
	 *
	 * @return the tcfirmas
	 */
	public Firmas getTcfirmas() {
		return tcfirmas;
	}

	/**
	 * Sets the tcfirmas.
	 *
	 * @param tcfirmas the new tcfirmas
	 */
	public void setTcfirmas(Firmas tcfirmas) {
		this.tcfirmas = tcfirmas;
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
