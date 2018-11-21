package com.gem.sistema.web.bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.predicates.PolidePredicate;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class CambioReferenciaMB.
 */
@ManagedBean
@ViewScoped
public class CambioReferenciaMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The polide repository. */
	@ManagedProperty("#{polideRepository}")
	private PolideRepository polideRepository;

	/** The polien repository. */
	@ManagedProperty("#{polienRepository}")
	private PolienRepository polienRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The polide. */
	private Polide polide;

	/** The anopol. */
	private Integer anopol;

	private Boolean bSave;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		Firmas firmas = firmasRepository.findAllByIdsector(getUserDetails().getIdSector());

		polide = new Polide();
		anopol = conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp();
		this.setbSave(Boolean.FALSE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

	/**
	 * Obtener polide.
	 */
	public void obtenerPolide() {
		if (validarCamposVacios() && tienePermiso()) {
			Polide polideFound = polideRepository.findByIdsectorAndTippolAndConpolAndRenpolAndMespolAndAnopol(
					getUserDetails().getIdSector(), polide.getTippol(), polide.getConpol(), polide.getRenpol(),
					polide.getMespol(), anopol);
			if (CollectionUtils.isEmpty((Collection<?>) polideRepository
					.findAll(PolidePredicate.existPolide(anopol, polide.getTippol(), polide.getMespol(),
							polide.getConpol(), polide.getRenpol(), this.getUserDetails().getIdSector())))) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
						"No existe esta poliza o renglon, verifique.");
				FacesContext.getCurrentInstance().addMessage(null, message);
				RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
			} else {
				polide = polideFound;
				this.setbSave(Boolean.TRUE);
			}
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		}
	}

	/**
	 * Guardar cambios.
	 */
	public void guardarCambios() {
		if (this.getbSave() == Boolean.TRUE) {
			if (validarCamposVacios() && tienePermiso() && Objects.nonNull(polide.getRefpol())
					&& polide.getRefpol().compareTo(BigDecimal.ZERO) >= 0) {
				RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
				polideRepository.save(polide);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
						"Referencia modificada con éxito.");

				FacesContext.getCurrentInstance().addMessage(null, message);
			} else {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
						"No se puede actualizar el campo de Polide");
				FacesContext.getCurrentInstance().addMessage(null, message);
				RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"No se puede actualizar el campo de polide");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * Tiene permiso.
	 *
	 * @return true, if successful
	 */
	private boolean tienePermiso() {
		Polien polien = polienRepository.findFirstByMespolAndAnopolAndConpolAndTippolAndIdsectorOrderById(
				polide.getMespol(), anopol, polide.getConpol(), polide.getTippol(), getUserDetails().getIdSector());
		boolean tienePermiso = Objects.nonNull(polien) && polien.getCappol().equals(getUserDetails().getUsername());
		if (!tienePermiso) {
			String msg = Objects.nonNull(polien) ? "Usuario no autorizado para modificar poliza." : "No existe poliza.";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY, msg);
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		}
		return tienePermiso;
	}

	/**
	 * Validar campos vacios.
	 *
	 * @return true, if successful
	 */
	private boolean validarCamposVacios() {
		boolean isValid = StringUtils.isNotBlank(polide.getTippol()) && Objects.nonNull(polide.getConpol())
				&& polide.getConpol() > 0 && Objects.nonNull(polide.getRenpol())
				&& polide.getRenpol().compareTo(BigDecimal.ZERO) > 0 && Objects.nonNull(polide.getMespol())
				&& polide.getMespol() > 0 && Objects.nonNull(anopol) && anopol > 0;
		if (!isValid) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Por favor debe capturar los campos Tipo Poliza, Número Poliza, Renglon, Mes Poliza y Año Poliza.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		}
		return isValid;
	}

	/**
	 * Gets the last year.
	 *
	 * @return the last year
	 */
	public String getLastYear() {
		return String.valueOf((LocalDate.now()).getYear());
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	// getters and setters
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
	 * Gets the polide repository.
	 *
	 * @return the polide repository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository
	 *            the new polide repository
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository
	 *            the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	/**
	 * Gets the polide.
	 *
	 * @return the polide
	 */
	public Polide getPolide() {
		return polide;
	}

	/**
	 * Sets the polide.
	 *
	 * @param polide
	 *            the new polide
	 */
	public void setPolide(Polide polide) {
		this.polide = polide;
	}

	/**
	 * Gets the anopol.
	 *
	 * @return the anopol
	 */
	public Integer getAnopol() {
		return anopol;
	}

	/**
	 * Sets the anopol.
	 *
	 * @param anopol
	 *            the new anopol
	 */
	public void setAnopol(Integer anopol) {
		this.anopol = anopol;

	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public Boolean getbSave() {
		return bSave;
	}

	public void setbSave(Boolean bSave) {
		this.bSave = bSave;
	}

}
