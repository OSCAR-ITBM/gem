/**
 * 
 */
package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.gem.sistema.business.domain.TcImagenesEntAdmin;
import com.gem.sistema.business.domain.TcImagenesMuni;
import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.business.repository.catalogs.ReportesRepository;
import com.gem.sistema.business.repository.catalogs.TcImagenesEntAdminRepository;
import com.gem.sistema.business.repository.catalogs.TcImagenesMuniRepository;
import com.gem.sistema.business.service.reportador.JasperGenericService;
import com.gem.sistema.business.service.reportador.JasperGenericServiceImpl;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.service.reportador.ReporteadorDirecto;
import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.web.util.FrontProperty;
import com.gem.sistema.web.util.GetFileInlineServlet;


// TODO: Auto-generated Javadoc
/**
 * The Class BaseDirectReport.
 */
public abstract class BaseDirectReport {
	
	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDirectReport.class);
	
	/** The Constant MESSAGE_ERROR. */
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");


	/** The report id. */
	protected long reportId = 0;

	/** The tc reporte. */
	protected TcReporte tcReporte;	
	
	/** The jasper reporte name. */
	protected String jasperReporteName;
	
	/** The uuid. */
	protected String uuid = null;
	
	/** The end filename. */
	protected String endFilename = null;	
		
	/** The tc imagenes muni repository. */
	@ManagedProperty(value = "#{tcImagenesMuniRepository}")
	protected TcImagenesMuniRepository tcImagenesMuniRepository;
	
	/** The tc imagenes ent admin repository. */
	@ManagedProperty(value = "#{tcImagenesEntAdminRepository}")
	protected TcImagenesEntAdminRepository tcImagenesEntAdminRepository;
	
	/** The reportes repository. */
	@ManagedProperty(value = "#{reportesRepository}")
	protected ReportesRepository reportesRepository;
	
	/** The jasper generic service. */
	@ManagedProperty(value = "#{jasperGenericServiceImpl}")
	protected JasperGenericService jasperGenericService;
	
	/** The reporteador directo impl. */
	@ManagedProperty(value = "#{reporteadorDirectoImpl}")
	protected ReporteadorDirecto reporteadorDirectoImpl;
	

	/**
	 * Instantiates a new base direct report.
	 */
	public BaseDirectReport() {
		super();
		//set specific charWidth and charHeight
		JasperGenericServiceImpl serviceImpl = (JasperGenericServiceImpl) jasperGenericService; 
		serviceImpl.CHAR_WIDTH = 5.45f;
		serviceImpl.CHAR_HEIGHT = 11.85f;
	}

	/**
	 * getUserDetails Metodo para obtener el usuario logueado.
	 *
	 * @return the user details
	 */
	protected GemUser getUserDetails() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            return (GemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }
	
	/**
	 * Gets the image mun.
	 *
	 * @return the image mun
	 */
	protected String getImageMun() {
		String img = "";		
		TcImagenesMuni tcImagenesMuni = tcImagenesMuniRepository.findByClvmun(this.getUserDetails().getIdMunicipio().intValue());
		if(tcImagenesMuni != null) 
			img =  tcImagenesMuni.getPathFile() + tcImagenesMuni.getNombreFile();
		return img;
	}
	
	/**
	 * Gets the image ent admin.
	 *
	 * @return the image ent admin
	 */
	protected String getImageEntAdmin() {
		String img = "";		
		 TcImagenesEntAdmin tcImagenesEntAdmin = tcImagenesEntAdminRepository
		 .findByIdEntidadAdmin(
				 this.getUserDetails().getIdManagementAdmin());
		
		if(tcImagenesEntAdmin != null) 
			img =  tcImagenesEntAdmin.getPathFile() + tcImagenesEntAdmin.getNombreFile();
		return img;
	}
	
	
	/**
	 * Gets the parameters reports.
	 *
	 * @return the parameters reports
	 * @throws ReportValidationException the report validation exception
	 */
	abstract public Map<String, Object> getParametersReports() throws ReportValidationException;	
	
	/**
	 * Genera reporte simple.
	 *
	 * @param type the type
	 * @return the streamed content
	 * @throws ReportValidationException the report validation exception
	 */
	abstract public StreamedContent generaReporteSimple(int type) throws ReportValidationException;
	

	/**
	 * Gets the current year.
	 *
	 * @return the current year
	 */
	protected int getCurrentYear(){
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
		//return String.valueOf(year);
	}
			
		
	
	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		StreamedContent streamedContent = null;
		try {			
			streamedContent = jasperGenericService.getReport(getParametersReports(), jasperReporteName, JasperGenericService.PDF);
		} catch (final ReportValidationException params) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, params.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (final Exception genericExc) {
			LOGGER.error("getFilePdf", genericExc);
		}
		return streamedContent;
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		StreamedContent streamedContent = null;
		LOGGER.info(":: Generar reporte de Excel, {}", jasperReporteName);
		// media = null;
		try {
			streamedContent =  jasperGenericService.getReport(getParametersReports(), jasperReporteName, JasperGenericService.XLS);
		} catch (final ReportValidationException params) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, params.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (final Exception genericExc) {
			LOGGER.error("getFileXls: Report = {} ", jasperReporteName, genericExc);	
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, "Hubo un error al generar el reporte, contacte a su administrador.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return streamedContent;
	}

	/**
	 * Gets the file txt.
	 *
	 * @return the file txt
	 */
	public StreamedContent getFileTxt() {
		StreamedContent streamedContent = null;
		LOGGER.info(":: Generando reporte de texto plano getFileTxt");
		// media = null;
		try {
			//streamedContent =  generaReporteSimple(ReporteadorDirecto.TXT);
			streamedContent = jasperGenericService.getReport(getParametersReports(), jasperReporteName, JasperGenericService.TXT);
		} catch (final Exception genericExc) {
			LOGGER.error("getFileTxt", genericExc);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, "Hubo un error al generar el reporte, contacte a su administrador.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return streamedContent;
	}

	/**
	 * Gets the file csv.
	 *
	 * @return the file csv
	 */
	public StreamedContent getFileCsv() {
		StreamedContent streamedContent = null;
	    LOGGER.info(":: Generando reporte de texto plano getFileCsv");
		// media = null;
		try {
			streamedContent =  generaReporteSimple(ReporteadorDirecto.CSV);
		} catch (final Exception genericExc) {
			LOGGER.error("getFileCsv", genericExc);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, "Hubo un error al generar el reporte, contacte a su administrador.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return streamedContent;
	}
	
	/**
	 * Creates the file pdf inline.
	 */
	public void createFilePdfInline() {
		uuid = UUID.randomUUID().toString()+"_";	
		LOGGER.info(":: Generar reporte de Pdf, {}", jasperReporteName);
		try{
			jasperGenericService.createReportPdftoFs(getParametersReports(), jasperReporteName, GetFileInlineServlet.getFullPath(uuid, endFilename));
		} catch (final ReportValidationException params) {
			uuid = null;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, params.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (final Exception genericExc) {
			uuid = null;	
			genericExc.printStackTrace();
			LOGGER.error ("createFilePdfInline: Report = {} ", jasperReporteName, genericExc);	
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, "Hubo un error al generar el reporte, contacte a su administrador.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}	
	
	/**
	 * Gets the tc imagenes muni repository.
	 *
	 * @return the tc imagenes muni repository
	 */
	public TcImagenesMuniRepository getTcImagenesMuniRepository() {
		return tcImagenesMuniRepository;
	}

	/**
	 * Sets the tc imagenes muni repository.
	 *
	 * @param tcImagenesMuniRepository the new tc imagenes muni repository
	 */
	public void setTcImagenesMuniRepository(TcImagenesMuniRepository tcImagenesMuniRepository) {
		this.tcImagenesMuniRepository = tcImagenesMuniRepository;
	}

	/**
	 * Gets the tc imagenes ent admin repository.
	 *
	 * @return the tc imagenes ent admin repository
	 */
	public TcImagenesEntAdminRepository getTcImagenesEntAdminRepository() {
		return tcImagenesEntAdminRepository;
	}

	/**
	 * Sets the tc imagenes ent admin repository.
	 *
	 * @param tcImagenesEntAdminRepository the new tc imagenes ent admin repository
	 */
	public void setTcImagenesEntAdminRepository(TcImagenesEntAdminRepository tcImagenesEntAdminRepository) {
		this.tcImagenesEntAdminRepository = tcImagenesEntAdminRepository;
	}

	/**
	 * Gets the jasper generic service.
	 *
	 * @return the jasper generic service
	 */
	public JasperGenericService getJasperGenericService() {
		return jasperGenericService;
	}

	/**
	 * Sets the jasper generic service.
	 *
	 * @param jasperGenericService the new jasper generic service
	 */
	public void setJasperGenericService(JasperGenericService jasperGenericService) {
		this.jasperGenericService = jasperGenericService;
	}
	
	/**
	 * Gets the reportes repository.
	 *
	 * @return the reportes repository
	 */
	public ReportesRepository getReportesRepository() {
		return reportesRepository;
	}

	/**
	 * Sets the reportes repository.
	 *
	 * @param reportesRepository the new reportes repository
	 */
	public void setReportesRepository(ReportesRepository reportesRepository) {
		this.reportesRepository = reportesRepository;
	}
	

	/**
	 * Gets the reporteador directo impl.
	 *
	 * @return the reporteador directo impl
	 */
	public ReporteadorDirecto getReporteadorDirectoImpl() {
		return reporteadorDirectoImpl;
	}


	/**
	 * Sets the reporteador directo impl.
	 *
	 * @param reporteadorDirectoImpl the new reporteador directo impl
	 */
	public void setReporteadorDirectoImpl(ReporteadorDirecto reporteadorDirectoImpl) {
		this.reporteadorDirectoImpl = reporteadorDirectoImpl;
	}

	/*
	public StreamedContent getMedia() {
		return media;
	}

	public void setMedia(StreamedContent media) {
		this.media = 	media;
	}*/
	
	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the uuid.
	 *
	 * @param uuid the new uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * Gets the end filename.
	 *
	 * @return the end filename
	 */
	public String getEndFilename() {
		return endFilename;
	}

	/**
	 * Sets the end filename.
	 *
	 * @param endFilename the new end filename
	 */
	public void setEndFilename(String endFilename) {
		this.endFilename = endFilename;
	}

	/**
	 * Gets the jasper reporte name.
	 *
	 * @return the jasper reporte name
	 */
	public String getJasperReporteName() {
		return jasperReporteName;
	}

	/**
	 * Sets the jasper reporte name.
	 *
	 * @param jasperReporteName the new jasper reporte name
	 */
	public void setJasperReporteName(String jasperReporteName) {
		this.jasperReporteName = jasperReporteName;
	}
	
}
