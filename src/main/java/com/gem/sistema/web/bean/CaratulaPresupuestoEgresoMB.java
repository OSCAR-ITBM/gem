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
import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Pasot;
import com.gem.sistema.business.repository.catalogs.PasotRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class CaratulaPresupuestoEgresoMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CaratulaPresupuestoEgresoMB implements Serializable {	

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CaratulaPresupuestoEgresoMB.class);

	/** The pasot data model. */
	@ManagedProperty( value = "#{pasotDataModel}")
	private LazyDataModel<Pasot> pasotDataModel;
	
	/** The selected pasot. */
	private Pasot selectedPasot;

	/** The pasot aux. */
	private Pasot pasotAux;

	/** The pasot repository. */
	@ManagedProperty(value = "#{pasotRepository}")
	private PasotRepository pasotRepository;

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct Pasot ");
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina CaratulaPresupuestoEgresoMB  ");

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
		this.updateSelectedPasot();
	}

	/**
	 * Update selected pasot.
	 */
	private void updateSelectedPasot() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",
				String.format("Partida %1$s actualizada correctamente", selectedPasot.getPartida()));
		System.out.println("REcibiendo Pasot:" + selectedPasot);

		try {
			pasotRepository.save(selectedPasot);
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
	 * Gets the pasot data model.
	 *
	 * @return the pasot data model
	 */
	public LazyDataModel<Pasot> getPasotDataModel() {
		return pasotDataModel;
	}

	/**
	 * Sets the pasot data model.
	 *
	 * @param pasotDataModel the new pasot data model
	 */
	public void setPasotDataModel(LazyDataModel<Pasot> pasotDataModel) {
		this.pasotDataModel = pasotDataModel;
	}

	/**
	 * Gets the selected pasot.
	 *
	 * @return the selected pasot
	 */
	public Pasot getSelectedPasot() {
		return selectedPasot;
	}

	/**
	 * Sets the selected pasot.
	 *
	 * @param selectedPasot the new selected pasot
	 */
	public void setSelectedPasot(Pasot selectedPasot) {
		this.selectedPasot = selectedPasot;
	}

	/**
	 * Gets the pasot aux.
	 *
	 * @return the pasot aux
	 */
	public Pasot getPasotAux() {
		return pasotAux;
	}

	/**
	 * Sets the pasot aux.
	 *
	 * @param pasotAux the new pasot aux
	 */
	public void setPasotAux(Pasot pasotAux) {
		this.pasotAux = pasotAux;
	}

	/**
	 * Gets the pasot repository.
	 *
	 * @return the pasot repository
	 */
	public PasotRepository getPasotRepository() {
		return pasotRepository;
	}

	/**
	 * Sets the pasot repository.
	 *
	 * @param pasotRepository the new pasot repository
	 */
	public void setPasotRepository(PasotRepository pasotRepository) {
		this.pasotRepository = pasotRepository;
	}

	/**
	 * On cell edit.
	 *
	 * @param event the event
	 */
	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			DataTable dataTable = (DataTable) event.getSource();
			Pasot oDetail = (Pasot) dataTable.getRowData();
			if ("Autorizado (Año anterior)".equalsIgnoreCase(event.getColumn().getHeaderText())) {
				oDetail.setAuto12(BigDecimal.valueOf(Double.valueOf(StringUtils.strip(newValue.toString(), "[]"))));
			}
			if ("Ejercido (Año anterior)".equalsIgnoreCase(event.getColumn().getHeaderText())) {
				oDetail.setAuto13(BigDecimal.valueOf(Double.valueOf(StringUtils.strip(newValue.toString(), "[]"))));
			}
			if ("Presupuestado".equalsIgnoreCase(event.getColumn().getHeaderText())) {
				oDetail.setAuto11(BigDecimal.valueOf(Double.valueOf(StringUtils.strip(newValue.toString(), "[]"))));
			}

			this.getPasotRepository().save(oDetail);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",
					String.format("Partida %1$s actualizada correctamente", oDetail.getPartida()));
			System.out.println("REcibiendo Pasot:" + selectedPasot);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
