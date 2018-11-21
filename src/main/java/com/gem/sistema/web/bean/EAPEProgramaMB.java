package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 *
 */
@ManagedBean(name = "eAPEProgramaMB")
@ViewScoped
public class EAPEProgramaMB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private String mes;
	private String funcion;
	private String nameFuncion;
	private Firmas firmas;
	private List<TcMes> listTcMes;
	private List<Muninep> listMuninep;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcmesRepository;

	@ManagedProperty("#{muniNepRepository}")
	private MuniNepRepository muninepRepository;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	

	@PostConstruct
	public void init() {
		jasperReporteName = "RF009122_EdoAvancePEfin";
		endFilename = "EAPE_Funcion.pdf";
		listTcMes = tcmesRepository.findAll();
		listMuninep = muninepRepository.getListPrograma(this.getUserDetails().getIdSector());
		if (!CollectionUtils.isEmpty(listTcMes)) {
			mes = listTcMes.get(0).getMes();
		}
		if (!CollectionUtils.isEmpty(listMuninep)) {
			funcion = listMuninep.get(0).getCampo7();
			nameFuncion = listMuninep.get(0).getCampo6();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		firmas = firmasRepository.findAllByIdsector(sector);
		parameters.put("p_IdSector", sector);
		parameters.put("p_Mes", Integer.valueOf(mes));
		parameters.put("p_NombreFin", nameFuncion);
		parameters.put("p_NumFin", funcion);
		parameters.put("p_Mun", firmas.getCampo1());
		if (sector == 2) {
			parameters.put("p_L1", firmas.getL4());
			parameters.put("p_N1", firmas.getN4());
			parameters.put("p_L2", firmas.getL5());
			parameters.put("p_N2", firmas.getN5());
			parameters.put("p_L3", firmas.getL27());
			parameters.put("p_N3", firmas.getN27());
		} else {
			parameters.put("p_L1", firmas.getL1());
			parameters.put("p_N1", firmas.getN1());
			parameters.put("p_L2", firmas.getL2());
			parameters.put("p_N2", firmas.getN2());
			parameters.put("p_L3", firmas.getL3());
			parameters.put("p_N3", firmas.getN3());
		}
		parameters.put("p_Img", this.getUserDetails().getPathImgCab1());
		parameters.put("p_Query", this.generateQuery(sector, Integer.valueOf(mes), funcion));
		parameters.put("p_An", firmas.getCampo3());
		parameters.put("p_LastDay", getLastDayByAnoEmp(Integer.valueOf(mes), firmas.getCampo3()));
		parameters.put("p_DescrpMes", tcmesRepository.findByMes(mes).getDescripcion());
		parameters.put("p_Dependencia", firmas.getCampo1());

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateName() {
		nameFuncion = muninepRepository.findFirstByCampo7AndIdsector(funcion, this.getUserDetails().getIdSector()).getCampo6();
	}
	
	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());

			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}
	
	public String generateQuery(Integer idsector, Integer mes, String xdire) {
		StringBuilder sSql = new StringBuilder();
		StringBuilder ejempa = new StringBuilder();
		StringBuilder ejxpa = new StringBuilder();
		StringBuilder toeje = new StringBuilder();
		StringBuilder ampli = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder compro = new StringBuilder();

		sSql.append("SELECT T1.PARTIDA, ").append("T1.DESCRIPCION,").append("T1.A APROBADO,").append("T1.F AMPLIACION,")
				.append("T1.G REDUCCION,").append("(T1.A+T1.F-T1.G) MODIFICADO,").append("(T1.CPT)COMPROMETIDO,")
				.append("T1.C DEVENGADO,").append("T1.B PAGADO,").append("T1.D EJERCIDO,")
				.append("((T1.A +T1.F-T1.G)-D) POR_EJERCER").append(" FROM (SELECT P.PARTIDA PARTIDA,")
				.append("N.NOMGAS DESCRIPCION,")
				.append("SUM(P.AUTO1_1+P.AUTO1_2+P.AUTO1_3+P.AUTO1_4+P.AUTO1_5+P.AUTO1_6+P.AUTO1_7+P.AUTO1_8+P.AUTO1_9+P.AUTO1_10+P.AUTO1_11+P.AUTO1_12)A,");

		for (int i = 1; i <= mes; i++) {

			ejempa.append(" P.EJPA1_" + i + "+");
			ejxpa.append(" P.EJXPA1_" + i + "+");
			toeje.append(" P.TOEJE1_" + i + "+");
			ampli.append(" P.AMPLI1_" + i + "+");
			redu.append(" P.REDU1_" + i + "+");
			compro.append(" P.COMPRO1_" + i + "+");

		}

		sSql.append(" SUM( ").append(ejempa.substring(1, ejempa.length() - 1)).append(")B,").append(" SUM( ")
				.append(ejxpa.substring(1, ejxpa.length() - 1)).append(")C,").append(" SUM( ")
				.append(toeje.substring(1, toeje.length() - 1)).append(")D,").append(" SUM( ")
				.append(ampli.substring(1, ampli.length() - 1)).append(")F,").append(" SUM( ")
				.append(redu.substring(1, redu.length() - 1)).append(")G,").append(" SUM( ")
				.append(compro.substring(1, compro.length() - 1)).append(")CPT ");

		sSql.append(" FROM").append(" PASO P JOIN NATGAS N ").append(" ON N.CLVGAS= P.PARTIDA ")
				.append(" AND N.IDSECTOR=P.IDSECTOR ").append(" WHERE ").append(" P.IDSECTOR = " + idsector)
				.append(" AND SUBSTR(P.PROGRAMA,1,8)='" + xdire + "'").append(" GROUP BY P.PARTIDA,N.NOMGAS)")
				.append("T1");
		return sSql.toString();
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getNameFuncion() {
		return nameFuncion;
	}

	public void setNameFuncion(String nameFuncion) {
		this.nameFuncion = nameFuncion;
	}

	public List<TcMes> getListTcMes() {
		return listTcMes;
	}

	public void setListTcMes(List<TcMes> listTcMes) {
		this.listTcMes = listTcMes;
	}

	public List<Muninep> getListMuninep() {
		return listMuninep;
	}

	public void setListMuninep(List<Muninep> listMuninep) {
		this.listMuninep = listMuninep;
	}

	public Firmas getFirmas() {
		return firmas;
	}

	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	public TcMesRepository getTcmesRepository() {
		return tcmesRepository;
	}

	public void setTcmesRepository(TcMesRepository tcmesRepository) {
		this.tcmesRepository = tcmesRepository;
	}

	public MuniNepRepository getMuninepRepository() {
		return muninepRepository;
	}

	public void setMuninepRepository(MuniNepRepository muninepRepository) {
		this.muninepRepository = muninepRepository;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
