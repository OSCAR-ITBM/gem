package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.dto.ReportsParamDTO;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ReporteRF009114Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF009114MB.
 */
@ManagedBean(name = "reporteRF009114MB")
@ViewScoped
public class ReporteRF009114MB extends BaseDirectReport {

	/** The firmas. */
	private Firmas firmas;

	/** The parametros repository. */
	@ManagedProperty(value = "#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The mes repository. */
	@ManagedProperty(value = "#{tcMesRepository}")
	private TcMesRepository mesRepository;

	public TcMesRepository getMesRepository() {
		return mesRepository;
	}

	public void setMesRepository(TcMesRepository mesRepository) {
		this.mesRepository = mesRepository;
	}

	/** The mes. */
	private String mes;

	/** The content TXT. */
	private StreamedContent contentTXT;

	/** The content CSV. */
	private StreamedContent contentCSV;

	/** The reporte service. */
	@ManagedProperty(value = "#{reporteRF009114Service}")
	private ReporteRF009114Service reporteService;

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
	 * @param firmas
	 *            the new firmas
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
	 * @param firmasRepository
	 *            the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the reporte service.
	 *
	 * @return the reporte service
	 */
	public ReporteRF009114Service getReporteService() {
		return reporteService;
	}

	/**
	 * Gets the content CSV.
	 *
	 * @return the content CSV
	 */
	public StreamedContent getContentCSV() {
		stream = null;
		ReportsParamDTO parameters = new ReportsParamDTO();
		parameters.setMes(Integer.valueOf(mes));
		parameters.setIdSector(this.getUserDetails().getIdSector());
		ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters, 2);
		if (reportsDTO.getoCodError() > 0) {
			endFilename = reportsDTO.getoFullFile().toString();
			try {
				stream = new FileInputStream(new File(reportsDTO.getoFullFile().toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contentCSV = new DefaultStreamedContent(stream, "application/txt", endFilename.substring(13));
			endFilename = "";
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", reportsDTO.getoMsgError());
		}
		return contentCSV;
	}

	/**
	 * Sets the content CSV.
	 *
	 * @param contentCSV
	 *            the new content CSV
	 */
	public void setContentCSV(StreamedContent contentCSV) {
		this.contentCSV = contentCSV;
	}

	/**
	 * Sets the reporte service.
	 *
	 * @param reporteService
	 *            the new reporte service
	 */
	public void setReporteService(ReporteRF009114Service reporteService) {
		this.reporteService = reporteService;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the content TXT.
	 *
	 * @return the content TXT
	 */
	public StreamedContent getContentTXT() {
		stream = null;
		ReportsParamDTO parameters = new ReportsParamDTO();
		parameters.setMes(Integer.valueOf(mes));
		parameters.setIdSector(this.getUserDetails().getIdSector());
		ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters, 1);
		if (reportsDTO.getoCodError() > 0) {
			endFilename = reportsDTO.getoFullFile().toString();
			try {
				stream = new FileInputStream(new File(reportsDTO.getoFullFile().toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contentTXT = new DefaultStreamedContent(stream, "application/txt", endFilename.substring(13));
			endFilename = "";
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", reportsDTO.getoMsgError());
		}
		return contentTXT;
	}

	/**
	 * Sets the content TXT.
	 *
	 * @param contentTXT
	 *            the new content TXT
	 */
	public void setContentTXT(StreamedContent contentTXT) {
		this.contentTXT = contentTXT;
	}

	/** The stream. */
	InputStream stream = null;

	/**
	 * Gets the file txt procedure.
	 *
	 * @return the file txt procedure
	 */
	public void getFileTxtProcedure() {
		stream = null;
		ReportsParamDTO parameters = new ReportsParamDTO();
		parameters.setMes(Integer.valueOf(mes));
		parameters.setIdSector(this.getUserDetails().getIdSector());
		ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters, 1);
		if (reportsDTO.getoCodError() > 0) {
			endFilename = reportsDTO.getoFullFile().toString();
			try {
				stream = new FileInputStream(new File(reportsDTO.getoFullFile().toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contentTXT = new DefaultStreamedContent(stream, "application/txt", endFilename.substring(13));
			endFilename = "";
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", reportsDTO.getoMsgError());
		}
	}

	/**
	 * Gets the file xls using link.
	 *
	 * @return the file xls using link
	 */
	public StreamedContent getFileXlsProcedure() {
		StreamedContent streamedContent = null;
		try {
			ReportsParamDTO parameters = new ReportsParamDTO();
			parameters.setMes(Integer.valueOf(mes));
			parameters.setIdSector(this.getUserDetails().getIdSector());
			ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters);
			if (reportsDTO.getoCodError() > 0) {
				streamedContent = this.getFileXls();
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", reportsDTO.getoMsgError());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					"Hubo un error al generar el reporte, contacte a su administrador.");
		}
		return streamedContent;
	}

	/**
	 * Gets the file pdf in link for project in display.
	 */
	public void getFilePdfInlineProcedure() {
		stream = null;
		try {
			jasperReporteName = "reporte_RF009114_pdf";
			endFilename = jasperReporteName + ".pdf";

			ReportsParamDTO parameters = new ReportsParamDTO();
			parameters.setMes(Integer.valueOf(mes));
			parameters.setIdSector(this.getUserDetails().getIdSector());
			ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters);
			if (reportsDTO.getoCodError() > 0) {
				this.createFilePdfInline();
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", reportsDTO.getoMsgError());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					"Hubo un error al generar el reporte, contacte a su administrador.");
		}
	}

	/**
	 * Generate file csv.
	 *
	 * @return the streamed content
	 */
	public void getFileCsvProcedure() {
		stream = null;
		ReportsParamDTO parameters = new ReportsParamDTO();
		parameters.setMes(Integer.valueOf(mes));
		parameters.setIdSector(this.getUserDetails().getIdSector());
		ReportsParamDTO reportsDTO = this.reporteService.executeProcedure(parameters, 2);
		if (reportsDTO.getoCodError() > 0) {
			endFilename = reportsDTO.getoFullFile().toString();
			try {
				stream = new FileInputStream(new File(reportsDTO.getoFullFile().toString()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			contentCSV = new DefaultStreamedContent(stream, "application/txt", endFilename.substring(13));
			endFilename = "";
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", reportsDTO.getoMsgError());
		}
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "reporte_RF009114_pdf";
		endFilename = jasperReporteName + ".pdf";

	}

	/** The parameters. */
	Map<String, Object> parameters = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());

		TcMes mesDes = this.mesRepository.findByMes(StringUtils.leftPad(mes, 2, "0"));
		parameters.put("Mes", this.lastDay(firmas.getCampo3(), Integer.valueOf(mes)));
		parameters.put("descMes", mesDes.getDescripcion());
		parameters.put("Anio", firmas.getCampo3());
		parameters.put("pathImage", this.getUserDetails().getPathImgCab1());
		parameters.put("idSector", this.getUserDetails().getIdSector());
		parameters.put("nomMunicipio", firmas.getCampo1());
		parameters.put("firmaP1", firmas.getL4());
		parameters.put("firmaP2", firmas.getL5());
		parameters.put("firmaP3", firmas.getL27());
		parameters.put("firmaN1", firmas.getN4());
		parameters.put("firmaN2", firmas.getN5());
		parameters.put("firmaN3", firmas.getN27());
		parameters.put("entidadName", firmas.getCampo1());
		parameters.put("entidadRfc", firmas.getCampo2());
		return parameters;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.dao.ReporteRF009114DAO#lastDay(java.lang.
	 * Integer, java.lang.Integer)
	 */
	public Integer lastDay(Integer anio, Integer mes) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(anio, mes - 1, 1);
		return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
