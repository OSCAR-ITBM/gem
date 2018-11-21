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
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010113MB.
 */
@ManagedBean(name = "rF0010113MB")
@ViewScoped
public class RF0010113MB extends BaseDirectReport {
	

	/** The mes. */
	private String mes;
	
	/** The saldo. */
	private String saldo;
	
	/** The list mes. */
	private List<TcMes> listMes;
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/**Servicio  para validacion de mes aprturado, polizas, afectacion*/
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	

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
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo the new saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
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
	
	

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		firmas = firmasRepository.findAllByIdsector(sector);
		parameters.put("mesName", tcMesRepository.findByMes(mes).getDescripcion());
		parameters.put("lastDay", getLastDayByAnoEmp(Integer.valueOf(mes),firmas.getCampo3()));
		parameters.put("year", firmas.getCampo3());
		parameters.put("imagePath", this.getUserDetails().getPathImgCab1());
		parameters.put("query", this.generateQuery(Integer.valueOf(mes), sector, saldo.equals("S")? 1:0));
		parameters.put("municipio", firmas.getCampo1());
		parameters.put("firmaP1", (sector == 1)? firmas.getL4() : firmas.getL4());
		parameters.put("firmaP2", (sector == 1)? firmas.getL5() : firmas.getL5());
		parameters.put("firmaP3", (sector == 1)? firmas.getL3() : firmas.getL27());
		parameters.put("firmaN1", (sector == 1)? firmas.getN4() : firmas.getN4());
		parameters.put("firmaN2", (sector == 1)? firmas.getN5() : firmas.getN5());
		parameters.put("firmaN3", (sector == 1)? firmas.getN3() : firmas.getN3());
		
		return parameters;
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "RF0010_1_13";
		endFilename = jasperReporteName + ".pdf";
		
		listMes = tcMesRepository.findAll();
		if(!CollectionUtils.isEmpty(listMes)) 
			mes = listMes.get(0).getMes();
		
		
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
	 * Generate query.
	 *
	 * @param mes the mes
	 * @param idSector the id sector
	 * @param saldosCero the saldos cero
	 * @return the string
	 */
	public String generateQuery(Integer mes, Integer idSector, Integer saldosCero) {

		StringBuilder sQuery = new StringBuilder();
		StringBuilder sCargosAnt = new StringBuilder();
		StringBuilder sAbonosAnt = new StringBuilder();

		sQuery.append(
				"SELECT CUENTA, NOMCTA, SALDOINICIAL, CARGOS_MES, ABONOS_MES,(SALDOINICIAL + CARGOS_MES - ABONOS_MES) SALDOFINAL,(SALDOINICIAL - (SALDOINICIAL + CARGOS_MES - ABONOS_MES)) FLUJO FROM ( SELECT CUENTA, NOMCTA, CASE WHEN NATCTA IN('A') THEN CASE WHEN CUENTA IN ('1115','1161') THEN ABS(SALINI + CARGOS_ANT - ABONOS_ANT) ELSE ABS(SALINI - CARGOS_ANT + ABONOS_ANT) END ELSE ABS(SALINI + CARGOS_ANT - ABONOS_ANT) END SALDOINICIAL, CARGOS_MES, ABONOS_MES FROM ( SELECT CU.CUENTA, MA.NATCTA, CU.NOMCTA, CU.SALINI,");

		if (mes > 1) {
			for (int i = 1; i < mes; i++) {
				sCargosAnt.append(" CU.CARGOS_" + i + " +");
				sAbonosAnt.append(" CU.ABONOS_" + i + " +");
			}
		}else{
			sCargosAnt.append("  '0'  ");
			sAbonosAnt.append("  '0'  ");
		}

		sQuery.append(sCargosAnt.substring(0, sCargosAnt.length() - 1) + "CARGOS_ANT,")
				.append(sAbonosAnt.substring(0, sAbonosAnt.length() - 1) + "ABONOS_ANT,")
				.append(" CU.CARGOS_" + mes + " CARGOS_MES, CU.ABONOS_" + mes + " ABONOS_MES ")
				.append(" FROM CUENTA AS CU, MAYCTA AS MA WHERE CU.IDSECTOR=" + idSector
						+ " AND CU.CUENTA = MA.CUENTA AND CU.SCTA = '' AND CU.SSCTA = '' AND CU.SSSCTA = '' AND CU.SSSSCTA = '' AND CU.CUENTA <= 1300 AND SUBSTR(CU.CUENTA,2,3) <> '000'  AND CU.NOTCTA <> 3 ) T1 )T2");
		if (saldosCero == 0) {
			sQuery.append(" WHERE SALDOINICIAL > 0 OR CARGOS_MES > 0 OR ABONOS_MES > 0 OR (SALDOINICIAL + CARGOS_MES - ABONOS_MES) > 0 OR (SALDOINICIAL - (SALDOINICIAL + CARGOS_MES - ABONOS_MES)) >0");
		}
		return sQuery.toString();
	}
	
	
	/**Metodo de validacion al descargar el PDF*/
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
	}

}
