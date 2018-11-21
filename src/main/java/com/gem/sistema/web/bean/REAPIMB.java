package com.gem.sistema.web.bean;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.DataYearSystemService;

// TODO: Auto-generated Javadoc
/**
 * The Class REAPIMB.
 */
@ManagedBean
@ViewScoped
public class REAPIMB extends BaseDirectReport {
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The data year system service. */
	@ManagedProperty(value = "#{dataYearSystemService}")
	private DataYearSystemService dataYearSystemService;

	/**
	 * Gets the data year system service.
	 *
	 * @return the data year system service
	 */
	public DataYearSystemService getDataYearSystemService() {
		return dataYearSystemService;
	}

	/**
	 * Sets the data year system service.
	 *
	 * @param dataYearSystemService the new data year system service
	 */
	public void setDataYearSystemService(DataYearSystemService dataYearSystemService) {
		this.dataYearSystemService = dataYearSystemService;
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

	/** The mes. */
	private Integer mes;

	// private String logo = "../reports/img/cuautitlan.jpg";

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct EAPI ");
		// reportId = 2;
		// tcReporte = reportesRepository.findOne(reportId);
		jasperReporteName = "EAPI";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());

		paramsReport.put("AN", firmas.getCampo3());
		paramsReport.put("MES", mes);
		paramsReport.put("LOGO", this.getUserDetails().getPathImgCab1());
		paramsReport.put("idSector", this.getUserDetails().getIdSector());
		if (this.getUserDetails().getIdSector() == 2) {
			paramsReport.put("firmaP1", firmas.getL4());
			paramsReport.put("firmaP2", firmas.getL5());
			paramsReport.put("firmaP3", firmas.getL27());
			paramsReport.put("firmaP4", "");
			paramsReport.put("firmaN1", firmas.getN4());
			paramsReport.put("firmaN2", firmas.getN5());
			paramsReport.put("firmaN3", firmas.getN27());
			paramsReport.put("firmaN4", "");
		} else {
			paramsReport.put("firmaP1", firmas.getL1());
			paramsReport.put("firmaP2", firmas.getL28());
			paramsReport.put("firmaP3", firmas.getL2());
			paramsReport.put("firmaP4", firmas.getL3());
			paramsReport.put("firmaN1", firmas.getN1());
			paramsReport.put("firmaN2", firmas.getN28());
			paramsReport.put("firmaN3", firmas.getN2());
			paramsReport.put("firmaN4", firmas.getN3());
		}
		paramsReport.put("dependenciaName", firmas.getCampo1());
		if (this.getUserDetails().getIdSector() == 2)
			paramsReport.put("where", this.generateWhere(mes));
		else
			paramsReport.put("where", "");

		paramsReport.put("LOGO_2", this.getUserDetails().getPathImgCab2());
		paramsReport.put("NumMun", conctb.getClave());

		return paramsReport;
	}

	/**
	 * Generate where.
	 *
	 * @param mes the mes
	 * @return the string
	 */
	public String generateWhere(Integer mes) {
		StringBuilder cargos10 = new StringBuilder();
		StringBuilder abonos10 = new StringBuilder();
		StringBuilder cargos40 = new StringBuilder();
		StringBuilder abonos40 = new StringBuilder();
		StringBuilder cargos50 = new StringBuilder();
		StringBuilder abonos50 = new StringBuilder();
		StringBuilder where = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			cargos10.append(" OR DI.CARGOS_" + i + " <> 0 ");
			abonos10.append(" OR DI.ABONOS_" + i + " <> 0 ");
			cargos40.append(" OR CU.CARGOS_" + i + " <> 0 ");
			abonos40.append(" OR CU.ABONOS_" + i + " <> 0 ");
			cargos50.append(" OR CI.CARGOS_" + i + " <> 0 ");
			abonos50.append(" OR CI.ABONOS_" + i + " <> 0 ");
			i++;
		}

		where.append("AND (DI.SALINI <> 0" + cargos10 + abonos10).append(" OR CU.SALINI <> 0 " + cargos40 + abonos40)
				.append(" OR CI.SALINI <> 0 " + cargos50 + abonos50).append(")");
		return where.toString();
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

}
