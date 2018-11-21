/**
 * 
 */
package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
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
import com.gem.sistema.business.service.callsp.CallSpService;
import com.gem.sistema.business.service.reportador.JasperGenericService;
import com.gem.sistema.business.service.reportador.ReporteadorDirecto;
import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.web.util.FrontProperty;
import com.gem.sistema.web.util.GetFileInlineServlet;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDirectReportByPL.
 */
@ManagedBean
@ViewScoped
public class BaseDirectReportByPL {
	
	/** The Constant LOGGER. */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDirectReportByPL.class);
	
	/** The Constant MESSAGE_ERROR. */
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The call sp service. */
	@ManagedProperty(value = "#{callSpReportServiceImpl}")
	protected CallSpService callSpService;

	/**
	 * Gets the call sp service.
	 *
	 * @return the call sp service
	 */
	public CallSpService getCallSpService() {
		return callSpService;
	}
	
	/**
	 * Sets the call sp service.
	 *
	 * @param callSpService the new call sp service
	 */
	public void setCallSpService(CallSpService callSpService) {
		this.callSpService = callSpService;
	}
	
	/**
	 * getUserDetails Metodo para obtener el usuario logueado.
	 *
	 * @return the user details
	 */
	public GemUser getUserDetails() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            return (GemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else {
            return null;
        }
    }
	
/*
	public void sendFileToUser(File fileToSend) {
		FileInputStream fileInputStream = null;
		OutputStream output = null;
		try {
			byte[] ingresoRespFile = new byte[(int) fileToSend.length()];
			fileInputStream = new FileInputStream(fileToSend);
			fileInputStream.read(ingresoRespFile);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

			String fileName = fileToSend.getName();
			fileToSend.delete();
			response.reset();
			response.setContentType("application/zip");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

			output = response.getOutputStream();
			output.write(ingresoRespFile);
			facesContext.responseComplete();
		} catch (IOException e) {
			//LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		} finally {
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}

			if (null != output) {
				try {
					output.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}
	*/
	
	/**
 * Send file by stream.
 *
 * @param fileToSend the file to send
 * @return the streamed content
 * @throws FileNotFoundException the file not found exception
 */
public StreamedContent sendFileByStream(File fileToSend) throws FileNotFoundException {
	   ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	   return new DefaultStreamedContent(new FileInputStream(fileToSend), externalContext.getMimeType(fileToSend.getName()), fileToSend.getName());
	}
	
}
