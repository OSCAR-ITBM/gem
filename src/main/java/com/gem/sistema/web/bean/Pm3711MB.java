package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getDateSystem;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.dto.Pm3711DTO;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.Pm3711Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "pm3711MB")
@ViewScoped
public class Pm3711MB extends BaseDirectReport {

	private List<TcPeriodo> listSemestres;
	private List<Pm3711DTO> listPm3711;
	private Integer semestre;
	private Pm3711DTO pm3711Selected;

	private boolean editando = false;
	private int currentPage = 0;

	@ManagedProperty("#{pm3711Service}")
	private Pm3711Service pm3711Service;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepository;

	@PostConstruct
	public void init() {
		listPm3711 = pm3711Service.listAll();
		listSemestres = periodoRepository.findByTipoPeriodo(6);

		if (!listSemestres.isEmpty()) {
			semestre = listSemestres.get(0).getPeriodo();
		}

		if (listPm3711.isEmpty()) {
			this.listPm3711.add(this.createEmptyPm3711());
		}
		editando = false;
		currentPage = 0;
		actualizaSeleccionado();
	}

	public void salvar() {
		Pm3711DTO nuevo = listPm3711.get(currentPage);
		
		if(editando && this.notNull(nuevo)) {
			editando = false;
			
			nuevo.setIdAnio(0L);
			nuevo.setIdRef(0L);
			nuevo.setIdSector(this.getUserDetails().getIdSector());
			nuevo.setCapturo(this.getUserDetails().getUsername());
			
			listPm3711.add(nuevo);
			
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se ha guardado con exito el registro");
		}
	}

	public void modificar() {
		editando = true;
	}

	public void reset() {

	}

	public void adicionar() {

		editando = true;
		if (listPm3711.size() == 2) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar 2 semestres.");
			return;
		}
	}

	public void cancelar() {

	}

	public void borrar() {
		this.listPm3711 = this.pm3711Service.deletePm3711(pm3711Selected.getIdRow());
		currentPage = currentPage > 0 ? currentPage - 1 : 0;
		
		if(this.listPm3711.isEmpty()) {
			this.listPm3711.add(this.createEmptyPm3711());
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean notNull(Pm3711DTO pm3711) {
		if(null == pm3711.getFechaIng()) 
			pm3711.setFechaIng(getDateSystem());
		
		if(null == pm3711.getTitulo())
			pm3711.setTitulo(0);
		
		if(null == pm3711.getCertificacion())
			pm3711.setCertificacion(0);
		
		if(null == pm3711.getExperiencia())
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
		if (!listPm3711.isEmpty()) {
			pm3711Selected = listPm3711.get(currentPage);
			semestre = pm3711Selected.getSemestre();
		} 
		/*else {
			inicializar();
		}
		*/
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

}
