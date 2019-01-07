package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.dto.Pm5911DTO;
import com.gem.sistema.business.dto.ProteccionCivilDTO;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.service.catalogos.Pm5911Service;
import com.gem.sistema.business.service.catalogos.ProteccionCivilService;

@ManagedBean(name = "proteccionCivilMB")
@ViewScoped
public class ProteccionCivilMB extends AbstractMB {

	private static final String TABLE_NAME = "PROTECCION";
	private static final Integer SEMESTRE = 6;

	@ManagedProperty("#{proteccionCivilService}")
	private ProteccionCivilService proteccionCivilService;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{trEtqTablasRepository}")
	private TrEtqTablasRepository trEtqTablasRepository;

	private ProteccionCivilDTO proteccionCivilDTO;

	private List<ProteccionCivilDTO> listProteccion;

	private Integer semestre;

	private List<TcPeriodo> listSemestre;

	private boolean bEditar = false;
	private boolean bSalva = false;
	private boolean bAdicionar = false;
	private boolean bUpdate = false;
	private boolean bBorrar = false;
	private boolean bModificar = false;
	private boolean bCancelar = false;
	private boolean bMostrarAdd = true;
	private boolean bLimpiar = false;

	private int currentPage = 0;

	@PostConstruct
	public void init() {
		this.findAll();

		listSemestre = tcPeriodoRepositoy.findByTipoPeriodo(SEMESTRE);
		proteccionCivilDTO = new ProteccionCivilDTO();
		proteccionCivilDTO.setCapturo(this.getUserDetails().getUsername());
		proteccionCivilDTO.setIdSector(this.getUserDetails().getIdSector());
		semestre = listSemestre.get(0).getPeriodo();
		if (CollectionUtils.isEmpty(listProteccion))
			createNewElement(0);

	}

	public void reset() {
		this.findAll();
		bAdicionar = false;
		bModificar = true;
		bMostrarAdd = true;
		bUpdate = true;

	}

	public void save() {

		proteccionCivilDTO = listProteccion.get(currentPage);
		Integer existe = this.trEtqTablasRepository.validaSemestre(proteccionCivilDTO.getSemestre().toString(),
				TABLE_NAME);
		proteccionCivilDTO.setIdAnio(0);

		proteccionCivilDTO.setIdSector(this.getUserDetails().getIdSector());
		proteccionCivilDTO.setCapturo(this.getUserDetails().getUsername());
		bAdicionar = true;
		if (existe == 0 && !bUpdate) {

			bAdicionar = false;

			listProteccion = this.proteccionCivilService.save(proteccionCivilDTO);
			bBorrar = true;
			bCancelar = false;
			bModificar = true;
			bMostrarAdd = true;
			bLimpiar = false;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se ha guardado con exito el registro");

		} else if (bUpdate) {
			proteccionCivilDTO = listProteccion.get(currentPage);
			listProteccion = this.proteccionCivilService.modify(proteccionCivilDTO);
			bAdicionar = false;
			bUpdate = false;
			bCancelar = false;
			bModificar = true;
			bMostrarAdd = true;
			bBorrar = true;
			bLimpiar = false;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "!Se ha modificado con exito el registro!");
		} else {
			listProteccion.set(currentPage, proteccionCivilDTO);
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
		listProteccion = this.proteccionCivilService.findByTableName(TABLE_NAME);
		if (CollectionUtils.isNotEmpty(listProteccion)) {
			if (StringUtils.isNotBlank(listProteccion.get(0).getFechaIng())) {
				bBorrar = true;
				bModificar = true;
			}
		} else {
			listProteccion.add(new ProteccionCivilDTO());
		}

		if (listProteccion.size() > 1) {
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
		listProteccion.add(new ProteccionCivilDTO());
		if (StringUtils.isEmpty(listProteccion.get(0).getFechaIng())) {
			for (int i = 0; i < listProteccion.size(); i++) {
				listProteccion.remove(i);
			}

		}

		if (listProteccion.size() == 2) {
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').paginator.setPage(" + (listProteccion.size() - 1) + ");");
			listProteccion.set(1, new ProteccionCivilDTO());

		} else {
			listProteccion.add(0, proteccionCivilDTO);
		}

	}

	public void createNewElement(Integer index) {

		listProteccion.add(index, proteccionCivilDTO);
	}

	public void borrar() {
		proteccionCivilDTO = listProteccion.get(currentPage);
		proteccionCivilService.delete(proteccionCivilDTO);
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

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public ProteccionCivilService getProteccionCivilService() {
		return proteccionCivilService;
	}

	public void setProteccionCivilService(ProteccionCivilService proteccionCivilService) {
		this.proteccionCivilService = proteccionCivilService;
	}

	public ProteccionCivilDTO getProteccionCivilDTO() {
		return proteccionCivilDTO;
	}

	public void setProteccionCivilDTO(ProteccionCivilDTO proteccionCivilDTO) {
		this.proteccionCivilDTO = proteccionCivilDTO;
	}

	public List<ProteccionCivilDTO> getListProteccion() {
		return listProteccion;
	}

	public void setListProteccion(List<ProteccionCivilDTO> listProteccion) {
		this.listProteccion = listProteccion;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public List<TcPeriodo> getListSemestre() {
		return listSemestre;
	}

	public void setListSemestre(List<TcPeriodo> listSemestre) {
		this.listSemestre = listSemestre;
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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

}
