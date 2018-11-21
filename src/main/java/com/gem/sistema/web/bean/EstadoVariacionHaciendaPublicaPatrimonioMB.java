package com.gem.sistema.web.bean;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

import static com.roonin.utils.UtilDate.getLastDay;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoVariacionHaciendaPublicaPatrimonioMB.
 */
@ManagedBean
@ViewScoped
public class EstadoVariacionHaciendaPublicaPatrimonioMB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	/** The mes. */
	private Integer mes;
	
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
		LOGGER.info(":: En postconsruct EstadoVariacionHaciendaPublicaPatrimonioMB ");
		// reportId = 2;
		jasperReporteName = "HaciendaPublicaPatrimonio";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		TcMes descriMes = tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(),2, "0"));

		parameters.put("pMunicipio", firmas.getCampo1());
		parameters.put("pNoMun", conctb.getClave());
		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pAn", conctb.getAnoemp());
		parameters.put("pLastDay", getLastDay(mes));
		parameters.put("pDesMes", descriMes.getDescripcion());
		parameters.put("pL3", firmas.getL4());
		parameters.put("pN3", firmas.getN4());
		parameters.put("pL5", firmas.getL5());
		parameters.put("pN5", firmas.getN5());
		parameters.put("pL4", firmas.getL28());
		parameters.put("pN4", firmas.getN28());
		parameters.put("sSqlPatrimonio",this.generaQueryPatrimonio(this.getUserDetails().getIdSector(), mes));
		parameters.put("sSql3211",this.generaQuery3211(this.getUserDetails().getIdSector(), mes));

		return parameters;
	}
	
	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
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
	 * Genera query patrimonio.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generaQueryPatrimonio(Integer idsector, Integer mes) {
		StringBuilder cargosPant = new StringBuilder();
		StringBuilder abonosPant = new StringBuilder();
		StringBuilder cargosPact = new StringBuilder();
		StringBuilder abonosPact = new StringBuilder();
		StringBuilder sSqlP = new StringBuilder();

		if (mes == 1) {
			cargosPant.append(" 0.00");
			abonosPant.append(" 0.00");
		} else {

			for (int i = 1; i < mes; i++) {
				cargosPant.append(" C.CARGOS_" + i + "+");
				abonosPant.append(" C.ABONOS_" + i + "+");
			}
		}

		for (int i = 1; i <= mes; i++) {
			cargosPact.append(" C.CARGOS_" + i + "+");
			abonosPact.append(" C.ABONOS_" + i + "+");
		}

		sSqlP.append("SELECT T2.NATCTA,").append(" T2.GRUPO,").append(" T2.CUENTA,").append(" T2.NOMCTA,")
				.append(" T2.SALINI,")
				.append(" CASE WHEN T2.CUENTA = '1115' OR T2.CUENTA='1162' THEN T2.SALINI+T2.CARGOS_ANT-T2.ABONOS_ANT")
				.append(" ELSE T2.MESANT").append(" END MESANT,")
				.append(" CASE WHEN T2.CUENTA = '1115' OR  T2.CUENTA='1162' THEN T2.SALINI+T2.CARGOS_ACT-T2.ABONOS_ACT")
				.append(" ELSE T2.MESACT").append(" END MESACT").append(" FROM (SELECT T1.NATCTA,").append(" T1.GRUPO,")
				.append(" T1.CUENTA,").append(" T1.NOMCTA,").append(" T1.SALINI,").append(" T1.CARGOS_ANT,")
				.append(" T1.ABONOS_ANT,").append(" T1.CARGOS_ACT,").append(" T1.ABONOS_ACT,")
				.append(" CASE WHEN T1.NATCTA='D' THEN T1.SALINI+CARGOS_ANT-ABONOS_ANT")
				.append(" ELSE T1.SALINI-CARGOS_ANT+ABONOS_ANT").append(" END MESANT,")
				.append(" CASE WHEN T1.NATCTA='D' THEN T1.SALINI+CARGOS_ACT-ABONOS_ACT")
				.append(" ELSE T1.SALINI-CARGOS_ACT+ABONOS_ACT").append(" END MESACT")
				.append(" FROM (SELECT M.NATCTA NATCTA,").append(" SUBSTR(C.CUENTA,1,1) GRUPO,")
				.append(" C.CUENTA CUENTA,").append(" C.NOMCTA NOMCTA,").append(" C.SALINI SALINI,").append(" SUM(")
				.append(cargosPant.substring(1, cargosPant.length() - 1)).append(" ) CARGOS_ANT, ").append(" SUM(")
				.append(abonosPant.substring(1, abonosPant.length() - 1)).append("  ) ABONOS_ANT, ").append(" SUM(")
				.append(cargosPact.substring(1, cargosPact.length() - 1)).append("  ) CARGOS_ACT,").append(" SUM(")
				.append(abonosPact.substring(1, abonosPact.length() - 1)).append(" ) ABONOS_ACT")
				.append(" FROM CUENTA C,").append(" MAYCTA M").append(" WHERE C.CUENTA = M.CUENTA")
				.append(" AND C.CUENTA BETWEEN '3000' AND '3399'").append(" AND C.SCTA=''").append(" AND C.NOTCTA=0")
				.append(" AND C.IDSECTOR=" + idsector).append(" GROUP BY M.NATCTA,").append(" SUBSTR(C.CUENTA,1,1),")
				.append(" C.CUENTA,").append(" C.NOMCTA,").append(" C.SALINI").append(" ORDER BY C.CUENTA)T1)T2");

		return sSqlP.toString();
	}
	
	
	/**
	 * Genera query 3211.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generaQuery3211(Integer idsector, Integer mes) {
		StringBuilder cargonant = new StringBuilder();
		StringBuilder abonosant = new StringBuilder();
		StringBuilder cargosact = new StringBuilder();
		StringBuilder abonosact = new StringBuilder();
		StringBuilder sSql = new StringBuilder();

		if (mes == 1) {
			cargonant.append(" 0.00");
			abonosant.append(" 0.00");
		} else {

			for (int i = 1; i < mes; i++) {
				cargonant.append(" C.CARGOS_" + i + "+");
				abonosant.append(" C.ABONOS_" + i + "+");
			}
		}

		for (int i = 1; i <= mes; i++) {
			cargosact.append(" C.CARGOS_" + i + "+");
			abonosact.append(" C.ABONOS_" + i + "+");
		}

		sSql.append("SELECT (SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT,").append(" (SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT")
				.append(" FROM (SELECT ").append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5,")
				.append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5")
				.append(" FROM(SELECT  T1.GRUPO, ").append(" T1.SALINI,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT)")
				.append(" ELSE (T1.SALINI-ABONOS_ANT+CARGOS_ANT)").append(" END VMANT,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT)")
				.append(" ELSE (T1.SALINI-ABONOS_ACT+CARGOS_ACT)").append(" END VMACT")
				.append(" FROM (SELECT SUBSTR(C.CUENTA,1,1) GRUPO, ").append(" C.CUENTA CUENTA ,")
				.append(" C.SALINI SALINI,").append(" SUM(").append(cargonant.substring(1, cargonant.length() - 1))
				.append(") CARGOS_ANT,").append(" SUM(").append(abonosant.substring(1, abonosant.length() - 1))
				.append(") ABONOS_ANT,").append(" SUM(").append(cargosact.substring(1, cargosact.length() - 1))
				.append(") CARGOS_ACT,").append(" SUM(").append(abonosact.substring(1, abonosact.length() - 1))
				.append(") ABONOS_ACT").append(" FROM CUENTA C").append(" WHERE C.IDSECTOR=" + idsector)
				.append(" AND (((C.CUENTA >='4100' AND C.CUENTA<='4399' AND SUBSTR(C.CUENTA,4,1)<>'0')")
				.append(" OR (C.CUENTA IN ('5100','5200','5300','5400','5600','5700')))").append(" AND C.SCTA ='')")
				.append(" OR (C.CUENTA ='5500' AND C.SCTA<>'' AND C.SSCTA ='' AND C.NOTCTA=0)")
				.append(" GROUP BY C.CUENTA,").append(" C.SALINI").append(" ORDER BY C.CUENTA)T1)T2")
				.append(" GROUP BY T2.GRUPO )T3");

		return sSql.toString();

	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
