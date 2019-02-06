package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Caratula;
import com.gem.sistema.business.predicates.CaratulaPredicates;
import com.gem.sistema.business.repository.catalogs.CaratulaRepository;
import com.gem.sistema.web.datamodel.DataModelGeneric;

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

	/** The Constant EDIT_FLOW_ROW_JQUERY. */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = "jQuery('span.ui-icon-pencil').eq(";

	/** The Constant VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT. */
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = ").each(function(){jQuery(this).click()});";

	/** The Constant FOCUS_BY_ROWID. */
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:objects:%1$s:cuenta');";

	/** The caratula data model. */
	private DataModelGeneric<Caratula> caratulaDataModel;

	/** The selected caratula. */
	private Caratula selectedCaratula;

	/** The caratula aux. */
	private Caratula caratulaAux;

	private List<Caratula> listCaratula;

	private Boolean bEdicion;

	/** The caratula repository. */
	@ManagedProperty(value = "#{caratulaRepository}")
	private CaratulaRepository caratulaRepository;

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct Caratula ");
		listCaratula = this.reloadData();
		caratulaDataModel = new DataModelGeneric<Caratula>(listCaratula);

	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina caratulaMB  ");
		this.caratulaDataModel.setListT(reloadData());

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

	public List<Caratula> reloadData() {
		listCaratula = this.caratulaRepository.findAll();
		for (Caratula cara : listCaratula) {
			cara.setScta(StringUtils.stripStart(cara.getScta(), BigInteger.ZERO.toString()));
		}
		return listCaratula;
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

	/**
	 * Gets the selected caratula.
	 *
	 * @return the selected caratula
	 */
	public Caratula getSelectedCaratula() {
		return selectedCaratula;
	}

	public void setCaratulaDataModel(DataModelGeneric<Caratula> caratulaDataModel) {
		this.caratulaDataModel = caratulaDataModel;
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
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Información!", "¡Edición cancelada!");
		listCaratula = this.reloadData();
		this.caratulaDataModel.setListT(listCaratula);
	}

	public void onInitRowEdit(final RowEditEvent event) {
		selectedCaratula = (Caratula) event.getObject();
		if (null != event.getObject()) {

			if (null != selectedCaratula.getId() && selectedCaratula.getId() != 0) {
				this.bEdicion = Boolean.TRUE;
			} else {
				this.bEdicion = Boolean.FALSE;
			}
			// this.activateRowEditFlow(selectedCaratula.getIndex());
		}
	}

	public void addRow() {
		selectedCaratula = new Caratula();

		selectedCaratula.setCuenta(StringUtils.EMPTY);
		selectedCaratula.setScta(StringUtils.EMPTY);
		selectedCaratula.setNombre(StringUtils.EMPTY);
		selectedCaratula.setSscta(StringUtils.EMPTY);
		selectedCaratula.setSsscta(StringUtils.EMPTY);
		selectedCaratula.setSssscta(StringUtils.EMPTY);
		selectedCaratula.setAutoi1(BigDecimal.ZERO);
		selectedCaratula.setAutoi2(BigDecimal.ZERO);
		selectedCaratula.setAutoi3(BigDecimal.ZERO);

		listCaratula.add(BigDecimal.ZERO.intValue(), selectedCaratula);
		caratulaDataModel.setListT(listCaratula);
		this.activateRowEditFlow(BigDecimal.ZERO.intValue());

	}

	public void onRowEdit(RowEditEvent event) {
		selectedCaratula = (Caratula) event.getObject();
		selectedCaratula.setScta(StringUtils.leftPad(selectedCaratula.getScta(), 10, "0"));
		selectedCaratula.setIdRef(BigInteger.ZERO.longValue());
		selectedCaratula.setSectorid(BigInteger.ONE.intValue());

		if (bEdicion) {
			this.caratulaRepository.save(selectedCaratula);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Información!",
					"¡El registro se ha actualizado correctamente!");
		} else {
			Caratula valida = this.caratulaRepository.findOne(
					CaratulaPredicates.existsAccountAndScta(selectedCaratula.getCuenta(), selectedCaratula.getScta()));

			if (null == valida) {
				this.caratulaRepository.save(selectedCaratula);
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Información!",
						"¡El registro se ha guardado correctamente!");
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Información!", "¡La Cuenta y Scta ya existe!");
				this.activateRowEditFlow(BigInteger.ZERO.intValue());
			}

		}

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

	public void activateRowEditFlow(final int index) {
		LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		// text.append(index);
		// text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		text.append(" ");
		text.append(String.format(FOCUS_BY_ROWID, index));
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	public Boolean getbEdicion() {
		return bEdicion;
	}

	public void setbEdicion(Boolean bEdicion) {
		this.bEdicion = bEdicion;
	}

	public List<Caratula> getListCaratula() {
		return listCaratula;
	}

	public void setListCaratula(List<Caratula> listCaratula) {
		this.listCaratula = listCaratula;
	}

}
