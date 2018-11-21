package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

// TODO: Auto-generated Javadoc
/**
 * The Class ECPEMB.
 *
 * @author Alfredo
 */
@ManagedBean
@ViewScoped
public class ECPEMB extends BaseDirectReport {

	/** The mes. */
	private Integer mes;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "ECPE";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		String mesName = tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0")).getDescripcion();
		firmas = firmasRepository.findAllByIdsector(sector);
		conctb = conctbRepository.findByIdsector(sector);

		paramsReport.put("imagePath1", this.getUserDetails().getPathImgCab1());
		paramsReport.put("imagePath2", this.getUserDetails().getPathImgCab2());
		paramsReport.put("entidadName", firmas.getCampo1());
		paramsReport.put("entidadClave", conctb.getClave());
		paramsReport.put("lastDayOfMonth", getLastDayByAnoEmp(mes, firmas.getCampo3()));
		paramsReport.put("mesName", mesName);
		paramsReport.put("year", firmas.getCampo3());
		paramsReport.put("sql", this.generateQueryECPE(mes, sector));
		paramsReport.put("firmaP1", firmas.getL27());
		paramsReport.put("firmaN1", firmas.getN27());
		paramsReport.put("firmaP2", firmas.getL28());
		paramsReport.put("firmaN2", firmas.getN28());
		paramsReport.put("firmaP3", firmas.getL5());
		paramsReport.put("firmaN3", firmas.getN5());
		paramsReport.put("firmaP4", firmas.getL3());
		paramsReport.put("firmaN4", firmas.getN3());

		return paramsReport;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Generate query ECPE.
	 *
	 * @param mes the mes
	 * @param sector the sector
	 * @return the string
	 */
	public String generateQueryECPE(Integer mes, Integer sector) {
		StringBuilder sQuery = new StringBuilder();
		StringBuilder toej = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder ampl = new StringBuilder();
		StringBuilder auto = new StringBuilder();

		for (Integer i = 1; i <= mes; i++) {
			auto.append(" PA.AUTO1_" + i + " +");
			redu.append(" PA.REDU1_" + i + " +");
			ampl.append(" PA.AMPLI1_" + i + " +");
			toej.append(" PA.TOEJE1_" + i + " +");
		}

		sQuery.append("SELECT	T1.*, (T1.MODIFICADO_ACUMULADO - T1.EJERCIDO_ACUMULADO) ABSOLUTA,")
				.append("DECODE(SUBSTR(T1.PARTIDA,2,3),'000',1,0) SUMAR ").append("FROM( SELECT PA.PARTIDA,NA.NOMGAS,")
				.append("SUM(PA.AUTO1_1 + PA.AUTO1_2 + PA.AUTO1_3 + PA.AUTO1_4 + PA.AUTO1_5 + PA.AUTO1_6 + PA.AUTO1_7 + PA.AUTO1_8 + PA.AUTO1_9 + PA.AUTO1_10 + PA.AUTO1_11 + PA.AUTO1_12) AUTORIZADO,")
				.append("SUM(PA.AUTO1_" + mes + " + PA.AMPLI1_" + mes + " - PA.REDU1_" + mes + ")MODIFICADO_MES,")
				.append("SUM(PA.TOEJE1_" + mes + ") EJERCIDO_MES,")
				.append("SUM((" + auto.substring(0, auto.length() - 2) + ") + (" + ampl.substring(0, ampl.length() - 2)
						+ ") - (" + redu.substring(0, redu.length() - 2) + ")) MODIFICADO_ACUMULADO,")
				.append("SUM(" + toej.substring(0, toej.length() - 2) + ") EJERCIDO_ACUMULADO")
				.append(" FROM PASO PA, NATGAS NA")
				.append(" WHERE PA.IDSECTOR = NA.IDSECTOR AND PA.PARTIDA = NA.CLVGAS AND PA.IDSECTOR= " + sector)
				.append(" GROUP BY PA.PARTIDA,NA.NOMGAS ").append("ORDER BY PA.PARTIDA )T1");

		return sQuery.toString();
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}
	
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
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
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
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
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}
	
	
}
