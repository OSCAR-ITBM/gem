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

@ManagedBean(name = "eAEPEClasificEcoTipoGastoMB")
@ViewScoped
public class EAEPEClasificEcoTipoGastoMB extends BaseDirectReport {
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
		jasperReporteName = "clasificacioneconomicaportipodegasto";
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

		parameters.put("pMunicipio", firmas.getCampo1());
		parameters.put("pDay", getLastDayByAnoEmp(mesSelected.getPeriodo(), conctb.getAnoemp()));
		parameters.put("pMes", mesSelected.getDescripcion());
		parameters.put("pYear", conctb.getAnoemp());
		parameters.put("pImagen", this.getUserDetails().getPathImgCab1());
		parameters.put("pL1", firmas.getL1());
		parameters.put("pN1", firmas.getN1());
		parameters.put("pL2", firmas.getL2());
		parameters.put("pN2", firmas.getN2());
		parameters.put("pL3", firmas.getL3());
		parameters.put("pN3", firmas.getN3());
		parameters.put("pQuery", this.generateSQL(idSector, mes));

		return parameters;
	}

	private String generateSQL(Integer idSector, Integer mes) {
		StringBuilder sql = new StringBuilder();

		String auto = "FN_GET_FORMAT_NUMBER(SUM(";
		String ejpa = "FN_GET_FORMAT_NUMBER(SUM(";
		String redu = "FN_GET_FORMAT_NUMBER(SUM(";
		String ejxpa = "FN_GET_FORMAT_NUMBER(SUM(";
		String ampli = "FN_GET_FORMAT_NUMBER(SUM(";

		for (int y = 1; y <= mes; y++) {
			auto = auto + " P.AUTO1_" + y + " +";
			ejpa = ejpa + " P.EJPA1_" + y + " +";
			redu = redu + " P.REDU1_" + y + " +";
			ejxpa = ejxpa + " P.EJXPA1_" + y + " +";
			ampli = ampli + " P.AMPLI1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " )) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " )) PAGADO ";
		redu = redu.substring(0, redu.length() - 2) + " )) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " )) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " )) AMPLIACIONES, ";

		sql.append("SELECT RES.CUENTA, RES.CONCEPTO, RES.APROBADO, (RES.AMPLIACIONES-RES.REDUCCIONES) AMP_RED, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES) MODIFICADO , RES.DEVENGADO, RES.PAGADO, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES-RES.DEVENGADO) SUB_E, 'Gasto Corriente' ")
				.append("FROM (SELECT P.PARTIDA CUENTA, N.NOMGAS CONCEPTO, ").append(auto).append(redu).append(ampli)
				.append(ejxpa).append(ejpa)
				.append("FROM PASO P, NATGAS N WHERE P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR = ")
				.append(idSector).append(" AND N.IDSECTOR = ").append(idSector)
				.append(" AND P.PARTIDA <5000 GROUP BY P.PARTIDA, N.NOMGAS ORDER BY 1,2 )RES UNION ALL ")
				.append("SELECT RES.CUENTA, RES.CONCEPTO, RES.APROBADO, (RES.AMPLIACIONES-RES.REDUCCIONES) AMP_RED, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES) MODIFICADO , RES.DEVENGADO, RES.PAGADO, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES-RES.DEVENGADO) SUB_E, 'Gasto Capital' ")
				.append("FROM (SELECT P.PARTIDA CUENTA, N.NOMGAS CONCEPTO, ").append(auto).append(redu).append(ampli)
				.append(ejxpa).append(ejpa)
				.append("FROM PASO P,NATGAS N WHERE P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR = ")
				.append(idSector).append(" AND N.IDSECTOR = ").append(idSector)
				.append(" AND P.PARTIDA >=5000 AND P.PARTIDA <9000 GROUP BY P.PARTIDA, N.NOMGAS ORDER BY 1,2 )RES UNION ALL ")
				.append("SELECT RES.CUENTA, RES.CONCEPTO, RES.APROBADO, (RES.AMPLIACIONES-RES.REDUCCIONES) AMP_RED, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES) MODIFICADO , RES.DEVENGADO, RES.PAGADO, ")
				.append("(RES.APROBADO+RES.AMPLIACIONES-RES.REDUCCIONES-RES.DEVENGADO) SUB_E,")
				.append("'Amortización de la Deuda y Disminucion de Pasivos' ")
				.append("FROM (SELECT P.PARTIDA CUENTA, N.NOMGAS  CONCEPTO, ").append(auto).append(redu).append(ampli)
				.append(ejxpa).append(ejpa)
				.append("FROM PASO P,NATGAS N WHERE P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND P.IDSECTOR = ")
				.append(idSector).append(" AND N.IDSECTOR = ").append(idSector)
				.append(" AND P.PARTIDA >=9000 GROUP BY P.PARTIDA, N.NOMGAS ORDER BY 1,2 )RES");

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
