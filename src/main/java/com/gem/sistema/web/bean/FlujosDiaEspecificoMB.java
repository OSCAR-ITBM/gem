package com.gem.sistema.web.bean;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class FlujosDiaEspecificoMB.
 */
@ManagedBean
@ViewScoped
public class FlujosDiaEspecificoMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The fecha. */
	private Date fecha;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct FlujosDiaEspecificoMB ");
		jasperReporteName = "FlujosDiaEspecifico";
		endFilename = jasperReporteName + ".pdf";

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Integer sector = this.getUserDetails().getIdSector();
		Firmas firmas = firmasRepository.findAllByIdsector(getUserDetails().getIdSector());
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		parameters.put("P_SECTOR", getUserDetails().getIdSector());
		parameters.put("P_CAMPO1", firmas.getCampo1());
		parameters.put("P_FECHA",new java.sql.Date(fecha.getTime()));
		parameters.put("imagePath", this.getUserDetails().getPathImgCab1());
		parameters.put("entidadRfc", firmas.getCampo2());
		parameters.put("firmaP1", (sector == 1) ? firmas.getL27() : firmas.getL4());
		parameters.put("firmaN1", (sector == 1) ? firmas.getN27() : firmas.getN4());
		parameters.put("firmaP2", (sector == 1) ? firmas.getL28() : firmas.getL5());
		parameters.put("firmaN2", (sector == 1) ? firmas.getN28() : firmas.getN5());
		parameters.put("firmaP3", (sector == 1) ? firmas.getL5() : firmas.getL27());
		parameters.put("firmaN3", (sector == 1) ? firmas.getN5() : firmas.getN27());
		parameters.put("firmaP4", (sector == 1) ? firmas.getL3() : "");
		parameters.put("firmaN4", (sector == 1) ? firmas.getN3() : "");
		
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Creates the file pdf inline validate.
	 */
	public void createFilePdfInlineValidate() {
		if (Objects.nonNull(fecha)) {
			createFilePdfInline();
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
			RequestContext.getCurrentInstance()
					.execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '45em');");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Seleccione una fecha por favor.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		}
	}
	
	/**
	 * Creates the file txt inline validate.
	 */
	public void createFileTxtInlineValidate() {
		if (Objects.nonNull(fecha)) {
			RequestContext.getCurrentInstance().execute("$(PrimeFaces.escapeClientId('form1:donwloadTxt')).click();");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Seleccione una fecha por favor.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	/**
	 * Creates the file xls inline validate.
	 */
	public void createFileXlsInlineValidate() {
		if (Objects.nonNull(fecha)) {
			RequestContext.getCurrentInstance().execute("$(PrimeFaces.escapeClientId('form1:donwloadXls')).click();");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Seleccione una fecha por favor.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
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
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
