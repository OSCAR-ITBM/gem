package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0010_2_19_3MB.
 *
 * @author Alfredo
 */
@ManagedBean
@ViewScoped
public class RF0010_2_19_3MB extends GenericExecuteProcedure {
	
	/** The mes. */
	private String mes;
	
	/** The file txt. */
	private StreamedContent fileTxt;
	
	/** The list tc mes. */
	private List<TcMes> listTcMes;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		procedureName = "SP_GENERA_TXT_RECAUDACION_IMPUESTO_PREDIAL";
		
		listTcMes = tcMesRepository.findAll();
		
		if (!CollectionUtils.isEmpty(listTcMes)) {
			mes = listTcMes.get(0).getMes();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		
		parametros.addValue("i_mes", mes);
		parametros.addValue("i_idsector", this.getUserDetails().getIdSector());
		
		return parametros;
	}

	/** The stream. */
	InputStream stream = null;
	
	/** The out. */
	Map<String, Object> out;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		out = new HashMap<String, Object>();
		out = executePlService.callProcedure(procedureName, getParametersReports());

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		}
		
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
	 * Gets the file txt.
	 *
	 * @return the file txt
	 */
	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	/**
	 * Sets the file txt.
	 *
	 * @param fileTxt the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * Gets the list tc mes.
	 *
	 * @return the list tc mes
	 */
	public List<TcMes> getListTcMes() {
		return listTcMes;
	}

	/**
	 * Sets the list tc mes.
	 *
	 * @param listTcMes the new list tc mes
	 */
	public void setListTcMes(List<TcMes> listTcMes) {
		this.listTcMes = listTcMes;
	}

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

}
