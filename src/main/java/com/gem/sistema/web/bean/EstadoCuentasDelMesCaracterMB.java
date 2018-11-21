package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;

import java.util.ArrayList;
import java.util.List;
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

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.google.common.base.Strings;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCuentasDelMesCaracterMB.
 */
@ManagedBean
@ViewScoped
public class EstadoCuentasDelMesCaracterMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/** The cuenta repository. */
	@ManagedProperty("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	/** The mes. */
	private Integer mes;
	
	/** The cuenta. */
	private Cuenta cuenta;
	
	/** The order by. */
	private String orderBy;
	
	/** The selected cuenta. */
	private Cuenta selectedCuenta;
	
	/** The cuentas. */
	private List<Cuenta> cuentas;
	
	/** The cuenta mayor. */
	private String cuentaMayor;
	
	/** The nombre cuenta mayor. */
	private String nombreCuentaMayor;
	
	/** The Constant SECTOR_MUNICIPAL. */
	private static final Integer SECTOR_MUNICIPAL = 1;
	
	/** The Constant SECTOR_CENTRAL. */
	private static final Integer SECTOR_CENTRAL = 2;
	
	/** The bandera M. */
	private boolean banderaM = Boolean.FALSE;
	
	/** The bandera C. */
	private boolean banderaC = Boolean.FALSE;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct EstadoCuentasMesMB ");
		// reportId = 2;
		// jasperReporteName = "EstadoCuentasDelMesCaracter";
		jasperReporteName = "EstadoCuentasDelMesGrafico";
		endFilename = "EstadoCuentasDelMesCaracter.pdf";

		cuenta = new Cuenta();
		orderBy = "FECPOL";

		if (this.getUserDetails().getIdSector() == SECTOR_MUNICIPAL) {
			banderaM = Boolean.TRUE;
		} else if (this.getUserDetails().getIdSector() == SECTOR_CENTRAL) {
			banderaM = Boolean.FALSE;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		parameters.put("P_SECTOR", getUserDetails().getIdSector());
		parameters.put("P_MES", mes);
		parameters.put("P_IMG", getUserDetails().getPathImgCab1());

		parameters.put("P_CAMPO1", firmas.getCampo1());
		parameters.put("P_CAMPO2", firmas.getCampo2());
		parameters.put("P_CAMPO3", firmas.getCampo3());

		parameters.put("P_CUENTA", cuenta.getCuenta());
		parameters.put("P_SCTA", cuenta.getScuenta());
		parameters.put("P_SSCTA", cuenta.getSscuenta());
		parameters.put("P_SSSCTA", cuenta.getSsscuenta());
		parameters.put("P_SSSSCTA", cuenta.getSssscuenta());

		parameters.put("P_L3", firmas.getL27());
		parameters.put("P_N3", firmas.getN27());
		parameters.put("P_L4", firmas.getL4());
		parameters.put("P_N4", firmas.getN4());
		parameters.put("P_L5", firmas.getL5());
		parameters.put("P_N5", firmas.getN5());

		parameters.put("P_ORDER_BY", orderBy);
		parameters.put("P_ORDER_BY_EXTENSO", orderBy.equals("FECPOL") ? "FECHA"
				: (orderBy.equals("REFPOL") ? "REFERENCIA" : (orderBy.equals("CONCEP") ? "CONCEPTO" : "MES")));
		parameters.put("abonos_cargos", this.generateCargosAbonos(mes));
		
		return parameters;
	}

	/**
	 * Generate cargos abonos.
	 *
	 * @param mes the mes
	 * @return the string
	 */
	public String generateCargosAbonos(Integer mes) {
		StringBuilder cadena = new StringBuilder();
		Integer i = 1;

		if (mes != 1) {
			while (i < mes) {
				cadena.append(" + CUENTA.CARGOS_" + i + " - CUENTA.ABONOS_" + i);

				i++;
			}
		} else {
			cadena.append(" + 0 - 0 ");
		}

		return cadena.toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String,
		 * Object>(); paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
	}

	/**
	 * Buscar.
	 */
	public void buscar() {
		cuentas = cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuentaMayor, null, null, null, null,
				StringUtils.isEmpty(nombreCuentaMayor) ? nombreCuentaMayor : nombreCuentaMayor.trim().toUpperCase(),
				((Integer) getUserDetails().getIdSector()).longValue());
	}

	/**
	 * Left pad.
	 */
	public void leftPad() {
		String value = cuenta.getScuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setScuenta(StringUtils.leftPad(value, 10, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSscuenta(StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSsscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSsscuenta(StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSssscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSssscuenta(StringUtils.leftPad(value, 3, StringUtils.EMPTY + ZERO));
		}
	}

	/**
	 * On row select.
	 */
	public void onRowSelect() {
		if (Objects.nonNull(selectedCuenta)) {
			this.cuenta.setCuenta(selectedCuenta.getCuenta());
			this.cuenta.setScuenta(selectedCuenta.getScuenta());
			this.cuenta.setSscuenta(selectedCuenta.getSscuenta());
			this.cuenta.setSsscuenta(selectedCuenta.getSsscuenta());
			this.cuenta.setSssscuenta(selectedCuenta.getSssscuenta());
		}
	}

	/**
	 * Todos vacios.
	 *
	 * @return true, if successful
	 */
	private boolean todosVacios() {
		return Strings.isNullOrEmpty(cuenta.getCuenta()) && Strings.isNullOrEmpty(cuenta.getScuenta())
				&& Strings.isNullOrEmpty(cuenta.getSscuenta()) && Strings.isNullOrEmpty(cuenta.getSscuenta())
				&& Strings.isNullOrEmpty(cuenta.getSscuenta());
	}

	/**
	 * Creates the file txt inline validate.
	 */
	public void createFileTxtInlineValidate() {
		if (!validateCuenta()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			RequestContext.getCurrentInstance().execute("$(PrimeFaces.escapeClientId('form1:donwloadTxt')).click();");
		}
	}

	/**
	 * Validate cuenta.
	 *
	 * @return true, if successful
	 */
	private boolean validateCuenta() {
		List<Cuenta> lista = todosVacios() ? new ArrayList<Cuenta>()
				: cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuenta.getCuenta(), cuenta.getScuenta(),
						cuenta.getSscuenta(), cuenta.getSsscuenta(), cuenta.getSssscuenta(), null,
						((Integer) getUserDetails().getIdSector()).longValue());
		return !lista.isEmpty();
	}

	/**
	 * Creates the file pdf inline validate.
	 */
	public void createFilePdfInlineValidate() {
		if (!validateCuenta()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		} else {
			createFilePdfInline();
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
			RequestContext.getCurrentInstance()
					.execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '57em');");
		}
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas the new cuentas
	 */
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the selected cuenta.
	 *
	 * @return the selected cuenta
	 */
	public Cuenta getSelectedCuenta() {
		return selectedCuenta;
	}

	/**
	 * Sets the selected cuenta.
	 *
	 * @param selectedCuenta the new selected cuenta
	 */
	public void setSelectedCuenta(Cuenta selectedCuenta) {
		this.selectedCuenta = selectedCuenta;
	}

	/**
	 * Gets the cuenta mayor.
	 *
	 * @return the cuenta mayor
	 */
	public String getCuentaMayor() {
		return cuentaMayor;
	}

	/**
	 * Sets the cuenta mayor.
	 *
	 * @param cuentaMayor the new cuenta mayor
	 */
	public void setCuentaMayor(String cuentaMayor) {
		this.cuentaMayor = cuentaMayor;
	}

	/**
	 * Gets the nombre cuenta mayor.
	 *
	 * @return the nombre cuenta mayor
	 */
	public String getNombreCuentaMayor() {
		return nombreCuentaMayor;
	}

	/**
	 * Sets the nombre cuenta mayor.
	 *
	 * @param nombreCuentaMayor the new nombre cuenta mayor
	 */
	public void setNombreCuentaMayor(String nombreCuentaMayor) {
		this.nombreCuentaMayor = nombreCuentaMayor;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
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
	 * Gets the cuenta repository.
	 *
	 * @return the cuenta repository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the new cuenta repository
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Checks if is bandera M.
	 *
	 * @return true, if is bandera M
	 */
	public boolean isBanderaM() {
		return banderaM;
	}

	/**
	 * Sets the bandera M.
	 *
	 * @param banderaM the new bandera M
	 */
	public void setBanderaM(boolean banderaM) {
		this.banderaM = banderaM;
	}

	/**
	 * Checks if is bandera C.
	 *
	 * @return true, if is bandera C
	 */
	public boolean isBanderaC() {
		return banderaC;
	}

	/**
	 * Sets the bandera C.
	 *
	 * @param banderaC the new bandera C
	 */
	public void setBanderaC(boolean banderaC) {
		this.banderaC = banderaC;
	}

	
	
	
}
