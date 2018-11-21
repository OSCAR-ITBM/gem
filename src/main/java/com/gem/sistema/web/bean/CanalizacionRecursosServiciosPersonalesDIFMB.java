package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Pm1411;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.Pm1411Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class CanalizacionRecursosServiciosPersonalesDIFMB.
 */
@ManagedBean
@ViewScoped
public class CanalizacionRecursosServiciosPersonalesDIFMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The service. */
	@ManagedProperty("#{pm1411Service}")
	private Pm1411Service service;

	/** The master list. */
	private List<Pm1411> masterList;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The selected. */
	private Pm1411 selected = new Pm1411();
	
	/** The trimestre. */
	private Integer trimestre;
	
	/** The current page. */
	private int currentPage = 0;
	
	/** The editando. */
	private boolean editando = false;
	
	/** The selectable month. */
	private List<Integer> selectableMonth;
	
	/** The valid months. */
	private List<Integer> validMonths = new ArrayList<Integer>();
	
	/** The firmas. */
	Firmas firmas;
	
	/** The conctb. */
	private Conctb conctb;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct CanalizacionRecursosServiciosPersonalesDIFMB");
		jasperReporteName = "reporte118";
		endFilename = jasperReporteName + ".pdf";
		inicializar();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		parameters.put("TRIMESTRE", trimestre);
		parameters.put("SECTOR", getUserDetails().getIdSector());
		parameters.put("IMAGEN", getUserDetails().getPathImgCab1());
		parameters.put("CAMPO1", firmas.getCampo1());
		parameters.put("CLAVE", conctb.getClave());
		parameters.put("ANO_EMP", firmas.getCampo3());
		parameters.put("N4", firmas.getN4());
		parameters.put("L4", firmas.getL4());
		parameters.put("N3", firmas.getN3());
		parameters.put("L3", firmas.getL3());
		parameters.put("N5", firmas.getN5());
		parameters.put("L5", firmas.getL5());

		return parameters;
	}

	/**
	 * Entity initialized.
	 *
	 * @return the pm 1411
	 */
	private Pm1411 entityInitialized() {
		Pm1411 entity = new Pm1411();
		//entity.setMensual(1);
		entity.setIdsector(getUserDetails().getIdSector());
		entity.setCapturo(getUserDetails().getUsername());
		entity.setFeccap(new Date());
		entity.setIdRef(getUserDetails().getIdSector() - 1l);
		entity.setUserid(getUserDetails().getUsername());

		entity.setTe(BigDecimal.ZERO);
		entity.setSp(BigDecimal.ZERO);
		entity.setSpspa(BigDecimal.ZERO);
		entity.setAcute(BigDecimal.ZERO);
		entity.setAcusp(BigDecimal.ZERO);
		entity.setAcuspspa(BigDecimal.ZERO);
		entity.setObste(" ");
		entity.setObssp(" ");
		entity.setObsspspa(" ");
		entity.setDif(BigDecimal.ZERO);
		entity.setPdif(BigDecimal.ZERO);
		entity.setSjaf(BigDecimal.ZERO);
		entity.setPsjaf(BigDecimal.ZERO);
		entity.setOapf(BigDecimal.ZERO);
		entity.setPoapf(BigDecimal.ZERO);
		entity.setAls(BigDecimal.ZERO);
		entity.setPals(BigDecimal.ZERO);
		entity.setApe(BigDecimal.ZERO);
		entity.setPape(BigDecimal.ZERO);
		entity.setAam(BigDecimal.ZERO);
		entity.setPaam(BigDecimal.ZERO);
		entity.setAj(BigDecimal.ZERO);
		entity.setPaj(BigDecimal.ZERO);
		entity.setIed(BigDecimal.ZERO);
		entity.setPied(BigDecimal.ZERO);
		entity.setEi(BigDecimal.ZERO);
		entity.setPei(BigDecimal.ZERO);
		entity.setAvma(BigDecimal.ZERO);
		entity.setPavma(BigDecimal.ZERO);
		entity.setPafd(BigDecimal.ZERO);
		entity.setPpafd(BigDecimal.ZERO);
		entity.setSpdc(BigDecimal.ZERO);
		entity.setPspdc(BigDecimal.ZERO);
		entity.setAmse(BigDecimal.ZERO);
		entity.setPamse(BigDecimal.ZERO);
		entity.setApcd(BigDecimal.ZERO);
		entity.setPapcd(BigDecimal.ZERO);
		entity.setAmpg(BigDecimal.ZERO);
		entity.setPampg(BigDecimal.ZERO);
		entity.setOana(BigDecimal.ZERO);
		entity.setPoana(BigDecimal.ZERO);
		entity.setAid(BigDecimal.ZERO);
		entity.setPaid(BigDecimal.ZERO);

		return entity;
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		for (int i = 1; i <= 4; i++) {
			validMonths.add(i);
		}
		masterList = service.findAllBySector(getUserDetails().getIdSector());
		if (masterList == null || masterList.isEmpty()) {
			masterList = new ArrayList<Pm1411>();
			masterList.add(entityInitialized());
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Pm1411 last = masterList.isEmpty() ? null : masterList.get(masterList.size() - 1);

		if (Objects.nonNull(last) && last.getId() == null) {
			masterList.remove(last);
		}

		// Si no existe registros este años crea el mes 1
		Pm1411 entity = entityInitialized();

		List<Integer> currentMonths = masterList.stream().map(Pm1411::getMensual).collect(Collectors.toList());
		selectableMonth = currentMonths.isEmpty() ? validMonths
				: validMonths.stream().filter(p -> !currentMonths.contains(p)).collect(Collectors.toList());

		if (selectableMonth.isEmpty()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Solo puede agregar hasta el trimestre 4.");
			return;
		}

		selected = entity;
		editando = true;

		entity.setMensual(selectableMonth.get(0));

		masterList.add(entity);
		RequestContext.getCurrentInstance()
				.execute("PF('dataGrid').getPaginator().setPage(" + (masterList.size() - 1) + ");");
		if (currentPage == -1) {
			currentPage = 0;
			actualizaSeleccionado();
		}
	}

	/**
	 * Modificar.
	 */
	public void modificar() {
		editando = true;
	}

	/**
	 * Salvar.
	 */
	public void salvar() {
		Pm1411 nuevo = masterList.get(currentPage);
		if (editando && validarNonNull(nuevo)) {
			editando = false;

			// Actualiza algunos campos autogenerados
			nuevo.setUserid(getUserDetails().getUsername());
			nuevo.setCapturo(getUserDetails().getUsername());
			nuevo.setIdsector(getUserDetails().getIdSector());
			nuevo.setIdRef(getUserDetails().getIdSector() - 1l);
			nuevo.setFeccap(new Date());

			boolean modificando = selected != null && selected.getId() != null && selected.getId() > 0;
			String msg = "El registro se ha " + (modificando ? "modificado" : "insertado") + " con éxito!";
			boolean isNotLastMonth = masterList.stream().anyMatch(p -> p.getMensual() > nuevo.getMensual());

			sortMasterList();
			RequestContext.getCurrentInstance()
					.execute("PF('dataGrid').getPaginator().setPage(" + (selected.getMensual() - 1) + ");");

			// Si esta modificando y no es el último registro se debe recalcular
			// desde el mes modificado has el último mes
			if (isNotLastMonth) {
				for (int i = masterList.indexOf(selected); i < masterList.size(); i++) {
					Pm1411 currentRecord = masterList.get(i);
					calculaAcumulados(currentRecord);
					Pm1411 Pm1411 = service.save(currentRecord);
					masterList.set(i, Pm1411);
					if (i == masterList.indexOf(selected)) {
						selected = Pm1411;
					}
				}
			} else {
				calculaAcumulados(nuevo);
				Pm1411 Pm1411 = service.save(nuevo);
				masterList.set(currentPage, Pm1411);
			}
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, msg);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}

	/**
	 * Cancelar.
	 */
	public void cancelar() {
		editando = false;
		if (selected.getId() == null) {
			masterList.remove(selected);
			currentPage--;
			actualizaSeleccionado();
		}
	}

	/**
	 * Validar non null.
	 *
	 * @param nuevo the nuevo
	 * @return true, if successful
	 */
	private boolean validarNonNull(Pm1411 nuevo) {
		boolean isValid = true;
		if (!isValid) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
					"Por favor complete los campos requeridos.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return isValid;
	}

	/**
	 * Sort master list.
	 */
	private void sortMasterList() {
		Collections.sort(masterList, new Comparator<Pm1411>() {
			@Override
			public int compare(Pm1411 h1, Pm1411 h2) {
				return h1.getMensual().compareTo(h2.getMensual());
			}
		});
	}

	/**
	 * Calcula acumulados.
	 *
	 * @param currentRecord the current record
	 */
	private void calculaAcumulados(Pm1411 currentRecord) {
		Pm1411 beforeSelected;
		// Agarramos el registro anterior al registro seleccionado
		int lastIndex = masterList.indexOf(currentRecord) - 1;
		beforeSelected = lastIndex > -1 ? masterList.get(lastIndex) : null;
		// Si no existe anterior instanciamos un nuevo registro
		if (Objects.isNull(beforeSelected)) {
			if (Objects.isNull(beforeSelected)) {
				beforeSelected = entityInitialized();
			}
		}
		if (Objects.isNull(currentRecord.getTe()))
			currentRecord.setTe(BigDecimal.ZERO);
		if (Objects.isNull(currentRecord.getSp()))
			currentRecord.setSp(BigDecimal.ZERO);
		currentRecord.setAcute(beforeSelected.getAcute().add(currentRecord.getTe()));
		currentRecord.setAcusp(beforeSelected.getAcusp().add(currentRecord.getSp()));
		currentRecord.setAcuspspa(beforeSelected.getAcuspspa().add(currentRecord.getSpspa()));

	}

	/**
	 * Cambiar pagina.
	 *
	 * @param event the event
	 */
	public void cambiarPagina(PageEvent event) {
		currentPage = event.getPage();
		actualizaSeleccionado();
	}

	/**
	 * Actualiza seleccionado.
	 */
	private void actualizaSeleccionado() {
		if (!masterList.isEmpty()) {
			selected = masterList.get(currentPage);
			trimestre = selected.getMensual();
		} else {
			inicializar();
		}
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (Objects.isNull(selected.getId())) {
			selected = entityInitialized();
		} else {
			selected = service.findById(selected.getId());
		}
		masterList.set(currentPage, selected);
	}

	/**
	 * Delete.
	 */
	public void delete() {
		service.delete(selected.getId());
		masterList.remove(currentPage);
		currentPage = currentPage > 0 ? currentPage - 1 : 0;
		for (int i = currentPage; i >= 0 && i < masterList.size(); i++) {
			Pm1411 currentRecord = masterList.get(i);
			if (Objects.nonNull(currentRecord) && currentRecord.getId() > 0) {
				calculaAcumulados(currentRecord);
				Pm1411 Pm1411 = service.save(currentRecord);
				masterList.set(i, Pm1411);
				if (i == currentPage) {
					selected = Pm1411;
				}
			}
		}
		actualizaSeleccionado();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El registro se eliminó de manera correcta");
	}

	/**
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public Integer getTrimestre() {
		return trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre the new trimestre
	 */
	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<Pm1411> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Pm1411> masterList) {
		this.masterList = masterList;
	}

	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	public Pm1411Service getService() {
		return service;
	}

	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	public void setService(Pm1411Service service) {
		this.service = service;
	}

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets the current page.
	 *
	 * @param currentPage the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * Checks if is editando.
	 *
	 * @return true, if is editando
	 */
	public boolean isEditando() {
		return editando;
	}

	/**
	 * Sets the editando.
	 *
	 * @param editando the new editando
	 */
	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public Pm1411 getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Pm1411 selected) {
		this.selected = selected;
	}

	/**
	 * Gets the selectable month.
	 *
	 * @return the selectable month
	 */
	public List<Integer> getSelectableMonth() {
		return selectableMonth;
	}

	/**
	 * Sets the selectable month.
	 *
	 * @param selectableMonth the new selectable month
	 */
	public void setSelectableMonth(List<Integer> selectableMonth) {
		this.selectableMonth = selectableMonth;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

}
