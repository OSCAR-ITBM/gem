package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

import com.gem.sistema.business.domain.Predial;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.PredialRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class CapturaRecaudacionImpuestoPredialMB.
 */
@ManagedBean
@ViewScoped
public class CapturaRecaudacionImpuestoPredialMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The repository. */
	@ManagedProperty("#{predialRepository}")
	private PredialRepository repository;

	/** The master list. */
	private List<Predial> masterList;

	/** The selected. */
	private Predial selected = new Predial();
	
	/** The mes. */
	private String mes;
	
	/** The current page. */
	private int currentPage = 0;
	
	/** The editando. */
	private boolean editando = false;
	
	/** The selectable month. */
	private List<String> selectableMonth;
	
	/** The valid months. */
	private List<String> validMonths = new ArrayList<String>();

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct CapturaRecaudacionImpuestoPredialMB ");
		inicializar();

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		return null;
	}

	/**
	 * Inicializar.
	 */
	public void inicializar() {
		for (int i = 1; i <= 12; i++) {
			validMonths.add(String.valueOf(i));
		}
		masterList = repository.findAllByIdsectorOrderByMesAsc(this.getUserDetails().getIdSector());
		if (masterList == null || masterList.isEmpty()) {
			masterList = new ArrayList<Predial>();
			masterList.add(initializedEntity());
		}
		currentPage = 0;
		actualizaSeleccionado();
	}

	/**
	 * Initialized entity.
	 *
	 * @return the predial
	 */
	private Predial initializedEntity() {
		Predial entity = new Predial();
		entity.setMes("0");
		entity.setIdsector(getUserDetails().getIdSector());
		entity.setUsuario(getUserDetails().getUsername());
		entity.setFeccap(new Date());
		entity.setIdRef(getUserDetails().getIdSector()-1l);
		entity.setUserid(getUserDetails().getUsername());
		return entity;
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		Predial last = masterList.isEmpty() ? null : masterList.get(masterList.size()-1);
		
		if(Objects.nonNull(last) && last.getId() == null){
			masterList.remove(last);
		}

		// Si no existe registros este años crea el mes 1
		Predial entity = initializedEntity();
		
		List<String> currentMonths = masterList.stream().map(Predial::getMes).collect(Collectors.toList());
		selectableMonth = currentMonths.isEmpty() ? validMonths : validMonths.stream().filter(p-> !currentMonths.contains(p)).collect(Collectors.toList());
		
		if (selectableMonth.isEmpty()) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
					"Solo puede agregar hasta el mes 12.");
			return;
		}
		
		selected = entity;
		editando = true;
		entity.setMes(selectableMonth.get(0));
		
		masterList.add(entity);
		RequestContext.getCurrentInstance().execute("PF('dataGrid').getPaginator().setPage(" + (masterList.size()-1) + ");");
		if(currentPage == -1){
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
		Predial nuevo = masterList.get(currentPage);
		//if(null == repository.findByIdsectorAndMes(this.getUserDetails().getIdSector(), nuevo.getMes())) {
			if (editando) {
				editando = false;
				
					boolean modificando = selected != null && selected.getId() != null && selected.getId() > 0;
					String msg = "El registro se ha " + (modificando ? "modificado" : "insertado") + " con éxito!";
					
					sortMasterList();
					
					nuevo = checkNullsAndSetDefaultValues(nuevo);
					Predial predial = repository.save(nuevo);
					masterList.set(currentPage, predial);
					selected = predial;				
					RequestContext.getCurrentInstance().execute("PF('dataGrid').getPaginator().setPage(" + currentPage + ");");
					
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,	msg);
					FacesContext.getCurrentInstance().addMessage(null, message);
			}
		//} else {
		//	generateNotificationFront(SEVERITY_INFO, "Error!", "El mes ya fue registrado");
		//}
		
		
	}
	
	/**
	 * Cancelar.
	 */
	public void cancelar() {
		editando = false;
		if(selected.getId() == null){
			masterList.remove(selected);
			currentPage--;
			actualizaSeleccionado();
		}
	}

	/**
	 * Sort master list.
	 */
	private void sortMasterList() {
		Collections.sort(masterList, new Comparator<Predial>() {
	        @Override
	        public int compare(Predial h1, Predial h2) {
	            return h1.getMes().compareTo(h2.getMes());
	        }
	    });
		
		final Predial comparator = selected;
		currentPage = IntStream.range(0, masterList.size())
			    .filter(p-> {Predial obj = masterList.get(p); return obj.getMes().equals(comparator.getMes()); })
			    .findFirst()
			    .orElse(-1);
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
	private void actualizaSeleccionado(){
		if(!masterList.isEmpty()){
			selected = masterList.get(currentPage);
			mes = selected.getMes();
		}else{
			inicializar();
		}
	}
	
	/**
	 * Reset.
	 */
	public void reset() {
		if(Objects.isNull(selected.getId())){
//			selected.setNcpip(null);
//			selected.setTcrpip(null);
//			selected.setObsncpip("");
//			selected.setObstcrpip("");
		}else{
			selected = repository.findOne(selected.getId());
			masterList.set(currentPage, selected);
		}
	}
	
	/**
	 * Delete.
	 */
	public void delete(){
		repository.delete(selected.getId());
		masterList.remove(currentPage);
		currentPage = currentPage > 0 ? currentPage - 1 : 0;
		actualizaSeleccionado();
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"El registro se eliminó de manera correcta");
	}
	
	/**
	 * Check nulls and set default values.
	 *
	 * @param <T> the generic type
	 * @param object the object
	 * @return the t
	 */
	public static <T> T checkNullsAndSetDefaultValues(T object){
        try
        {
            Class<?> clazz = object.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods)
            {
                boolean isGetterMhetodType = method.getName().startsWith("get") || method.getName().startsWith("is");
                if (isGetterMhetodType){
                    Object fieldValue = method.invoke(object);
                    if (Objects.isNull(fieldValue))
                    {
                        String setMethodName = method.getName().replace("get", "");
                        Field field = clazz.getDeclaredField(setMethodName.toLowerCase());
                        field.setAccessible(true);
                        if(method.getReturnType().equals(Integer.class)){
                            field.set(object, 0);
                        }else if(method.getReturnType().equals(String.class)){
                            field.set(object, "");
                        }else if(method.getReturnType().equals(BigDecimal.class)){
                            field.set(object, BigDecimal.ZERO);
                        }else if(method.getReturnType().equals(Long.class)){
                            field.set(object, 0l);
                        }else if(method.getReturnType().equals(Date.class)){
                        	field.set(object, new Date());
                        }
                    } 
                }
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return object;
    }
	
	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	//getters and setters
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
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
	 * Gets the master list.
	 *
	 * @return the master list
	 */
	public List<Predial> getMasterList() {
		return masterList;
	}

	/**
	 * Sets the master list.
	 *
	 * @param masterList the new master list
	 */
	public void setMasterList(List<Predial> masterList) {
		this.masterList = masterList;
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
	public Predial getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected the new selected
	 */
	public void setSelected(Predial selected) {
		this.selected = selected;
	}

	/**
	 * Gets the selectable month.
	 *
	 * @return the selectable month
	 */
	public List<String> getSelectableMonth() {
		return selectableMonth;
	}

	/**
	 * Sets the selectable month.
	 *
	 * @param selectableMonth the new selectable month
	 */
	public void setSelectableMonth(List<String> selectableMonth) {
		this.selectableMonth = selectableMonth;
	}

	/**
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public PredialRepository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(PredialRepository repository) {
		this.repository = repository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}
}
