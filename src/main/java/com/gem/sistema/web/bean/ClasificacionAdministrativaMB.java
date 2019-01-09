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
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "clasificacionAdministrativaMB")
@ViewScoped
public class ClasificacionAdministrativaMB extends BaseDirectReport {

	private List<TcPeriodo> listTrimestres;
	private Integer trimestre;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "ClasificacionAdministrativa";
		endFilename = jasperReporteName + ".pdf";
		listTrimestres = tcPeriodoRepositoy.findByTipoPeriodo(3);

		if (!listTrimestres.isEmpty()) {
			trimestre = listTrimestres.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Object[] meses = getMonthsByTrimestre(trimestre);

		parameters.put("municipio", firmas.getCampo1());
		parameters.put("image", this.getUserDetails().getPathImgCab1());
		parameters.put("firstMonth", meses[0]);
		parameters.put("lastMonth", meses[1]);
		parameters.put("lastDay", meses[2]);
		parameters.put("year", firmas.getCampo3());
		parameters.put("firmaL1", firmas.getL1());
		parameters.put("firmaN1", firmas.getN1());
		parameters.put("firmaL2", firmas.getL3());
		parameters.put("firmaN2", firmas.getN3());
		parameters.put("sql", this.generateSql(trimestre, this.getUserDetails().getIdSector()));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSql(Integer trimestre, Integer idSector) {
		StringBuilder sql = new StringBuilder();
		int mesFinal = trimestre * 3;

		String pagado = "SUM(";
		String aprobado = "SUM(";
		String reduccion = "SUM(";
		String devengado = "SUM(";
		String ampliacion = "SUM(";

		for (int y = 1; y <= mesFinal; y++) {
			pagado = pagado + " PA.EJPA1_" + y + " +";
			aprobado = aprobado + " PA.AUTO1_" + y + " +";
			reduccion = reduccion + " PA.REDU1_" + y + " +";
			devengado = devengado + " PA.EJXPA1_" + y + " +";
			ampliacion = ampliacion + " PA.AMPLI1_" + y + " +";
		}

		aprobado = aprobado.substring(0, aprobado.length() - 2) + " ) APROBADO, ";
		pagado = pagado.substring(0, pagado.length() - 2) + " ) PAGADO ";
		reduccion = reduccion.substring(0, reduccion.length() - 2) + " ) REDUCCIONES, ";
		devengado = devengado.substring(0, devengado.length() - 2) + " ) DEVENGADO, ";
		ampliacion = ampliacion.substring(0, ampliacion.length() - 2) + " ) AMPLIACION, ";

		sql.append("SELECT 1 GRUP, T1.CLAVE, T1.NOMBRE, T1.APROBADO, (T1.AMPLIACION - T1.REDUCCIONES) AMPLI_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION - T1.REDUCCIONES) MODIFICADO, T1.DEVENGADO, T1.PAGADO, ")
				.append("(T1.APROBADO + T1.AMPLIACION - T1.REDUCCIONES)- T1.DEVENGADO SUBEJERCICIO ")
				.append("FROM (SELECT CA.CLAVE,CA.NOMBRE, ").append(aprobado).append(ampliacion).append(reduccion)
				.append(devengado).append(pagado)
				.append("FROM PASO PA INNER JOIN CATDGM CA ON SUBSTR(PA.CLAVE,1,3)=CA.CLAVE ")
				.append("WHERE PA.IDSECTOR= ").append(idSector).append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY CA.NOMBRE,CA.CLAVE ORDER BY CA.CLAVE,CA.NOMBRE ASC) T1 UNION ALL ")
				.append("SELECT 2 GRUP, T2.CLAVE, T2.NOMBRE, T2.APROBADO, (T2.AMPLIACION - T2.REDUCCIONES) AMPLI_REDU, ")
				.append("(T2.APROBADO + T2.AMPLIACION - T2.REDUCCIONES) MODIFICADO, T2.DEVENGADO, T2.PAGADO, ")
				.append("(T2.APROBADO + T2.AMPLIACION - T2.REDUCCIONES)- T2.DEVENGADO SUBEJERCICIO ")
				.append("FROM (SELECT CA.CLAVE,CA.NOMBRE, ").append(aprobado).append(ampliacion).append(reduccion)
				.append(devengado).append(pagado)
				.append("FROM PASO PA INNER JOIN CATDGM CA ON SUBSTR(PA.CLAVE,1,3)=CA.CLAVE ")
				.append("WHERE PA.IDSECTOR=").append(idSector).append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='115' ")
				.append("GROUP BY CA.NOMBRE,CA.CLAVE ORDER BY CA.CLAVE,CA.NOMBRE ASC) T2 ORDER BY GRUP");

		return sql.toString();
	}

	public Object[] getMonthsByTrimestre(Integer trimestre) {
		Integer mesFinal = trimestre * 3;
		Integer mesInicial = mesFinal - 2;
		Object[] meses = { tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesInicial).getDescripcion(),
				tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesFinal).getDescripcion(), getLastDay(mesFinal) };

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

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

}
