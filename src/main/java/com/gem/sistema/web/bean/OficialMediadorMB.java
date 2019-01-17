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

import com.gem.sistema.business.dto.MediadorDTO;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;

import com.gem.sistema.business.service.catalogos.MediadorService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "oficialMediadorMB")
@ViewScoped
public class OficialMediadorMB extends BaseDirectReport {
	private static final String TABLE_NAME = "MEDIADOR";

	private MediadorDTO mediadorDTO;
	private List<MediadorDTO> listMediador;
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

	@ManagedProperty("#{mediadorService}")
	private MediadorService mediadorService;

	@PostConstruct
	public void init() {
		this.findAll();
		listSemestres = tcPeriodoRepositoy.findByTipoPeriodo(6);
		jasperReporteName = "oficialMediador";
		endFilename = jasperReporteName + ".pdf";

		if (!listSemestres.isEmpty()) {
			semestre = listSemestres.get(0).getPeriodo();
		}
		mediadorDTO = new MediadorDTO();
		mediadorDTO.setCapturo(this.getUserDetails().getUsername());
		mediadorDTO.setIdSector(this.getUserDetails().getIdSector());
		listSeme = tcPeriodoRepositoy.findByTipoPeriodo(6);
		semestre = listSeme.get(0).getPeriodo();
		if (CollectionUtils.isEmpty(listMediador))
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

		mediadorDTO = listMediador.get(currentPage);
		Integer existe = this.trEtqTablasRepository.validaSemestre(mediadorDTO.getSemestre().toString(), TABLE_NAME);

		mediadorDTO.setIdSector(this.getUserDetails().getIdSector());
		mediadorDTO.setCapturo(this.getUserDetails().getUsername());
		bAdicionar = true;
		if (existe == 0 && !bUpdate) {

			bAdicionar = false;

			listMediador = this.mediadorService.save(mediadorDTO);
			bBorrar = true;
			bCancelar = true;
			bModificar = true;
			bMostrarAdd = true;
			bLimpiar = true;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se ha guardado con exito el registro");

		} else if (bUpdate) {
			mediadorDTO = listMediador.get(currentPage);
			listMediador = this.mediadorService.modify(mediadorDTO);
			bAdicionar = false;
			bUpdate = false;
			bCancelar = true;
			bModificar = true;
			bMostrarAdd = true;
			bBorrar = true;
			bLimpiar = true;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "!Se ha modificado con exito el registro!");
		} else {
			listMediador.set(currentPage, mediadorDTO);
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
		listMediador = this.mediadorService.findByTableName(TABLE_NAME);
		if (CollectionUtils.isNotEmpty(listMediador)) {
			if (StringUtils.isNotBlank(listMediador.get(0).getFechaIng())) {
				bBorrar = true;
				bModificar = true;
			}
		} else {
			listMediador.add(new MediadorDTO());
		}

		if (listMediador.size() > 1) {
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
		listMediador.add(new MediadorDTO());
		if (StringUtils.isEmpty(listMediador.get(0).getFechaIng())) {
			for (int i = 0; i < listMediador.size(); i++) {
				listMediador.remove(i);
			}

		}

		if (listMediador.size() == 2) {
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').paginator.setPage(" + (listMediador.size() - 1) + ");");
			listMediador.set(1, new MediadorDTO());

		} else {
			listMediador.add(0, mediadorDTO);
		}

	}

	public void createNewElement(Integer index) {

		listMediador.add(index, mediadorDTO);
	}

	public void borrar() {
		mediadorDTO = listMediador.get(currentPage);
		mediadorService.delete(mediadorDTO);
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

	public MediadorDTO getMediadorDTO() {
		return mediadorDTO;
	}

	public void setMediadorDTO(MediadorDTO mediadorDTO) {
		this.mediadorDTO = mediadorDTO;
	}

	public List<MediadorDTO> getListMediador() {
		return listMediador;
	}

	public void setListMediador(List<MediadorDTO> listMediador) {
		this.listMediador = listMediador;
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

	public MediadorService getMediadorService() {
		return mediadorService;
	}

	public void setMediadorService(MediadorService mediadorService) {
		this.mediadorService = mediadorService;
	}

}
