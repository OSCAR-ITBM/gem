package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.gem.sistema.business.domain.Ingreso;
import com.gem.sistema.business.repository.load.IngresoRepository;
import com.gem.sistema.web.datamodel.DataModelGeneric;

@ManagedBean(name = "plantillaIngresosOsfemMB")
@ViewScoped
public class PlantillaIngresosOsfemMB extends AbstractMB {
	
	private Ingreso ingresoSelected;
	private List<Ingreso> listIngresos;
	private DataModelGeneric<Ingreso> dataModelIngreso;
	
	private Integer currentIndex;
	private String oldCuenta;
	private String oldScta;
	private String oldSscta;
	private String oldSsscta;
	private String oldSssscta;

	@ManagedProperty("#{ingresoRepository}")
	private IngresoRepository ingresoRepository;

	@PostConstruct
	public void init() {
		this.refreshData();
	}

	public void refreshData() {
		listIngresos = ingresoRepository.getIngresosByIdsector((long) this.getUserDetails().getIdSector());
		dataModelIngreso = new DataModelGeneric<>(listIngresos);
	}

	public void onInitRowEdit(final RowEditEvent event) {
		ingresoSelected = (Ingreso) event.getObject();
		if (null != event.getObject()) {

			if (null != ingresoSelected.getId() && ingresoSelected.getId() != 0) {
				oldCuenta = this.ingresoSelected.getCuenta();
				oldScta = this.ingresoSelected.getScta();
				oldSscta = this.ingresoSelected.getSscta();
				oldSsscta = this.ingresoSelected.getSsscta();
				oldSssscta = this.ingresoSelected.getSssscta();

				for (int i = 0; i < dataModelIngreso.getListT().size(); i++) {

					if (dataModelIngreso.getListT().get(i).getId().equals(ingresoSelected.getId())) {
						currentIndex = i;
						break;
					}
				}
			} 
		}
	}

	public void onRowEdit(RowEditEvent event) {
		ingresoSelected = (Ingreso) event.getObject();
		
		if(null == ingresoSelected.getCvetxt()) {
			ingresoSelected.setCvetxt(BigInteger.ZERO.toString());
		}
		
		ingresoRepository.save(ingresoSelected);
		
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "Actualización finalizada");
	}
	
	public Ingreso getIngresoSelected() {
		return ingresoSelected;
	}

	public void setIngresoSelected(Ingreso ingresoSelected) {
		this.ingresoSelected = ingresoSelected;
	}

	public List<Ingreso> getListIngresos() {
		return listIngresos;
	}

	public void setListIngresos(List<Ingreso> listIngresos) {
		this.listIngresos = listIngresos;
	}

	public DataModelGeneric<Ingreso> getDataModelIngreso() {
		return dataModelIngreso;
	}

	public void setDataModelIngreso(DataModelGeneric<Ingreso> dataModelIngreso) {
		this.dataModelIngreso = dataModelIngreso;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public String getOldCuenta() {
		return oldCuenta;
	}

	public void setOldCuenta(String oldCuenta) {
		this.oldCuenta = oldCuenta;
	}

	public String getOldScta() {
		return oldScta;
	}

	public void setOldScta(String oldScta) {
		this.oldScta = oldScta;
	}

	public String getOldSscta() {
		return oldSscta;
	}

	public void setOldSscta(String oldSscta) {
		this.oldSscta = oldSscta;
	}

	public String getOldSsscta() {
		return oldSsscta;
	}

	public void setOldSsscta(String oldSsscta) {
		this.oldSsscta = oldSsscta;
	}

	public String getOldSssscta() {
		return oldSssscta;
	}

	public void setOldSssscta(String oldSssscta) {
		this.oldSssscta = oldSssscta;
	}

	public IngresoRepository getIngresoRepository() {
		return ingresoRepository;
	}

	public void setIngresoRepository(IngresoRepository ingresoRepository) {
		this.ingresoRepository = ingresoRepository;
	}
	
}
