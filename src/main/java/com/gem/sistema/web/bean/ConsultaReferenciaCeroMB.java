package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

/**
 * @author Alfredo
 *
 */
@ManagedBean(name = "consultaReferenciaCeroMB")
@ViewScoped
public class ConsultaReferenciaCeroMB extends GenericExecuteProcedure {
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_REFERENCIA_CERO";

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant FOCUS_IN_PREVIEW. */
	private static final String FOCUS_IN_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";

	/** The mes. */
	private String mes;

	/** The direccion. */
	private String direccion;

	/** The partida ini. */
	private String partidaIni;

	/** The partida fin. */
	private String partidaFin;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		listMes = tcMesRepository.findAll();

		if (!CollectionUtils.isEmpty(listMes)) {
			mes = listMes.get(0).getMes();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", Integer.valueOf(mes));
		parameter.addValue("i_direccion", direccion + "%");
		parameter.addValue("i_partidaI", partidaIni);
		parameter.addValue("i_partidaF", partidaFin);
		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());

		return parameter;
	}

	/** The out. */
	Map<String, Object> out;

	/** The stream. */
	InputStream stream = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		if (validatePartidas()) {
			out = executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
			if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
				try {
					stream = new FileInputStream(
							new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
			}
		}
	}

	/**
	 * Pre view.
	 */
	public void preView() {
		if (validatePartidas()) {
			File newFile;
			File target;
			String fileNameOut = "";
			this.setbPreView(Boolean.TRUE);
			try {
				out = this.executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
			} catch (ReportValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Integer.valueOf(out.get("O_COD_ERROR").toString()) == 0) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, out.get("O_MSG_ERROR").toString());
			} else {
				fileNameOut = out.get("O_RUTA_FILE").toString() + UUID.randomUUID() + ".txt";
				newFile = new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString());
				target = new File(fileNameOut);

				try {
					ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				txtPreview = this.generatePreviewService.generatePreview(fileNameOut);
				RequestContext.getCurrentInstance().execute(FOCUS_IN_PREVIEW);
			}
		}
	}

	/**
	 * @return
	 */
	public boolean validatePartidas() {
		if (Integer.valueOf(partidaIni) > Integer.valueOf(partidaFin)) {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"La Partida Inical debe ser menor que la Partida Final");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @return
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return
	 */
	public String getPartidaIni() {
		return partidaIni;
	}

	/**
	 * @param partidaIni
	 */
	public void setPartidaIni(String partidaIni) {
		this.partidaIni = partidaIni;
	}

	/**
	 * @return
	 */
	public String getPartidaFin() {
		return partidaFin;
	}

	/**
	 * @param partidaFin
	 */
	public void setPartidaFin(String partidaFin) {
		this.partidaFin = partidaFin;
	}

	/**
	 * @return
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * @param listMes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	/**
	 * @return
	 */
	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	/**
	 * @param fileTxt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * @return
	 */
	public StringBuilder getTxtPreview() {
		return txtPreview;
	}

	/**
	 * @param txtPreview
	 */
	public void setTxtPreview(StringBuilder txtPreview) {
		this.txtPreview = txtPreview;
	}

	/**
	 * @return
	 */
	public Boolean getbPreView() {
		return bPreView;
	}

	/**
	 * @param bPreView
	 */
	public void setbPreView(Boolean bPreView) {
		this.bPreView = bPreView;
	}

	/**
	 * @return
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * @param tcMesRepository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * @return
	 */
	public GeneratePreviewService getGeneratePreviewService() {
		return generatePreviewService;
	}

	/**
	 * @param generatePreviewService
	 */
	public void setGeneratePreviewService(GeneratePreviewService generatePreviewService) {
		this.generatePreviewService = generatePreviewService;
	}

}
