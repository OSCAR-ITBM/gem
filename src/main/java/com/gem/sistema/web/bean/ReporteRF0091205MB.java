package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.web.security.model.GemUser;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0091205MB.
 */
@ManagedBean(name = "reporteRF0091205MB")
@ViewScoped
public class ReporteRF0091205MB extends BaseDirectReport {
	
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_EAPE_DEPENDENCIA";
	
	/** The mes. */
	private Integer mes;
	
	/** The clv dep. */
	private String clvDep;
	
	/** The list catdep. */
	private List<Catdep> listCatdep;
	
	/** The file txt. */
	private StreamedContent fileTxt;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct reporteRF0091205MB ");
		jasperReporteName = "rf009_1_20_5";
		endFilename = jasperReporteName + ".pdf";
		listCatdep = catdepRepository.findAllByIdsectorOrderByClvdep(this.getUserDetails().getIdSector());
		if (!CollectionUtils.isEmpty(listCatdep)) {
			clvDep = listCatdep.get(0).getClvdep();
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		GemUser user = this.getUserDetails();
		Firmas firmas = this.firmasRepository.findAllByIdsector(user.getIdSector());
		paramsReport.put("p_mes", mes);
		paramsReport.put("p_an", firmas.getCampo3());
		paramsReport.put("p_sector", user.getIdSector());
		paramsReport.put("p_clvdep", clvDep);
		if (user.getIdSector() == 1) {
			paramsReport.put("p_l1", firmas.getL1());
			paramsReport.put("p_l2", firmas.getL2());
			paramsReport.put("p_l3", firmas.getL3());
			paramsReport.put("p_n1", firmas.getN1());
			paramsReport.put("p_n2", firmas.getN2());
			paramsReport.put("p_n3", firmas.getN3());
		} else if (user.getIdSector() == 2) {
			paramsReport.put("p_l1", firmas.getL4());
			paramsReport.put("p_l2", firmas.getL5());
			paramsReport.put("p_l3", firmas.getL27());
			paramsReport.put("p_n1", firmas.getN4());
			paramsReport.put("p_n2", firmas.getN5());
			paramsReport.put("p_n3", firmas.getN27());
		}

		paramsReport.put("p_sql", this.generateQuery(mes, user.getIdSector(), clvDep));
		paramsReport.put("p_img", user.getPathImgCab1());
		paramsReport.put("entidadName", firmas.getCampo1());
		paramsReport.put("mesName", tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0")).getDescripcion());
		paramsReport.put("lastDayOfMonth", getLastDayByAnoEmp(mes, firmas.getCampo3()));

		return paramsReport;
	}
	
	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {

			this.createFilePdfInline();
			;
		}
	}

	/** The out. */
	Map<String, Object> out;
	
	/** The stream. */
	InputStream stream = null;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getFileTxt()
	 */
	public StreamedContent getFileTxt() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", mes);
		parameter.addValue("i_sector", this.getUserDetails().getIdSector());
		parameter.addValue("i_dependencia", clvDep);
		parameter.addValue("i_query", this.generateQuery(mes, this.getUserDetails().getIdSector(), clvDep));

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		return fileTxt;
	}

	/**
	 * Sets the file txt.
	 *
	 * @param fileTxt the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * Gets the execute pl service.
	 *
	 * @return the execute pl service
	 */
	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	/**
	 * Sets the execute pl service.
	 *
	 * @param executePlService the new execute pl service
	 */
	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
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
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the list catdep.
	 *
	 * @return the list catdep
	 */
	public List<Catdep> getListCatdep() {
		return listCatdep;
	}

	/**
	 * Sets the list catdep.
	 *
	 * @param listCatdep the new list catdep
	 */
	public void setListCatdep(List<Catdep> listCatdep) {
		this.listCatdep = listCatdep;
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
	 * Gets the clv dep.
	 *
	 * @return the clv dep
	 */
	public String getClvDep() {
		return clvDep;
	}

	/**
	 * Sets the clv dep.
	 *
	 * @param clvDep the new clv dep
	 */
	public void setClvDep(String clvDep) {
		this.clvDep = clvDep;
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

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Generate query.
	 *
	 * @param mes the mes
	 * @param sector the sector
	 * @param clvdep the clvdep
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer sector, String clvdep) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder a = new StringBuilder();
		StringBuilder b = new StringBuilder();
		StringBuilder c = new StringBuilder();
		StringBuilder d = new StringBuilder();
		StringBuilder f = new StringBuilder();
		StringBuilder g = new StringBuilder();
		StringBuilder cpt = new StringBuilder();
		a.append(" P.AUTO1_1 ");
		b.append(" P.EJPA1_1 ");
		c.append(" P.EJXPA1_1 ");
		d.append(" P.TOEJE1_1 ");
		f.append(" P.AMPLI1_1 ");
		g.append(" P.REDU1_1 ");
		cpt.append(" P.COMPRO1_1 ");

		for (int i = 2; i < 13; i++) {
			a.append("+ P.AUTO1_" + i + " ");
		}
		for (int i = 2; i <= mes; i++) {
			b.append("+ P.EJPA1_" + i + " ");
			c.append("+ P.EJXPA1_" + i + " ");
			d.append("+ P.TOEJE1_" + i + " ");
			f.append("+ P.AMPLI1_" + i + " ");
			g.append("+ P.REDU1_" + i + " ");
			cpt.append("+ P.COMPRO1_" + i + " ");
		}
		sSql.append(
				" SELECT PARTIDA,  NOMGAS , A , B , C , D , F , G , CPT , ( ( A + F - G ) -D ) E , A1 , B1 , C1 , D1 , F1 , G1 , CPT1  , ( ( A1 + F1 - G1 ) -D1 ) E1 ");
		sSql.append(" FROM( ");
		sSql.append("  SELECT  ");
		sSql.append("  P.PARTIDA ");
		sSql.append("  , NOMGAS ");
		sSql.append("  , SUM( " + a + " ) A ");
		sSql.append("  , SUM( " + b + " ) B ");
		sSql.append("  , SUM( " + c + " ) C ");
		sSql.append("  , SUM( " + d + " ) D ");
		sSql.append("  , SUM( " + f + " ) F ");
		sSql.append("  , SUM( " + g + " ) G ");
		sSql.append("  , SUM( " + cpt + " ) CPT ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + a + " ), 0) ) A1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + b + " ),0 )) B1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + c + " ),0 )) C1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + d + " ),0 )) D1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + f + " ),0 )) F1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + g + " ),0 )) G1 ");
		sSql.append("  , SUM( DECODE( SUBSTR(P.PARTIDA,2,3),'000', ( " + cpt + " ),0 )) CPT1 ");
		sSql.append("    FROM PASO P, NATGAS N ");
		sSql.append("    WHERE P.PARTIDA=N.CLVGAS  ");
		sSql.append("      AND P.IDSECTOR= N.IDSECTOR  ");
		sSql.append("      AND N.IDSECTOR=" + sector + " ");
		sSql.append("      AND P.CLAVE = '" + clvdep + "' ");
		sSql.append("  GROUP BY PARTIDA , NOMGAS 	 ");
		sSql.append("  ) ORDER BY PARTIDA ");

		return sSql.toString();
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
