package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDay;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.util.Calendar;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.callsp.ParamsRF9111DTO;
import com.gem.sistema.web.security.model.GemUser;
import com.roonin.utils.UtilDate;

// TODO: Auto-generated Javadoc
/**
 * The Class RF9111MB.
 */
@ManagedBean
@ViewScoped
public class RF9111MB extends BaseDirectReport {
	
	/** The mes. */
	private Integer mes;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The descrip mes. */
	private TcMes descripMes;
	
	/** The firmas repository. */
	@ManagedProperty(value = "#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/** The report by PL. */
	@ManagedProperty(value = "#{baseDirectReportByPL}")
	private BaseDirectReportByPL reportByPL;
	
	/** The tc mes repository. */
	@ManagedProperty(value = "#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The Constant NAME_PROCEDURE. */
	private static final String NAME_PROCEDURE = "SP_GENERA_XLS_FLUJO_EFECTIVO";

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
	 * Gets the descrip mes.
	 *
	 * @return the descrip mes
	 */
	public TcMes getDescripMes() {
		return descripMes;
	}

	/**
	 * Sets the descrip mes.
	 *
	 * @param descripMes the new descrip mes
	 */
	public void setDescripMes(TcMes descripMes) {
		this.descripMes = descripMes;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF9111MB ");
		jasperReporteName = "rf9111";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		descripMes = tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0"));
		paramsReport.put("CAMPO1", firmas.getCampo1());
		paramsReport.put("CAMPO2", firmas.getCampo2());
		paramsReport.put("DIA", UtilDate.getLastDayByAnoEmp(mes,firmas.getCampo3()));
		paramsReport.put("MES", descripMes.getDescripcion());
		paramsReport.put("ANIO", firmas.getCampo3());
		paramsReport.put("SECTOR", this.getUserDetails().getIdSector());
		paramsReport.put("pImg", this.getUserDetails().getPathImgCab1());
		paramsReport.put("pN1", firmas.getN4());
		paramsReport.put("pN2", firmas.getN5());
		paramsReport.put("pN3", firmas.getN27());
		paramsReport.put("pL1", firmas.getL4());
		paramsReport.put("pL2", firmas.getL5());
		paramsReport.put("pL3", firmas.getL27());

		return paramsReport;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Gets the file xls using link.
	 *
	 * @return the file xls using link
	 */
	public StreamedContent getFileXlsUsingLink() {
		StreamedContent streamedContent = null;
		try {
			GemUser user = getUserDetails();
			ParamsRF9111DTO params = new ParamsRF9111DTO();
			params.setIdSector(user.getIdSector());
			params.setMes(mes);
			reportByPL.callSpService.getFile(NAME_PROCEDURE, params);
			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				streamedContent = this.getFileXls();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
		}
		return streamedContent;
	}

	/**
	 * File pdf using link.
	 */
	public void filePdfUsingLink() {
		try {
			GemUser user = getUserDetails();
			ParamsRF9111DTO params = new ParamsRF9111DTO();
			params.setIdSector(user.getIdSector());
			params.setMes(mes);
			reportByPL.callSpService.getFile(NAME_PROCEDURE, params);
			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				this.createFilePdfInline();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, e.getMessage());
		}

	}
	
	
	public StreamedContent getFileTxtUsingLink() {
		StreamedContent streamedContent = null;
		try {
			GemUser user = getUserDetails();
			ParamsRF9111DTO params = new ParamsRF9111DTO();
			params.setIdSector(user.getIdSector());
			params.setMes(mes);
			reportByPL.callSpService.getFile(NAME_PROCEDURE, params);
			if (params.getCodError() == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, params.getMsgError());
			} else {
				streamedContent = this.getFileTxt();
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

}
