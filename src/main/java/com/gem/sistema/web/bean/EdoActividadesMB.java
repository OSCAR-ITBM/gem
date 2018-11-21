package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoActividadesMB.
 */
@ManagedBean
@ViewScoped
public class EdoActividadesMB extends BaseDirectReport {
	
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_EDO_ACTIVIDADES";
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The listquery. */
	private List<String> listquery;
	
	/** The file txt. */
	private StreamedContent fileTxt;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	/**
	 * Gets the listquery.
	 *
	 * @return the listquery
	 */
	public List<String> getListquery() {
		return listquery;
	}

	/**
	 * Sets the listquery.
	 *
	 * @param listquery the new listquery
	 */
	public void setListquery(List<String> listquery) {
		this.listquery = listquery;
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
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "edoactividadesc";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();
		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
	}

	/**
	 * Generate querys.
	 *
	 * @param mes the mes
	 * @param sector the sector
	 * @return the list
	 */
	public List<String> generateQuerys(Integer mes, Integer sector) {
		List<String> list = new ArrayList<String>();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder cargo = new StringBuilder();
		StringBuilder abono = new StringBuilder();
		StringBuilder queryEgresos = new StringBuilder();
		StringBuilder queryIngresos = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			cargos.append(" NVL(CARGOS_" + i + ",0) + ");
			abonos.append(" NVL(ABONOS_" + i + ",0) + ");
			cargo.append(" NVL(C.CARGOS_" + i + ",0) + ");
			abono.append(" NVL(C.ABONOS_" + i + ",0) + ");
			i++;
		}
		queryEgresos.append("SELECT	CLVGAS, ")
				.append("DECODE(CLVGAS,'1000','5100','2000','5100','3000','5100','4000','5200','5000','5600','6000','5700','8000','5300','9000','5400','')")
				.append("||' '||NOMGAS NOMBRE, MES, ACUMULADO ").append("FROM(SELECT CLVGAS, NOMGAS, ")
				.append("SUM((" + cargo.substring(0, cargo.length() - 2) + ") - ("
						+ abono.substring(0, abono.length() - 2) + ") + NVL(C.SALINI,0)) ACUMULADO, ")
				.append("SUM(NVL(C.CARGOS_" + mes + ",0) - NVL(C.ABONOS_" + mes + ",0) + NVL(C.SALINI,0)) MES ")
				.append("FROM NATGAS N LEFT JOIN CUENTA C ON N.CLVGAS = SUBSTR(C.SSSCTA,1,1)||'000' ")
				.append("AND C.CUENTA IN ('5100','5200', '5300','5600','5700') AND C.IDSECTOR=N.IDSECTOR ")
				.append("WHERE N.IDSECTOR=" + sector + " AND SUBSTR(N.CLVGAS,2,3)='000' ")
				.append("GROUP BY CLVGAS, NOMGAS ) T1 ").append("WHERE CLVGAS <> '7000' ORDER BY CLVGAS");

		queryIngresos
				.append("SELECT	1,SUBSTR(CU.CUENTA,1,3)||'0' , MA.NOMMAY, SUM(("
						+ abonos.substring(0, abonos.length() - 2) + ") - (" + cargos.substring(0, cargos.length() - 2)
						+ ") + NVL(SALINI,0)) ACUMULADO,SUM((ABONOS_" + mes + " - CARGOS_" + mes
						+ ") + NVL(SALINI,0)) MENSUAL FROM CUENTA CU, MAYCTA MA WHERE SUBSTR(CU.CUENTA,1,3)||'0'=MA.CUENTA AND SUBSTR(CU.CUENTA,1,2) = '41' AND CU.SSSCTA <> '' AND CU.IDSECTOR= "
						+ sector + " GROUP BY SUBSTR(CU.CUENTA,1,3)||'0', MA.NOMMAY ")
				.append("UNION SELECT	2,SUBSTR(CU.CUENTA,1,3)||'0' , MA.NOMMAY, SUM(("
						+ abonos.substring(0, abonos.length() - 2) + ") - (" + cargos.substring(0, cargos.length() - 2)
						+ ") + NVL(SALINI,0)) ACUMULADO,SUM((ABONOS_" + mes + " - CARGOS_" + mes
						+ ") + NVL(SALINI,0)) MENSUAL FROM CUENTA CU, MAYCTA MA WHERE SUBSTR(CU.CUENTA,1,3)||'0'=MA.CUENTA AND SUBSTR(CU.CUENTA,1,2) = '43' AND CU.SSSCTA <> '' AND CU.IDSECTOR= "
						+ sector + " GROUP BY SUBSTR(CU.CUENTA,1,3)||'0', MA.NOMMAY ")
				.append("UNION SELECT	3,SUBSTR(CU.CUENTA,1,3)||'0' , MA.NOMMAY, SUM(("
						+ abonos.substring(0, abonos.length() - 2) + ") - (" + cargos.substring(0, cargos.length() - 2)
						+ ") + NVL(SALINI,0)) ACUMULADO,SUM((ABONOS_" + mes + " - CARGOS_" + mes
						+ ") + NVL(SALINI,0)) MENSUAL FROM CUENTA CU, MAYCTA MA WHERE SUBSTR(CU.CUENTA,1,3)||'0'=MA.CUENTA AND SUBSTR(CU.CUENTA,1,2) = '42' AND CU.SSSCTA <> '' AND CU.IDSECTOR= "
						+ sector + " GROUP BY SUBSTR(CU.CUENTA,1,3)||'0', MA.NOMMAY ORDER BY 1,2");
		list.add(queryEgresos.toString());
		list.add(queryIngresos.toString());
		return list;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		listquery = generateQuerys(Integer.valueOf(mes), this.getUserDetails().getIdSector());

		parameters.put("lastDayOfMonth", getLastDayByAnoEmp(Integer.valueOf(mes),firmas.getCampo3()));
		parameters.put("mesName", tcMesRepository.findByMes(mes).getDescripcion());
		parameters.put("year", conctbRepository.findByIdsector(getUserDetails().getIdSector()).getAnoemp());
		parameters.put("pathImage", getUserDetails().getPathImgCab1());
		parameters.put("queryEgresos", listquery.get(0));
		parameters.put("queryIngresos", listquery.get(1));
		parameters.put("usuario", getUserDetails().getUsername());
		parameters.put("firmaP1", firmas.getL4());
		parameters.put("firmaP2", firmas.getL5());
		parameters.put("firmaP3", firmas.getL27());
		parameters.put("firmaN1", firmas.getN4());
		parameters.put("firmaN2", firmas.getN5());
		parameters.put("firmaN3", firmas.getN27());
		parameters.put("entidadName", firmas.getCampo1());
		parameters.put("entidadRfc", firmas.getCampo2());

		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {

		return null;
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
		listquery = generateQuerys(Integer.valueOf(mes), this.getUserDetails().getIdSector());

		parameter.addValue("i_mes", Integer.valueOf(mes));
		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameter.addValue("i_queryIng", listquery.get(1));
		parameter.addValue("i_queryEgr", listquery.get(0));

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

}
