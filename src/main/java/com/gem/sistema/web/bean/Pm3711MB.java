package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getDateSystem;

import java.util.ArrayList;
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

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.dto.Pm3711DTO;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.service.catalogos.Pm3711Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "pm3711MB")
@ViewScoped
public class Pm3711MB extends BaseDirectReport {
	
	private static final String REPORT_NAME = "";

	private List<TcPeriodo> listSemestres;
	private List<Pm3711DTO> listPm3711;
	private Integer semestre;
	private Pm3711DTO pm3711Selected;

	private boolean editando = false;
	private boolean bModificar = false;
	private boolean bUpdate = false;
	private boolean bBorrar;
	private boolean bCancelar;
	private int currentPage = 0;

	@ManagedProperty("#{pm3711Service}")
	private Pm3711Service pm3711Service;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepository;

	@ManagedProperty("#{trEtqTablasRepository}")
	private TrEtqTablasRepository trEtqTablasRepository;

	@PostConstruct
	public void init() {
		listPm3711 = pm3711Service.listAll();
		listSemestres = periodoRepository.findByTipoPeriodo(6);
		bBorrar = false;
		if (CollectionUtils.isNotEmpty(listSemestres)) {
			semestre = listSemestres.get(0).getPeriodo();

		}

		if (CollectionUtils.isEmpty(listPm3711))
			bBorrar = true;
		bModificar = true;
		if (CollectionUtils.isNotEmpty(listPm3711))
			bModificar = false;
		editando = false;
		currentPage = 0;
		bCancelar = true;

		actualizaSeleccionado();
		this.addElement();
	}

