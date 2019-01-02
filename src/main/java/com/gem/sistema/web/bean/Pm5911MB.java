package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.event.data.PageEvent;

import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.dto.Pm5911DTO;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.service.catalogos.Pm5911Service;

@ManagedBean(name = "pm5911MB")
@ViewScoped
public class Pm5911MB extends AbstractMB {

	private static final String TABLE_NAME = "pm5911";
	private static final Integer SEMESTRE = 1;

	@ManagedProperty("#{pm5911Service}")
	private Pm5911Service pm5911Service;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{trEtqTablasRepository}")
	private TrEtqTablasRepository trEtqTablasRepository;

	private Pm5911DTO pm5911dto;

	private List<Pm5911DTO> listPmDTO;

	private Integer semestre;

	private List<TcPeriodo> listSemestre;

	private boolean bEditar = false;
	private boolean bSalva = false;
	private boolean bAdicionar = false;
	private boolean bUpdate = false;

	private int currentPage = 0;

	@PostConstruct
	public void init() {
		this.findAll();

		listSemestre = tcPeriodoRepositoy.findByTipoPeriodo(SEMESTRE);

		semestre = listSemestre.get(0).getPeriodo();
		this.addElement();

	}

	public void reset() {
		this.findAll();
	}

	public void save() {

		pm5911dto = listPmDTO.get(currentPage);
		Integer existe = this.trEtqTablasRepository.validaSemestre(pm5911dto.getSemestre().toString());
		pm5911dto.setIdAnio(0);
		pm5911dto.setIdRef(0);
		pm5911dto.setIdSector(this.getUserDetails().getIdSector());
		pm5911dto.setCapturo(this.getUserDetails().getUsername());
		bEditar = true;
		if (existe == 0 && !bUpdate) {
			if (bEditar) {
				bEditar = false;

				listPmDTO = this.pm5911Service.save(pm5911dto);
				

				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se ha guardado con exito el registro");
			}
		} else if (bUpdate) {
			listPmDTO = this.pm5911Service.save(pm5911dto);
			bEditar = false;
			

			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "!Se ha modificado con exito el registro!");
		} else {
			listPmDTO.set(currentPage, pm5911dto);
			generateNotificationFront(FacesMessage.SEVERITY_WARN, "Error!", "!El semestre ya existe!");
			bEditar = false;
		}

	}

	public void findAll() {
		listPmDTO = this.pm5911Service.findByTableName(TABLE_NAME);
	}

	public void cambiarPagina(PageEvent event) {
		currentPage = event.getPage();
		this.findAll();
	}

	public void addElement() {
		pm5911dto = new Pm5911DTO();
		pm5911dto.setCapturo(this.getUserDetails().getUsername());
		pm5911dto.setIdSector(this.getUserDetails().getIdSector());
		if (CollectionUtils.isEmpty(listPmDTO)) {

			listPmDTO = new ArrayList<Pm5911DTO>();
			listPmDTO.add(0, pm5911dto);
		}
	}
	
	public void borrar() {
		
	}

	public Pm5911Service getPm5911Service() {
		return pm5911Service;
	}

	public void setPm5911Service(Pm5911Service pm5911Service) {
		this.pm5911Service = pm5911Service;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public Pm5911DTO getPm5911dto() {
		return pm5911dto;
	}

	public void setPm5911dto(Pm5911DTO pm5911dto) {
		this.pm5911dto = pm5911dto;
	}

	public List<Pm5911DTO> getListPmDTO() {
		return listPmDTO;
	}

	public void setListPmDTO(List<Pm5911DTO> listPmDTO) {
		this.listPmDTO = listPmDTO;
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

}
