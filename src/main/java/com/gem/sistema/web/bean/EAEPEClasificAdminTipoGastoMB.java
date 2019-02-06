package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

@ManagedBean(name = "eAEPEClasificAdminTipoGastoMB")
@ViewScoped
public class EAEPEClasificAdminTipoGastoMB extends BaseDirectReport {
	private List<TcPeriodo> listMeses;
	private Integer mes;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "clasAdmin";
		endFilename = jasperReporteName + ".pdf";

		listMeses = periodoRepositoy.findByTipoPeriodo(1);

		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
		}
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		TcPeriodo mesSelected = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		Firmas firmas = firmasRepository.findAllByIdsector(idSector);

		parameters.put("pNameMun", firmas.getCampo1());
		parameters.put("pLastDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));
		parameters.put("pMesFinal", mesSelected.getDescripcion());
		parameters.put("pAnio", conctb.getAnoemp());
		parameters.put("pImage", this.getUserDetails().getPathImgCab1());
		parameters.put("pL1", firmas.getL1());
		parameters.put("pN1", firmas.getN1());
		parameters.put("pL2", firmas.getL2());
		parameters.put("pN2", firmas.getN2());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pSql", this.generateSQL(idSector, mes));

		return parameters;
	}

	private String generateSQL(Integer idSector, Integer mes) {
		StringBuilder sql = new StringBuilder();

		String auto = "SUM(";
		String redu = "SUM(";
		String ejpa = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";
		String toeje = "SUM(";
		String compro = "SUM(";

		for (int y = 1; y <= mes; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			ejxpa = ejxpa + " PA.EJXPA1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
			toeje = toeje + " PA.TOEJE1_" + y + " +";
			compro = compro + " PA.COMPRO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCION, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION, ";
		toeje = toeje.substring(0, toeje.length() - 2) + " ) EJERCIDO, ";
		compro = compro.substring(0, compro.length() - 2) + " ) COMPROMETIDO ";

		sql.append("SELECT T1.CLAVE, T1.NOMBRE, T1.APROBADO, (T1.AMPLIACION - T1.REDUCCION)AMP_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION)- T1.REDUCCION MODIFICADO, (T1.DEVENGADO)DEVENGADO, ")
				.append("(T1.PAGADO)PAGADO, (T1.APROBADO + T1.AMPLIACION) - (T1.REDUCCION - T1.DEVENGADO)SUBEJERCICIO ")
				.append("FROM (SELECT CA.CLAVE, CA.NOMBRE, ").append(auto).append(ampli).append(redu).append(ejpa)
				.append(ejxpa).append(toeje).append(compro)
				.append("FROM PASO PA,  CATDGM CA\n" + "WHERE PA.IDSECTOR = ").append(idSector)
				.append(" AND CA.CLAVE=SUBSTR(PA.CLAVE,1,3) GROUP BY CA.CLAVE, CA.NOMBRE ORDER BY 1,2,3) T1");

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

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

}