	public void salvar() {
		Pm3711DTO nuevo = listPm3711.get(currentPage);
		Integer existe = this.trEtqTablasRepository.validaSemestre(nuevo.getSemestre().toString());
		nuevo.setIdAnio(0L);
		nuevo.setIdRef(0L);
		nuevo.setIdSector(this.getUserDetails().getIdSector());
		nuevo.setCapturo(this.getUserDetails().getUsername());
		bModificar = true;
		if (existe == 0 && !bUpdate) {
			if (editando && this.notNull(nuevo)) {
				editando = false;

				listPm3711 = this.pm3711Service.save(nuevo);
				bModificar = false;
				bCancelar = true;
				bBorrar = false;

				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se ha guardado con exito el registro");
			}
		} else if (bUpdate) {
			listPm3711 = this.pm3711Service.modificar(nuevo);
			bModificar = false;
			editando = false;
			bCancelar = true;
			bBorrar = false;

			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "!Se ha modificado con exito el registro!");
		} else {
			listPm3711.set(listPm3711.size() - 1, nuevo);
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "!El semestre ya existe!");
			bModificar = false;
		}
	}

	public void modificar() {
		pm3711Selected = listPm3711.get(currentPage);
		listPm3711.set(currentPage, pm3711Selected);

		editando = true;
		bUpdate = true;
		bCancelar = false;

		bBorrar = true;
	}

	public void reset() {
		listPm3711 = this.pm3711Service.listAll();
		if (CollectionUtils.isEmpty(listPm3711)) {
			listPm3711.add(new Pm3711DTO());
		}
		editando = false;
	}

	public void adicionar() {

		if (null == listPm3711.get(0).getFechaIng()) {
			for (int i = 0; i < listPm3711.size(); i++) {
				listPm3711.remove(i);
			}
		}

		listPm3711.add(new Pm3711DTO());

		editando = true;
		bCancelar = false;
		if (listPm3711.size() == 2) {
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').paginator.setPage(" + (listPm3711.size() - 1) + ");");

		}
		if (listPm3711.size() > 2) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar 2 semestres.");
			return;
		}
	}

	public void cancelar() {

	}

	public void borrar() {
		if(listPm3711.size() == 1) {
			currentPage = 0;
		}

		pm3711Selected = listPm3711.get(currentPage);

		listPm3711 = this.pm3711Service.deletePm3711(pm3711Selected);

		if (this.listPm3711.isEmpty()) {
			this.listPm3711.add(this.createEmptyPm3711());
			if (StringUtils.isEmpty(listPm3711.get(0).getFechaIng()))
				bBorrar = true;
		}

		this.actualizaSeleccionado();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se eliminó de manera correcta");
	}

	public void cambiarPagina(PageEvent event) {
		currentPage = event.getPage();
		actualizaSeleccionado();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		
		return null;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean notNull(Pm3711DTO pm3711) {
		if (null == pm3711.getFechaIng())
			pm3711.setFechaIng(getDateSystem().toString());

		if (null == pm3711.getTitulo())
			pm3711.setTitulo(0);

		if (null == pm3711.getCertificacion())
			pm3711.setCertificacion(0);

		if (null == pm3711.getExperiencia())
			pm3711.setExperiencia(0);

		return true;
	}

	private Pm3711DTO createEmptyPm3711() {
		Pm3711DTO pm3711 = new Pm3711DTO();
		pm3711.setCapturo(this.getUserDetails().getUsername());
		pm3711.setIdSector(this.getUserDetails().getIdSector());

		return pm3711;
	}

	private void actualizaSeleccionado() {
		if (CollectionUtils.isNotEmpty(listPm3711)) {
			pm3711Selected = listPm3711.get(listPm3711.size() - 1);
			semestre = pm3711Selected.getSemestre();
		}

		/*
		 * else { inicializar(); }
		 */
	}

	public void addElement() {
		Pm3711DTO dto = new Pm3711DTO();
		dto.setCapturo(this.getUserDetails().getUsername());
		dto.setIdSector(this.getUserDetails().getIdSector());
		salida: for (Pm3711DTO pm : listPm3711) {
			if (StringUtils.isEmpty(pm.getFechaIng())) {
				listPm3711.remove(pm);
				break salida;
			}
		}
		if (CollectionUtils.isEmpty(listPm3711)) {

			listPm3711 = new ArrayList<Pm3711DTO>();
			listPm3711.add(0, dto);
		}
	}

	public Pm3711Service getPm3711Service() {
		return pm3711Service;
	}

	public void setPm3711Service(Pm3711Service pm3711Service) {
		this.pm3711Service = pm3711Service;
	}

	public List<TcPeriodo> getListSemestres() {
		return listSemestres;
	}

	public void setListSemestres(List<TcPeriodo> listSemestres) {
		this.listSemestres = listSemestres;
	}

	public List<Pm3711DTO> getListPm3711() {
		return listPm3711;
	}

	public void setListPm3711(List<Pm3711DTO> listPm3711) {
		this.listPm3711 = listPm3711;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public TcPeriodoRepositoy getPeriodoRepository() {
		return periodoRepository;
	}

	public void setPeriodoRepository(TcPeriodoRepositoy periodoRepository) {
		this.periodoRepository = periodoRepository;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public Pm3711DTO getPm3711Selected() {
		return pm3711Selected;
	}

	public void setPm3711Selected(Pm3711DTO pm3711Selected) {
		this.pm3711Selected = pm3711Selected;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isbModificar() {
		return bModificar;
	}

	public void setbModificar(boolean bModificar) {
		this.bModificar = bModificar;
	}

	public TrEtqTablasRepository getTrEtqTablasRepository() {
		return trEtqTablasRepository;
	}

	public void setTrEtqTablasRepository(TrEtqTablasRepository trEtqTablasRepository) {
		this.trEtqTablasRepository = trEtqTablasRepository;
	}

	public boolean isbUpdate() {
		return bUpdate;
	}

	public void setbUpdate(boolean bUpdate) {
		this.bUpdate = bUpdate;
	}

	public boolean isbCancelar() {
		return bCancelar;
	}

	public void setbCancelar(boolean bCancelar) {
		this.bCancelar = bCancelar;
	}

	public boolean isbBorrar() {
		return bBorrar;
	}

	public void setbBorrar(boolean bBorrar) {
		this.bBorrar = bBorrar;
	}

}
