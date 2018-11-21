package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009115MB.
 */
@ManagedBean
@ViewScoped
public class RF009115MB extends BaseDirectReport {
	
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_ESTADO_INGRESOSD";
	
	/** The mes. */
	private Integer mes;
	
	/** The firmas. */
	private Integer firmas;
	
	/** The txt. */
	private StreamedContent txt;
	
	/** The tcfirmas. */
	private Firmas tcfirmas;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
    protected ExecutePlService executePlService;

	/** The out. */
	Map<String, Object> out;
	
	/** The stream. */
	InputStream stream = null;
	
	/**
	 * Gets the txt.
	 *
	 * @return the txt
	 */
	public StreamedContent getTxt() {
MapSqlParameterSource parameter = new MapSqlParameterSource();
		
		parameter.addValue("i_usuario", this.getUserDetails().getUsername());
		parameter.addValue("i_query", this.generateQuery(this.getUserDetails().getIdSector(), mes));
		parameter.addValue("i_mes", mes);
		parameter.addValue("i_firmas", firmas);
		
		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);
		
		if(Integer.valueOf(out.get("O_COD_ERROR").toString())>0){
            try {
                stream = new FileInputStream(
                  new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
               } catch (FileNotFoundException e) {
                
                e.printStackTrace();
               }
            txt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
               generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
        }else{
        	generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
        }
		return txt;
	}

	/**
	 * Sets the txt.
	 *
	 * @param txt the new txt
	 */
	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}

	/**
	 * Gets the tcfirmas.
	 *
	 * @return the tcfirmas
	 */
	public Firmas getTcfirmas() {
		return tcfirmas;
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
	 * Sets the tcfirmas.
	 *
	 * @param tcfirmas the new tcfirmas
	 */
	public void setTcfirmas(Firmas tcfirmas) {
		this.tcfirmas = tcfirmas;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009115MB ");		
		jasperReporteName = "RF009_1_15";
		endFilename = jasperReporteName+".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		GemUser user = this.getUserDetails();
		tcfirmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		
		paramsReport.put("idSector", user.getIdSector()); 
		paramsReport.put("year", tcfirmas.getCampo3());
		paramsReport.put("mes", mes); 
		paramsReport.put("NFIRMAS", firmas); 
		paramsReport.put("entidadName", tcfirmas.getCampo1());
		paramsReport.put("entidadRfc", tcfirmas.getCampo2());
		paramsReport.put("imagePath", user.getPathImgCab1());
		paramsReport.put("sql", this.generateQuery(user.getIdSector(), mes));
		if(this.getUserDetails().getIdSector() == 2){
			paramsReport.put("firmaP1", tcfirmas.getL4());
			paramsReport.put("firmaP2", tcfirmas.getL5());
			paramsReport.put("firmaP3", tcfirmas.getL27());
			paramsReport.put("firmaP4", ""); // falta indicar que firma ira aqui
			paramsReport.put("firmaN1", tcfirmas.getN4());
			paramsReport.put("firmaN2", tcfirmas.getN5());
			paramsReport.put("firmaN3", tcfirmas.getN27());
			paramsReport.put("firmaN4", ""); // falta indicar que firma ira aqui
		}else{
			paramsReport.put("firmaP1", tcfirmas.getL1());
			paramsReport.put("firmaP2", tcfirmas.getL3());
			paramsReport.put("firmaP3", tcfirmas.getL4());
			paramsReport.put("firmaP4", ""); // falta indicar que firma ira aqui
			paramsReport.put("firmaN1", tcfirmas.getN1());
			paramsReport.put("firmaN2", tcfirmas.getN3());
			paramsReport.put("firmaN3", tcfirmas.getN4());
			paramsReport.put("firmaN4", ""); // falta indicar que firma ira aqui
		}
		
		return paramsReport;
	}	
	
	/**
	 * Generate query.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the string
	 */
	public String generateQuery(Integer idSector, Integer mes) {
		StringBuilder cargo10 = new StringBuilder();
		StringBuilder abono10 = new StringBuilder();
		StringBuilder cargo50 = new StringBuilder();
		StringBuilder abono50 = new StringBuilder();
		StringBuilder ingreso = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			cargo10.append(" NVL(DI.CARGOS_" + i + ",0) + ");
			abono10.append(" NVL(DI.ABONOS_" + i + ",0) + ");
			cargo50.append(" NVL(CI.CARGOS_" + i + ",0) + ");
			abono50.append(" NVL(CI.ABONOS_" + i + ",0) + ");
			ingreso.append(" NVL(II.AUTOI_" + i + ",0) + ");
			i++;
		}
		query.append("SELECT CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, NOMCTA, ESTIMADA, MODIFICADO_MES, RECAUDADO_MES, ")
				.append("MODIFICADO_ACUMULADO, RECAUDADO_ACUMULADO, (RECAUDADO_ACUMULADO - MODIFICADO_ACUMULADO) VARABS, ")
				.append("CASE WHEN MODIFICADO_ACUMULADO = 0 THEN 0 ")
				.append("ELSE ABS(((RECAUDADO_ACUMULADO - MODIFICADO_ACUMULADO) / MODIFICADO_ACUMULADO) * 100) END ABSOLUTO ")
				.append("FROM (SELECT DI.CUENTA, DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA, DI.NOMCTA, ")
				.append("(NVL(DI.SALINI,0) + ((" + abono10.substring(0, abono10.length() - 2) + ") - ("
						+ cargo10.substring(0, cargo10.length() - 2) + "))) ESTIMADA, ")
				.append("NVL(II.AUTOI_" + mes + ",0) MODIFICADO_MES, ")
				.append("(NVL(CI.ABONOS_" + mes + ",0) - NVL(CI.CARGOS_" + mes + ",0)) RECAUDADO_MES, ")
				.append("(" + ingreso.substring(0, ingreso.length() - 2) + ") MODIFICADO_ACUMULADO, ")
				.append("((" + abono50.substring(0, abono50.length() - 2) + ") - ("
						+ cargo50.substring(0, cargo50.length() - 2) + ")) RECAUDADO_ACUMULADO")
				.append(" FROM CUENTA AS DI ")
				.append("INNER JOIN CUENTA AS CI ON CI.CUENTA= '8150' AND CI.SCTA=DI.SCTA AND CI.SSCTA=DI.SSCTA ")
				.append("AND CI.SSSCTA=DI.SSSCTA AND CI.SSSSCTA=DI.SSSSCTA AND CI.IDSECTOR = DI.IDSECTOR ")
				.append("LEFT JOIN INGRESO AS II ON DI.CUENTA = II.CUENTA AND DI.SCTA = II.SCTA AND DI.SSCTA=II.SSCTA ")
				.append("AND DI.SSSCTA = II.SSSCTA AND DI.SSSSCTA=II.SSSSCTA AND DI.IDSECTOR=II.IDSECTOR ")
				.append("WHERE (DI.CUENTA='8110') AND DI.SCTA <> '' AND DI.SSCTA <> '' AND DI.SSSCTA <> '' ")
				.append("AND DI.SSSSCTA <> '' AND DI.IDSECTOR=" + idSector)
				.append(" ORDER BY DI.CUENTA,DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA ) T1");

		return query.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
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
	public Integer getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Integer firmas) {
		this.firmas = firmas;
	}
}
