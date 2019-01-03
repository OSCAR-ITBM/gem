package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;
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

@ManagedBean(name = "oficialMediadorMB")
@ViewScoped
public class OficialMediadorMB extends BaseDirectReport {

	private List<TcPeriodo> listSemestres;
	private Integer semestre;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@PostConstruct
	public void init() {
		listSemestres = tcPeriodoRepositoy.findByTipoPeriodo(6);
		jasperReporteName = "oficialMediador";
		endFilename = jasperReporteName + ".pdf";

		if (!listSemestres.isEmpty()) {
			semestre = listSemestres.get(0).getPeriodo();
		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Integer month = semestre * 6;
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		TcPeriodo mes = tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, month);

		parameters.put("pImagen", this.getUserDetails().getPathImgCab1());
		parameters.put("pMunicipio", firmas.getCampo1());
		parameters.put("pMes", mes.getDescripcion());
		parameters.put("pDay", getLastDayByAnoEmp(month, firmas.getCampo3()));
		parameters.put("pYear", firmas.getCampo3());
		parameters.put("pSemestre", semestre);
		parameters.put("firmaL32", firmas.getL32());
		parameters.put("firmaN32", firmas.getN32());

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<TcPeriodo> getListSemestres() {
		return listSemestres;
	}

	public void setListSemestres(List<TcPeriodo> listSemestres) {
		this.listSemestres = listSemestres;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
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
