package com.gem.sistema.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Caratula;
import com.gem.sistema.business.repository.catalogs.CaratulaRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CaratulaMB.
 *
 * @author Ernesto Nava Serrano
 */
@ManagedBean(name = "caratulaMB")
@ViewScoped
public class CaratulaMB implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CaratulaMB.class);

	/** The caratula data model. */
	@ManagedProperty(value = "#{caratulaDataModel}")
	private LazyDataModel<Caratula> caratulaDataModel;

	/** The selected caratula. */
	private Caratula selectedCaratula;

	/** The caratula aux. */
	private Caratula caratulaAux;

	/** The caratula repository. */
	@ManagedProperty(value = "#{caratulaRepository}")
	private CaratulaRepository caratulaRepository;

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct Caratula ");
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina caratulaMB  ");

	}

	/**
	 * Prepara un usuario recuperando toda su informacion.
	 *
	 * @param id the id
	 */
	public void prepareActualizar(String id) {
		// try {
		// caratulaAux = userService.getUserById(id);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	/**
	 * Actualiza un usuario.
	 *
	 * @param actionEvent the action event
	 */
	public void actualizar(ActionEvent actionEvent) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",
				String.format("Cuenta %1$s y Subcuenta %2$s   actualizadas correctamente", selectedCaratula.getCuenta(),
						selectedCaratula.getScta()));
		System.out.println("REcibiendo caratula:" + selectedCaratula);

		try {
			caratulaRepository.save(selectedCaratula);
			// transaccionService.saveRastreo(getUsuarioLogueado(),
			// TransactionCodes.USUARIO_ADMON, " Actualizo usuario " +
			// user.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
		}

		context.addMessage(null, msg);
	}

	/**
	 * Gets the caratula data model.
	 *
	 * @return the caratula data model
	 */
	public LazyDataModel<Caratula> getCaratulaDataModel() {
		return caratulaDataModel;
	}

	/**
	 * Sets the caratula data model.
	 *
	 * @param caratulaDataModel the new caratula data model
	 */
	public void setCaratulaDataModel(LazyDataModel<Caratula> caratulaDataModel) {
		this.caratulaDataModel = caratulaDataModel;
	}

	/**
	 * Gets the selected caratula.
	 *
	 * @return the selected caratula
	 */
	public Caratula getSelectedCaratula() {
		return selectedCaratula;
	}

	/**
	 * Sets the selected caratula.
	 *
	 * @param selectedCaratula the new selected caratula
	 */
	public void setSelectedCaratula(Caratula selectedCaratula) {
		this.selectedCaratula = selectedCaratula;
	}

	/**
	 * Gets the caratula aux.
	 *
	 * @return the caratula aux
	 */
	public Caratula getCaratulaAux() {
		return caratulaAux;
	}

	/**
	 * Sets the caratula aux.
	 *
	 * @param caratulaAux the new caratula aux
	 */
	public void setCaratulaAux(Caratula caratulaAux) {
		this.caratulaAux = caratulaAux;
	}

	/**
	 * Gets the caratula repository.
	 *
	 * @return the caratula repository
	 */
	public CaratulaRepository getCaratulaRepository() {
		return caratulaRepository;
	}

	/**
	 * Sets the caratula repository.
	 *
	 * @param caratulaRepository the new caratula repository
	 */
	public void setCaratulaRepository(CaratulaRepository caratulaRepository) {
		this.caratulaRepository = caratulaRepository;
	}

	/**
	 * On row cancel.
	 *
	 * @param event the event
	 */
	public void onRowCancel(RowEditEvent event) {
		// TODO pendiente de implementar
	}

	/**
	 * On cell edit.
	 *
	 * @param event the event
	 */
	public void onCellEdit(CellEditEvent event) {
		event.getColumn().getHeaderText();
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable dataTable = (DataTable) event.getSource();
			Caratula oDetail = (Caratula) dataTable.getRowData();
			if ("Estimado Año(Anterior)".equalsIgnoreCase(event.getColumn().getHeaderText())) {
				oDetail.setAutoi2(BigDecimal.valueOf(Double.valueOf(StringUtils.strip(newValue.toString(), "[]"))));
			}
			if ("Recaudado Año(Anterior)".equalsIgnoreCase(event.getColumn().getHeaderText())) {
				oDetail.setAutoi3(BigDecimal.valueOf(Double.valueOf(StringUtils.strip(newValue.toString(), "[]"))));
			}
			if ("Presupuestado Año(Actual)".equalsIgnoreCase(event.getColumn().getHeaderText())) {
				oDetail.setAutoi1(BigDecimal.valueOf(Double.valueOf(StringUtils.strip(newValue.toString(), "[]"))));
			}

			this.getCaratulaRepository().save(oDetail);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",
					String.format("Cuenta %1$s y Subcuenta %2$s   actualizadas correctamente", oDetail.getCuenta(),
							oDetail.getScta()));
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
