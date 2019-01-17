package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDay;

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

@ManagedBean(name = "clasificacionFunMB")
@ViewScoped
public class ClasificacionFunMB extends BaseDirectReport {

	private List<TcPeriodo> listTrimestres;
	private Integer trimestre;
	private Firmas firmas;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;
	
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@PostConstruct
	public void init() {
		listTrimestres = tcPeriodoRepositoy.findByTipoPeriodo(3);
		jasperReporteName = "EdoAnaEjerPreEgreDet";
		endFilename = jasperReporteName + ".pdf";

		if (!listTrimestres.isEmpty()) {
			trimestre = listTrimestres.get(0).getPeriodo();
		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Object[] meses = getMonthsByTrimestre(trimestre);
		
		parameters.put("municipioName", firmas.getCampo1());
		parameters.put("pathImage", this.getUserDetails().getPathImgCab1());
		parameters.put("mesInicial", meses[0]);
		parameters.put("mesFinal", meses[1]);
		parameters.put("lastDay", meses[2]);
		parameters.put("anio", firmas.getCampo3());
		parameters.put("firmaL1", firmas.getL1());
		parameters.put("firmaN1", firmas.getN1());
		parameters.put("firmaL3", firmas.getL3());
		parameters.put("firmaN3", firmas.getN3());
		parameters.put("sql", this.generateSQL(trimestre, this.getUserDetails().getIdSector()));
		
		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer trimestre, Integer sector) {

		StringBuilder sql = new StringBuilder();
		int mesFinal = trimestre * 3;

		String auto = "(";
		String ejpa = "(";
		String redu = "(";
		String ejxpa = "(";
		String ampli = "(";

		for (int y = 1; y <= mesFinal; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.EJXPA1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) AUTO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) EJPA, ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDU ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) EJXPA, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLI, ";

		sql.append("SELECT * FROM (	SELECT  1 GRUP, GRUPO_GASTO, NOMBRE, GASTO, NAME, ")
				.append("SUM(AUTO) AUTO, SUM(EJPA) EJPA, SUM(EJXPA) EJXPA, SUM(AMPLI) AMPLI, SUM(REDU) REDU ")
				.append("FROM ( SELECT  SUBSTR(PA.PROGRAMA,1,2) GRUPO_GASTO, MU.CAMPO6 NOMBRE, SUBSTR(PA.PROGRAMA,1,4) GASTO, MP.CAMPO6 NAME, ")
				.append(auto).append(ejpa).append(ejxpa).append(ampli).append(redu)
				.append(" FROM PASO PA, MUNINEP MU, MUNINEP MP ")
				.append("WHERE 	MP.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,2) = MU.CAMPO7 ")
				.append("AND PA.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,4) = MP.CAMPO7 AND ")
				.append("PA.IDSECTOR = MU.IDSECTOR AND PA.IDSECTOR = ").append(sector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1) != '0' ")
				.append("AND (SUBSTR(PA.PROGRAMA,13,3) BETWEEN 101 AND 113 OR SUBSTR(PA.PROGRAMA,13,3) BETWEEN 201 AND 202) )T1 ")
				.append("GROUP BY GRUPO_GASTO, NOMBRE, GASTO, NAME )T2 UNION ALL SELECT * FROM ( ")
				.append("SELECT  2 GRUP, GRUPO_GASTO, NOMBRE, GASTO, NAME, SUM(AUTO) AUTO, SUM(EJPA) EJPA, SUM(EJXPA) EJXPA, SUM(AMPLI) AMPLI, SUM(REDU) REDU ")
				.append("FROM (SELECT  SUBSTR(PA.PROGRAMA,1,2) GRUPO_GASTO, MU.CAMPO6 NOMBRE, SUBSTR(PA.PROGRAMA,1,4) GASTO, MP.CAMPO6 NAME, ")
				.append(auto).append(ejpa).append(ejxpa).append(ampli).append(redu)
				.append(" FROM PASO PA, MUNINEP MU, MUNINEP MP ")
				.append("WHERE 	MP.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,2) = MU.CAMPO7 ")
				.append("AND PA.IDSECTOR = MU.IDSECTOR AND SUBSTR(PA.PROGRAMA,1,4) = MP.CAMPO7 AND ")
				.append("PA.IDSECTOR = MU.IDSECTOR AND PA.IDSECTOR = ").append(sector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1) != '0' ")
				.append("AND (SUBSTR(PA.PROGRAMA,13,3) BETWEEN 114 AND 115 OR SUBSTR(PA.PROGRAMA,13,3) BETWEEN 203 AND 225) )T1 ")
				.append("GROUP BY GRUPO_GASTO, NOMBRE, GASTO, NAME )T2 ORDER BY GRUP, GRUPO_GASTO, GASTO");

		return sql.toString();
	}

	public Object[] getMonthsByTrimestre(Integer trimestre) {
		Integer mesFinal = trimestre * 3;
		Integer mesInicial = mesFinal - 2;
		Object[] meses = {
				tcMesRepository.findByMes(org.apache.commons.lang3.StringUtils.leftPad(mesInicial.toString(), 2, "0"))
						.getDescripcion(),
				tcMesRepository.findByMes(org.apache.commons.lang3.StringUtils.leftPad(mesFinal.toString(), 2, "0"))
						.getDescripcion(),
				getLastDay(mesFinal) };

		return meses;
	}
	
	public List<TcPeriodo> getListTrimestres() {
		return listTrimestres;
	}

	public void setListTrimestres(List<TcPeriodo> listTrimestres) {
		this.listTrimestres = listTrimestres;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public Firmas getFirmas() {
		return firmas;
	}

	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
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
