package com.gem.sistema.web.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

@ManagedBean(name = "ecpedgMB")
@ViewScoped
public class EcpedgMB extends BaseDirectReport {

	private List<TcPeriodo> listMeses;
	private Integer mesSelected;
	private Firmas firmas;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@PostConstruct
	public void init() {
		listMeses = tcPeriodoRepositoy.findByTipoPeriodo(1);
		jasperReporteName = "ECPEDG";
		endFilename = jasperReporteName + ".pdf";

		if (!listMeses.isEmpty()) {
			mesSelected = listMeses.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		TcPeriodo mes = tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesSelected);

		parameters.put("pImage", this.getUserDetails().getPathImgCab1());
		parameters.put("pMunicipio", firmas.getCampo1());
		parameters.put("pMesFinal", mes.getDescripcion());
		parameters.put("pLastDay", getLastDayByAnoEmp(mesSelected, firmas.getCampo3()));
		parameters.put("pAnio", firmas.getCampo3());
		parameters.put("pL1", firmas.getL1());
		parameters.put("pN1", firmas.getN1());
		parameters.put("pL2", firmas.getL2());
		parameters.put("pN2", firmas.getN2());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pSql", this.generateSQL(this.getUserDetails().getIdSector(), mesSelected));

		return parameters;
	}

	private String generateSQL(Integer sector, Integer mes) {

		StringBuilder sql = new StringBuilder();

		String auto = "(";
		String redu = "(";
		String ampl = "(";
		String toeje = "SUM(";

		for (int y = 1; y <= mes; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ampl = ampl + " PA.AMPLI1_" + y + " +";
			toeje = toeje + "PA.TOEJE1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + ")";
		redu = redu.substring(0, redu.length() - 2) + ")";
		ampl = ampl.substring(0, ampl.length() - 2) + ")";
		toeje = toeje.substring(0, toeje.length() - 2) + ") EJERACUM ";

		sql.append("SELECT DEPGEN, NOMBRE, PARTIDA, CONCEPTO, APROBADO, MODIFICADO, EJERCIDO, MODIACUM, EJERACUM, ")
				.append("(MODIACUM-EJERACUM) VARABS, ")
				.append("DECODE(MODIACUM-EJERACUM, 0.0, 0, (((MODIACUM-EJERACUM)/ MODIACUM) * 100)) VAR ")
				.append("FROM ( SELECT  GM.CLAVE DEPGEN, GM.NOMBRE, PA.PARTIDA, NA.NOMGAS CONCEPTO, ")
				.append("SUM(PA.AUTO1_1+PA.AUTO1_2+PA.AUTO1_3+PA.AUTO1_4+PA.AUTO1_5+PA.AUTO1_6+")
				.append("PA.AUTO1_7+PA.AUTO1_8+PA.AUTO1_9+PA.AUTO1_10+PA.AUTO1_11+PA.AUTO1_12)APROBADO, ")
				.append("SUM(PA.AUTO1_").append(mes).append(" + PA.AMPLI1_").append(mes).append(" - PA.REDU1_")
				.append(mes).append(") MODIFICADO, ").append("SUM(PA.TOEJE1_").append(mes).append(") EJERCIDO, ")
				.append("SUM(").append(auto).append("+").append(ampl).append("-").append(redu).append(") MODIACUM, ")
				.append(toeje)
				.append("FROM PASO PA INNER JOIN NATGAS NA ON PA.PARTIDA=NA.CLVGAS AND PA.IDSECTOR=NA.IDSECTOR ")
				.append("INNER JOIN CATDAA CA ON SUBSTR(PA.CLAVE,4,3)=CA.CLAVE ")
				.append("INNER JOIN CATDGM GM ON SUBSTR(PA.CLAVE,1,3)=GM.CLAVE ").append("WHERE PA.IDSECTOR= ")
				.append(sector)
				.append(" GROUP BY GM.CLAVE, GM.NOMBRE, PA.PARTIDA, NA.NOMGAS )T1 ORDER BY DEPGEN, T1.PARTIDA ASC ");

		return sql.toString();
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getMesSelected() {
		return mesSelected;
	}

	public void setMesSelected(Integer mesSelected) {
		this.mesSelected = mesSelected;
	}

	public Firmas getFirmas() {
		return firmas;
	}

	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

}
