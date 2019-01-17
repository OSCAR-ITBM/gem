package com.gem.sistema.web.bean;

import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.dto.PuestosFirmasDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ComparativoEgresosService;
import com.gem.sistema.business.service.catalogos.EaidService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.TcPeriodoService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "comparativoEgresosMB")
@ViewScoped
public class ComparativoEgresosMB extends BaseDirectReport {

	private static final Log LOG = LogFactory.getLog(ComparativoEgresosMB.class);



	private static final String REPORT_NAME = "comparativoEgresos";

	@ManagedProperty("#{tcPeriodoService}")
	private TcPeriodoService tcPeriodoService;
	
	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{eaidService}")
	private EaidService eaidService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	private PuestosFirmasDTO presidente;
	private PuestosFirmasDTO tesorero;

	private TcPeriodo tcPeriodo;
	private Conctb conctb;

	private String operador;

	private Integer trimestre;

	private Integer idSector;
	private Integer anio;

	private List<TcPeriodo> listPeriodo;

	@ManagedProperty("#{comparativoEgresosService}")
	private ComparativoEgresosService comparativoEgresosService;

	@PostConstruct
	public void init() {
		LOG.info("INICIA EL PROCESO DE CAPTURA DE EAID");

		jasperReporteName = REPORT_NAME;
		endFilename = jasperReporteName + ".pdf";
		this.setIdSector(this.getUserDetails().getIdSector());
		conctb = this.eaidService.getAnioContable(idSector, 0l);

		this.listPeriodo = this.tcPeriodoService.findByPeriodo(1);
		trimestre = listPeriodo.get(0).getPeriodo();

	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public EaidService getEaidService() {
		return eaidService;
	}

	public void setEaidService(EaidService eaidService) {
		this.eaidService = eaidService;
	}

	public void save() {

	}

	public TcPeriodoService getTcPeriodoService() {
		return tcPeriodoService;
	}

	public void setTcPeriodoService(TcPeriodoService tcPeriodoService) {
		this.tcPeriodoService = tcPeriodoService;
	}

	public TcPeriodo getTcPeriodo() {
		return tcPeriodo;
	}

	public void setTcPeriodo(TcPeriodo tcPeriodo) {
		this.tcPeriodo = tcPeriodo;
	}

	public List<TcPeriodo> getListPeriodo() {
		return listPeriodo;
	}

	public void setListPeriodo(List<TcPeriodo> listPeriodo) {
		this.listPeriodo = listPeriodo;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public PuestosFirmasDTO getPresidente() {
		return presidente;
	}

	public void setPresidente(PuestosFirmasDTO presidente) {
		this.presidente = presidente;
	}

	public PuestosFirmasDTO getTesorero() {
		return tesorero;
	}

	public void setTesorero(PuestosFirmasDTO tesorero) {
		this.tesorero = tesorero;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		TcPeriodo periodo = tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, trimestre);

		String sSql = this.comparativoEgresosService.generaQueryCompartivo(idSector, trimestre);
		parameters.put("mes", "Enero");
		parameters.put("pMesFinal", periodo.getDescripcion());
		parameters.put("dia1", getLastDayByAnoEmp(trimestre, firmas.getCampo3()));
		parameters.put("year", firmas.getCampo3());
		parameters.put("municipio", firmas.getCampo1());
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		parameters.put("firmaCargo1", firmas.getL1());
		parameters.put("firmaNombre1", firmas.getN1());
		parameters.put("firmaCargo2", firmas.getL2());
		parameters.put("firmaNombre2", firmas.getN2());
		parameters.put("idSector", this.getIdSector());
		parameters.put("firmaCargo3", firmas.getL3());
		parameters.put("firmaNombre3", firmas.getN3());
		parameters.put("sSql", sSql);

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void getFirmas() {
		List<PuestosFirmasDTO> puestosFirmas = puestosFirmasService.listFirmas(this.getUserDetails().getIdSector());
		for (int y = 0; y < puestosFirmas.size(); y++) {
			if (puestosFirmas.get(y).getId() == 1) {
				this.presidente = puestosFirmas.get(y);
			}
			if (puestosFirmas.get(y).getId() == 3) {
				this.tesorero = puestosFirmas.get(y);
			}
		}
	}

	public ComparativoEgresosService getComparativoEgresosService() {
		return comparativoEgresosService;
	}

	public void setComparativoEgresosService(ComparativoEgresosService comparativoEgresosService) {
		this.comparativoEgresosService = comparativoEgresosService;
	}

}
