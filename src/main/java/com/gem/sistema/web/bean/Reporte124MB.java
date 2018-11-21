package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte124MB.
 */
@ManagedBean
@ViewScoped
public class Reporte124MB extends GenericExecuteProcedure {
	
	/** The semestre. */
	private String semestre;
	
	/** The list. */
	private List<String> list;
	
	/** The file txt. */
	private StreamedContent fileTxt;

	/**
	 * Gets the semestre.
	 *
	 * @return the semestre
	 */
	public String getSemestre() {
		return semestre;
	}

	/**
	 * Sets the semestre.
	 *
	 * @param semestre the new semestre
	 */
	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(List<String> list) {
		this.list = list;
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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		procedureName = "SP_GENERA_TXT124";

		list = new ArrayList<String>();

		for (int i = 1; i <= 2; i++)
			list.add("0" + i);

		semestre = list.get(0);
	}

	/**
	 * Mes by semestre.
	 *
	 * @param sem the sem
	 * @return the int
	 */
	public int mesBySemestre(Integer sem) {

		return (sem * 6);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {

		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_usuario", this.getUserDetails().getUsername())
				.addValue("i_semestre", Integer.valueOf(semestre))
				.addValue("i_mes", this.mesBySemestre(Integer.valueOf(semestre)))
				.addValue("i_lastDayOfMonth", getLastDay(this.mesBySemestre(Integer.valueOf(semestre))));

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

}
