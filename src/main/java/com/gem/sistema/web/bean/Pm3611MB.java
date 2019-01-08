package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;

import com.gem.sistema.business.dto.Pm3611DTO;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;

import com.gem.sistema.business.service.catalogos.Pm3611Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "pm3611MB")
@ViewScoped
public class Pm3611MB extends BaseDirectReport {
	private static final String TABLE_NAME = "pm3611";

	private Pm3611DTO pm3611dto;
	private List<Pm3611DTO> listPm3611;
	private List<TcPeriodo> listSemestres;
	private Integer semestre;

	private List<TcPeriodo> listSeme;
	private Integer semes;
	private Firmas firmas;

	private int currentPage = 0;

	private boolean bEditar = false;
	private boolean bSalva = false;
	private boolean bAdicionar = false;
	private boolean bUpdate = false;
	private boolean bBorrar = false;
	private boolean bModificar = false;
	private boolean bCancelar = false;
	private boolean bMostrarAdd = true;
	private boolean bLimpiar = false;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{trEtqTablasRepository}")
	private TrEtqTablasRepository trEtqTablasRepository;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{pm3611Service}")
	private Pm3611Service pm3611Service;

	@PostConstruct
	public void init() {
		this.findAll();
		listSemestres = tcPeriodoRepositoy.findByTipoPeriodo(6);
		jasperReporteName = "secretarioAyuntamiento";
		endFilename = jasperReporteName + ".pdf";

		if (!listSemestres.isEmpty()) {
			semestre = listSemestres.get(0).getPeriodo();
		}
		pm3611dto = new Pm3611DTO();
		pm3611dto.setCapturo(this.getUserDetails().getUsername());
		pm3611dto.setIdSector(this.getUserDetails().getIdSector());
		listSeme = tcPeriodoRepositoy.findByTipoPeriodo(6);
		semestre = listSeme.get(0).getPeriodo();
		if (CollectionUtils.isEmpty(listPm3611))
			createNewElement(0);

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

	public void reset() {
		this.findAll();
		bAdicionar = false;
		bModificar = true;
		bMostrarAdd = true;
		bUpdate = true;

	}

	public void save() {

		pm3611dto = listPm3611.get(currentPage);
		Integer existe = this.trEtqTablasRepository.validaSemestre(pm3611dto.getSemestre().toString(), TABLE_NAME);

		pm3611dto.setIdSector(this.getUserDetails().getIdSector());
		pm3611dto.setCapturo(this.getUserDetails().getUsername());
		bAdicionar = true;
		if (existe == 0 && !bUpdate) {

			bAdicionar = false;

			listPm3611 = this.pm3611Service.save(pm3611dto);
			bBorrar = true;
			bCancelar = true;
			bModificar = true;
			bMostrarAdd = true;
			bLimpiar = true;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se ha guardado con exito el registro");

		} else if (bUpdate) {
			pm3611dto = listPm3611.get(currentPage);
			listPm3611 = this.pm3611Service.modify(pm3611dto);
			bAdicionar = false;
			bUpdate = false;
			bCancelar = true;
			bModificar = true;
			bMostrarAdd = true;
			bBorrar = true;
			bLimpiar = true;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "!Se ha modificado con exito el registro!");
		} else {
			listPm3611.set(currentPage, pm3611dto);
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "!El semestre ya existe!");
			bAdicionar = true;
			bCancelar = true;
		}

	}

	public void findAll() {
		bBorrar = false;
		bModificar = false;
		bCancelar = true;
		bLimpiar = true;
		listPm3611 = this.pm3611Service.findByTableName(TABLE_NAME);
		if (CollectionUtils.isNotEmpty(listPm3611)) {
			if (StringUtils.isNotBlank(listPm3611.get(0).getFechaIng())) {
				bBorrar = true;
				bModificar = true;
			}
		} else {
			listPm3611.add(new Pm3611DTO());
		}

		if (listPm3611.size() > 1) {
			bAdicionar = false;
			bBorrar = true;
		}
	}

	public void cambiarPagina(PageEvent event) {
		currentPage = event.getPage();

	}

	public void addElement() {
		bAdicionar = true;
		bCancelar = true;
		bBorrar = false;
		bLimpiar = true;
		bMostrarAdd = false;
		bModificar = false;
		listPm3611.add(new Pm3611DTO());
		if (StringUtils.isEmpty(listPm3611.get(0).getFechaIng())) {
			for (int i = 0; i < listPm3611.size(); i++) {
				listPm3611.remove(i);
			}

		}

		if (listPm3611.size() == 2) {
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').paginator.setPage(" + (listPm3611.size() - 1) + ");");
			listPm3611.set(1, new Pm3611DTO());

		} else {
			listPm3611.add(0, pm3611dto);
		}

	}

	public void createNewElement(Integer index) {

		listPm3611.add(index, pm3611dto);
	}

	public void borrar() {
		pm3611dto = listPm3611.get(currentPage);
		pm3611Service.delete(pm3611dto);
		this.findAll();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "!Se borro con exito el registro!");

	}

	public void modificar() {
		bUpdate = true;
		bAdicionar = true;
		bMostrarAdd = false;
		bLimpiar = false;
		bBorrar = false;
		bCancelar = false;
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

	public List<TcPeriodo> getListSeme() {
		return listSeme;
	}

	public void setListSeme(List<TcPeriodo> listSeme) {
		this.listSeme = listSeme;
	}

	public Integer getSemes() {
		return semes;
	}

	public void setSemes(Integer semes) {
		this.semes = semes;
	}

	public Firmas getFirmas() {
		return firmas;
	}

	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isbEditar() {
		return bEditar;
	}

	public void setbEditar(boolean bEditar) {
		this.bEditar = bEditar;
	}

	public boolean isbSalva() {
		return bSalva;
	}

	public void setbSalva(boolean bSalva) {
		this.bSalva = bSalva;
	}

	public boolean isbAdicionar() {
		return bAdicionar;
	}

	public void setbAdicionar(boolean bAdicionar) {
		this.bAdicionar = bAdicionar;
	}

	public boolean isbUpdate() {
		return bUpdate;
	}

	public void setbUpdate(boolean bUpdate) {
		this.bUpdate = bUpdate;
	}

	public boolean isbBorrar() {
		return bBorrar;
	}

	public void setbBorrar(boolean bBorrar) {
		this.bBorrar = bBorrar;
	}

	public boolean isbModificar() {
		return bModificar;
	}

	public void setbModificar(boolean bModificar) {
		this.bModificar = bModificar;
	}

	public boolean isbCancelar() {
		return bCancelar;
	}

	public void setbCancelar(boolean bCancelar) {
		this.bCancelar = bCancelar;
	}

	public boolean isbMostrarAdd() {
		return bMostrarAdd;
	}

	public void setbMostrarAdd(boolean bMostrarAdd) {
		this.bMostrarAdd = bMostrarAdd;
	}

	public boolean isbLimpiar() {
		return bLimpiar;
	}

	public void setbLimpiar(boolean bLimpiar) {
		this.bLimpiar = bLimpiar;
	}

	public TrEtqTablasRepository getTrEtqTablasRepository() {
		return trEtqTablasRepository;
	}

	public void setTrEtqTablasRepository(TrEtqTablasRepository trEtqTablasRepository) {
		this.trEtqTablasRepository = trEtqTablasRepository;
	}

	public Pm3611DTO getPm3611dto() {
		return pm3611dto;
	}

	public void setPm3611dto(Pm3611DTO pm3611dto) {
		this.pm3611dto = pm3611dto;
	}

	public List<Pm3611DTO> getListPm3611() {
		return listPm3611;
	}

	public void setListPm3611(List<Pm3611DTO> listPm3611) {
		this.listPm3611 = listPm3611;
	}

	public Pm3611Service getPm3611Service() {
		return pm3611Service;
	}

	public void setPm3611Service(Pm3611Service pm3611Service) {
		this.pm3611Service = pm3611Service;
	}

}
