package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

// TODO: Auto-generated Javadoc
/**
 * The Class RF01014MB.
 */
@ManagedBean(name = "rF01014MB")
@ViewScoped
public class RF01014MB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The mes. */
	private Integer mes;

	/** The firmas. */
	private Firmas firmas;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/**
	 * 
	 */
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF010.1.4 ");
		jasperReporteName = "RF010.1.4";
		endFilename = jasperReporteName + ".pdf";
	}

	/** The params report. */
	Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());

		paramsReport.put("AN", firmas.getCampo3());
		paramsReport.put("USUARIO", this.getUserDetails().getUsername());
		paramsReport.put("MES", mes);
		paramsReport.put("LOGO", this.getUserDetails().getPathImgCab1());
		paramsReport.put("SECTOR", getUserDetails().getIdSector());
		paramsReport.put("entidadName", firmas.getCampo1());
		if (this.getUserDetails().getIdSector() == 2) {
			paramsReport.put("firmaP1", firmas.getL4());
			paramsReport.put("firmaP2", firmas.getL5());
			paramsReport.put("firmaP3", firmas.getL27());
			paramsReport.put("firmaN1", firmas.getN4());
			paramsReport.put("firmaN2", firmas.getN5());
			paramsReport.put("firmaN3", firmas.getN27());
		} else {
			paramsReport.put("firmaP1", firmas.getL27());
			paramsReport.put("firmaP2", firmas.getL5());
			paramsReport.put("firmaP3", firmas.getL3());
			paramsReport.put("firmaN1", firmas.getN27());
			paramsReport.put("firmaN2", firmas.getN5());
			paramsReport.put("firmaN3", firmas.getN3());
		}
		paramsReport.put("lastDayOfMonth", getLastDayByAnoEmp(mes, firmas.getCampo3()));
		paramsReport.put("nameMonth",
				tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, '0')).getDescripcion());
		paramsReport.put("QUERY_INGRESOS", this.generateQueryIngresos(mes, this.getUserDetails().getIdSector()));
		paramsReport.put("QUERY_EGRESOS", this.generateQueryEgresos(mes, this.getUserDetails().getIdSector()));
		paramsReport.put("QUERY_EGRESOS5002", this.GenerateQueryEgresos5002(mes, this.getUserDetails().getIdSector()));

		return paramsReport;
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

	/*
	 * (non-Javadoc)
	 * 
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
	 * Generate query ingresos.
	 *
	 * @param mes
	 *            the mes
	 * @param sector
	 *            the sector
	 * @return the string
	 */
	public String generateQueryIngresos(Integer mes, Integer sector) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder acomunlado = new StringBuilder().append(" (C.ABONOS_1 - C.CARGOS_1) ");
		StringBuilder mesActual = new StringBuilder().append(" C.ABONOS_" + mes + " - C.CARGOS_" + mes + " ");
		StringBuilder mesAnterior = new StringBuilder().append(" (C.ABONOS_1 - C.CARGOS_1) ");

		for (int i = 2; i <= mes; i++) {

			acomunlado.append("+ (C.ABONOS_" + i + " - C.CARGOS_" + i + ") ");
			if (i < mes) {
				mesAnterior.append("+ (C.ABONOS_" + i + " - C.CARGOS_" + i + ") ");
			}
		}

		if (mes == 1)
			mesAnterior.replace(0, mesActual.length() + 1, " 0.000 ");

		sSql.append(" SELECT " + " CUENTA " + " , CONCEPTO " + " , GRUPO " + " , SUM(MES_ANT) MES_ANT "
				+ " , SUM(MES_ACT) MES_ACT " + " , SUM(ACUMULADO) ACUMULADO " + " FROM ( " + " SELECT "
				+ " 	C.CUENTA "
				+ " 	, DECODE(C.CUENTA, '4100', 'TOTAL DE INGRESOS DE GESTION', '4300',  'TOTAL DE OTROS NGRESOS', '4200', 'TOTAL DE PARTICIPACIONES Y APORTACIONES', C.NOMCTA) AS CONCEPTO "
				+ " 	, substr(C.SCTA,8,3) as GRUPO  " + " 	, " + mesAnterior + " as MES_ANT " + " 	, " + mesActual
				+ " as MES_ACT " + " 	, " + acomunlado + " AS ACUMULADO " + " FROM " + " 	CUENTA C " + " WHERE "
				+ " 	C.CUENTA BETWEEN '4100' AND '4399' AND " + " 	substr(C.CUENTA,4,1) <> '0' AND "
				+ " 	C.SCTA<>'' AND C.SSCTA='' AND " + " 	C.IDSECTOR = " + sector + " ) "
				+ " GROUP BY CUENTA, GRUPO, CONCEPTO " + " ORDER BY CUENTA,GRUPO ASC   ");

		return sSql.toString();
	}

	/**
	 * Generate query egresos.
	 *
	 * @param mes
	 *            the mes
	 * @param sector
	 *            the sector
	 * @return the string
	 */
	public String generateQueryEgresos(Integer mes, Integer sector) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder acomunlado = new StringBuilder().append(" (C.CARGOS_1 - C.ABONOS_1) ");
		StringBuilder mesActual = new StringBuilder().append(" C.CARGOS_" + mes + " - C.ABONOS_" + mes + " ");
		StringBuilder mesAnterior = new StringBuilder().append(" (C.CARGOS_1 - C.ABONOS_1) ");

		for (int i = 2; i <= mes; i++) {
			acomunlado.append("+ (C.CARGOS_" + i + " - C.ABONOS_" + i + ") ");
			if (i < mes) {
				mesAnterior.append("+ (C.CARGOS_" + i + " - C.ABONOS_" + i + ") ");
			}
		}

		if (mes == 1)
			mesAnterior.replace(0, mesActual.length() + 1, " 0.000 ");

		sSql.append(" SELECT  " + "    CUENTA  " + " ,  CONCEPTO " + " ,  SALINI " + " ,  GRUPO "
				+ " ,  SUM(MES_ANT) MES_ANT " + " ,  SUM(MES_ACT) MES_ACT " + " ,  SUM(ACUMULADO) ACUMULADO "
				+ " FROM ( " + " SELECT " + " 	C.CUENTA "
				+ " 	, DECODE(SUBSTR(C.SSSCTA,1,1)||'000','1000', 'SERVICIOS PERSONALES ','2000','MATERIALES Y SUMINISTROS','3000','SERVICIOS GENERALES','4000','TRANSFERENCIAS','5000','MUEBLES E INMUEBLES','6000','INVERSION PUBLICA','7000','INVERSIONES FINANCIERAS','8000','PART. Y APORTACIONES','9000','DEUDA PUBLICA',C.NOMCTA)  AS CONCEPTO "
				+ " 	, NVL(C.SALINI,0) SALINI " + " 	, C.SSSCTA " + " 	, SUBSTR(C.SSSCTA,1,1)||'000' as GRUPO "
				+ " 	, " + mesAnterior + " as MES_ANT " + " 	, " + mesActual + " as MES_ACT " + " 	, " + acomunlado
				+ " AS ACUMULADO " + " FROM " + " 	CUENTA C " + " WHERE " + " 	C.CUENTA BETWEEN '5100' AND '5700' "
				+ " 	AND C.CUENTA<>'5500'  " + " 	AND C.IDSECTOR = " + sector + " ) " + " WHERE GRUPO <>' 000' "
				+ " GROUP BY  CUENTA,CONCEPTO, SALINI, GRUPO " + " 	ORDER BY GRUPO ASC ");

		return sSql.toString();
	}

	/**
	 * Generate query egresos 5002.
	 *
	 * @param mes
	 *            the mes
	 * @param sector
	 *            the sector
	 * @return the string
	 */
	public String GenerateQueryEgresos5002(Integer mes, Integer sector) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder acomunlado = new StringBuilder().append(" (SUM(C.CARGOS_1) - SUM(C.ABONOS_1)) ");
		Integer mes2;

		if (mes <= 1) {
			mes2 = 1;
		} else {
			mes2 = mes - 1;
		}

		for (int i = 2; i <= mes; i++) {

			acomunlado.append("+ (SUM(C.CARGOS_" + i + ") - SUM(C.ABONOS_" + i + ")) ");
		}

		sSql.append(
				" SELECT C.CUENTA , NVL(C.SCTA,'') SCTA , CASE WHEN NVL(SCTA,'')<>'' THEN C.NOMCTA ELSE 'EGRESOS EXTRAORDINARIOS'  END NOMBRE "
						+ " , CASE WHEN " + mes + ">1 THEN SUM(C.CARGOS_" + mes2 + ") - SUM(C.ABONOS_" + mes2
						+ ")  ELSE 0.00 END  MESANT " + " , SUM(C.CARGOS_" + mes + ") - SUM(C.ABONOS_" + mes
						+ ") MESACT " + " , " + acomunlado + " MESACUM  "
						+ " FROM CUENTA C WHERE C.CUENTA='5500' AND C.SSCTA = '' AND C.IDSECTOR = " + sector + " "
						+ " GROUP BY C.CUENTA , C.SCTA, C.NOMCTA ");

		return sSql.toString();
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
	 * @param mes
	 *            the new mes
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
	 * @param tcMesRepository
	 *            the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

}
