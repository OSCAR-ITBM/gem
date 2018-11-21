package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

// TODO: Auto-generated Javadoc
/**
 * The Class RF009355MB.
 */
@ManagedBean(name = "rF009355MB")
@ViewScoped
public class RF009355MB extends BaseDirectReport {
	
	/** The mes. */
	private String mes;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The firmas. */
	private Firmas firmas;

	/** The tc mes repository. */
	// Indicamos el repositorio de donde se traeran los datos
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

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

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "RF009355";
		endFilename = jasperReporteName + ".pdf";

		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes))
			mes = listMes.get(0).getMes();

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters;
		TcMes descripMes = tcMesRepository.findByMes(mes);
		parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		
		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pMun", firmas.getCampo1());
		parameters.put("pClveMun", conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getClave());
		parameters.put("pAnio", firmas.getCampo3());
		parameters.put("pLastDay", getLastDayByAnoEmp(Integer.valueOf(mes),firmas.getCampo3()));
		parameters.put("pMesDescrip", descripMes.getDescripcion());
		parameters.put("pL1", firmas.getL27());
		parameters.put("pN1", firmas.getN27());
		parameters.put("pL2", firmas.getL5());
		parameters.put("pN2", firmas.getN5());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pSsql", this.generaQuery(this.getUserDetails().getIdSector(), Integer.valueOf(mes)));
		return parameters;
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
	 * Genera query.
	 *
	 * @param idsector the idsector
	 * @param mes the mes
	 * @return the string
	 */
	public String generaQuery(Integer idsector, Integer mes) {

		StringBuilder sSql = new StringBuilder();
		StringBuilder acargos = new StringBuilder();
		StringBuilder aabonos = new StringBuilder();
		StringBuilder agcargos = new StringBuilder();
		StringBuilder agabonos = new StringBuilder();
		StringBuilder dcargos = new StringBuilder();
		StringBuilder dabonos = new StringBuilder();
		StringBuilder depcargos = new StringBuilder();
		StringBuilder depabonos = new StringBuilder();
		StringBuilder mavicigargos = new StringBuilder();
		StringBuilder maviciabonos = new StringBuilder();

		sSql.append("SELECT T2.CUENTA,").append("T2.NOMBRE,").append(" T2.AYUREC,").append("T2.AGUAREC,")
				.append(" T2.DIFREC,").append(" T2.DEPREC,").append("T2.MAVREC,")
				.append(" (T2.AYUREC+T2.AGUAREC+T2.DIFREC+T2.DEPREC+T2.MAVREC)TOTAL").append(" FROM ")
				.append("(SELECT T1.CUENTA,").append("T1.NOMBRE,").append("NVL((T1.AABONOS-T1.ACARGOS),0)AYUREC,")
				.append("NVL((T1.AGABONOS-T1.AGCARGOS),0)AGUAREC,").append("NVL((T1.DABONOS-T1.DCARGOS),0) DIFREC,")
				.append("NVL((T1.DEPABONOS-T1.DEPCARGOS),0)DEPREC,").append("NVL((T1.MAVICIABONOS-T1.MAVICICARGOS),0)MAVREC")
				.append(" FROM ")
				.append("(SELECT C.CUENTA ||' '|| SUBSTR(C.SCTA,7,4)||' '||SUBSTR(C.SSCTA,13,3)||' '||SUBSTR(C.SSSCTA,2,3)||' '||SUBSTR(C.SSSSCTA,1,3) CUENTA, ")
				.append(" C.NOMCTA NOMBRE,");

		for (int i = 1; i <= mes; i++) {

			acargos.append(" I.AYTTOCARGOS_" + i + "+");
			aabonos.append(" I.AYTTOABONOS_" + i + "+");
			agcargos.append(" I.AGUACARGOS_" + i + "+");
			agabonos.append(" I.AGUAABONOS_" + i + "+");
			dcargos.append(" I.DIFCARGOS_" + i + "+");
			dabonos.append(" I.DIFABONOS_" + i + "+");
			depcargos.append(" I.DEPORTECARGOS_" + i + "+");
			depabonos.append(" I.DEPORTEABONOS_" + i + "+");
			mavicigargos.append(" I.MAVICICARGOS_" + i + "+");
			maviciabonos.append(" I.MAVICIABONOS_" + i + "+");

		}

		sSql.append(" (" + acargos.substring(1, acargos.length() - 1)).append(") ACARGOS,")
				.append(" (" + aabonos.substring(1, aabonos.length() - 1)).append(") AABONOS,")
				.append(" (" + agcargos.substring(1, agcargos.length() - 1)).append(")AGCARGOS,")
				.append(" (" + agabonos.substring(1, agabonos.length() - 1)).append(")AGABONOS,")
				.append(" (" + dcargos.substring(1, dcargos.length() - 1)).append(") DCARGOS,")
				.append(" (" + dabonos.substring(1, dabonos.length() - 1)).append(") DABONOS,")
				.append(" (" + depcargos.substring(1, depcargos.length() - 1)).append(") DEPCARGOS,")
				.append(" (" + depabonos.substring(1, depabonos.length() - 1)).append(") DEPABONOS,")
				.append(" (" + mavicigargos.substring(1, mavicigargos.length() - 1)).append(")MAVICICARGOS,")
				.append(" (" + maviciabonos.substring(1, maviciabonos.length() - 1)).append(") MAVICIABONOS");
		sSql.append(" FROM CUENTA C")
				.append(" LEFT JOIN INTEGRADOI I ON C.CUENTA=I.CUENTA AND C.SCTA = I.SCTA AND C.SSCTA=I.SSCTA AND C.SSSCTA = I.SSSCTA AND C.SSSSCTA=I.SSSSCTA AND C.IDSECTOR=I.IDSECTOR")
				.append(" WHERE C.IDSECTOR=" + idsector).append(" AND C.CUENTA = '8150' ORDER BY C.CUENTA,").append("C.SCTA,")
				.append("C.SSCTA,").append("C.SSSCTA,").append(" C.SSSSCTA) T1)T2");

		LOGGER.info("QUERY,{}", sSql.toString());
		return sSql.toString();
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

}
