package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

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

import com.gem.sistema.business.dao.CalificadorDAO;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.dto.CalificadorDTO;
import com.gem.sistema.business.dto.DefensorDTO;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.service.catalogos.CalificadorService;

import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "oficialCalificadorMB")
@ViewScoped
public class OficialCalificadorMB extends BaseDirectReport {

	private static final String TABLE_NAME = "CALIFICADOR";

	private CalificadorDTO calificadorDTO;
	private List<CalificadorDTO> listcalificador;
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

	@ManagedProperty("#{calificadorService}")
	private CalificadorService calificadorService;

	@PostConstruct
	public void init() {
		this.findAll();
		listSemestres = tcPeriodoRepositoy.findByTipoPeriodo(6);
		jasperReporteName = "oficialCalificador";
		endFilename = jasperReporteName + ".pdf";

		if (!listSemestres.isEmpty()) {
			semestre = listSemestres.get(0).getPeriodo();
		}

		calificadorDTO = new CalificadorDTO();
		calificadorDTO.setCapturo(this.getUserDetails().getUsername());
		calificadorDTO.setIdSector(this.getUserDetails().getIdSector());
		listSeme = tcPeriodoRepositoy.findByTipoPeriodo(6);
		semestre = listSeme.get(0).getPeriodo();
		if (CollectionUtils.isEmpty(listcalificador))
			createNewElement(0);

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Object[] meses = getMonthsBySemestre(semestre);

		parameters.put("municipio", firmas.getCampo1());
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		parameters.put("year1", firmas.getCampo3());
		parameters.put("mes1", meses[1]);
		parameters.put("dia1", meses[2]);
		parameters.put("firmaCargo", firmas.getL33());
		parameters.put("firmaNombre", firmas.getN33());

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] getMonthsBySemestre(Integer semestre) {
		Integer mesFinal = semestre * 6;
		Integer mesInicial = mesFinal - 5;
		Object[] meses = {
				tcMesRepository.findByMes(org.apache.commons.lang3.StringUtils.leftPad(mesInicial.toString(), 2, "0"))
						.getDescripcion(),
				tcMesRepository.findByMes(org.apache.commons.lang3.StringUtils.leftPad(mesFinal.toString(), 2, "0"))
						.getDescripcion(),
				getLastDay(mesFinal) };

		return meses;
	}

	public void reset() {
		this.findAll();
		bAdicionar = false;
		bModificar = true;
		bMostrarAdd = true;
		bUpdate = true;

	}

	public void save() {

		calificadorDTO = listcalificador.get(currentPage);
		Integer existe = this.trEtqTablasRepository.validaSemestre(calificadorDTO.getSemestre().toString(), TABLE_NAME);

		calificadorDTO.setIdSector(this.getUserDetails().getIdSector());
		calificadorDTO.setCapturo(this.getUserDetails().getUsername());
		bAdicionar = true;
		if (existe == 0 && !bUpdate) {

			bAdicionar = false;

			listcalificador = this.calificadorService.save(calificadorDTO);
			bBorrar = true;
			bCancelar = false;
			bModificar = true;
			bMostrarAdd = true;
			bLimpiar = false;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Se ha guardado con exito el registro");

		} else if (bUpdate) {
			calificadorDTO = listcalificador.get(currentPage);
			listcalificador = this.calificadorService.modify(calificadorDTO);
			bAdicionar = false;
			bUpdate = false;
			bCancelar = false;
			bModificar = true;
			bMostrarAdd = true;
			bBorrar = true;
			bLimpiar = false;
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "!Se ha modificado con exito el registro!");
		} else {
			listcalificador.set(currentPage, calificadorDTO);
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
		listcalificador = this.calificadorService.findByTableName(TABLE_NAME);
		if (CollectionUtils.isNotEmpty(listcalificador)) {
			if (StringUtils.isNotBlank(listcalificador.get(0).getFechaIng())) {
				bBorrar = true;
				bModificar = true;
			}
		} else {
			listcalificador.add(new CalificadorDTO());
		}

		if (listcalificador.size() > 1) {
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
		listcalificador.add(new CalificadorDTO());
		if (StringUtils.isEmpty(listcalificador.get(0).getFechaIng())) {
			for (int i = 0; i < listcalificador.size(); i++) {
				listcalificador.remove(i);
			}

		}

		if (listcalificador.size() == 2) {
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').paginator.setPage(" + (listcalificador.size() - 1) + ");");
			listcalificador.set(1, new CalificadorDTO());

		} else {
			listcalificador.add(0, calificadorDTO);
		}

	}

	public void createNewElement(Integer index) {

		listcalificador.add(index, calificadorDTO);
	}

	public void borrar() {
		calificadorDTO = listcalificador.get(currentPage);
		calificadorService.delete(calificadorDTO);
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

	public Firmas getFirmas() {
		return firmas;
	}

	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
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

	public CalificadorDTO getCalificadorDTO() {
		return calificadorDTO;
	}

	public void setCalificadorDTO(CalificadorDTO calificadorDTO) {
		this.calificadorDTO = calificadorDTO;
	}

	public List<CalificadorDTO> getListcalificador() {
		return listcalificador;
	}

	public void setListcalificador(List<CalificadorDTO> listcalificador) {
		this.listcalificador = listcalificador;
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

	public CalificadorService getCalificadorService() {
		return calificadorService;
	}

	public void setCalificadorService(CalificadorService calificadorService) {
		this.calificadorService = calificadorService;
	}

}
