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

import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteEgresosGlobalMB.
 */
@ManagedBean(name = "reporteEgresosGlobalMB")
@ViewScoped
public class ReporteEgresosGlobalMB extends GenericExecuteProcedure {

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_REPORTE_RF0091202";

	/** The Constant AFFECTED_POLICY_ST. */
	private static final String AFFECTED_POLICY_ST = "A";

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant FOCUS_IN_PREVIEW. */
	private static final String FOCUS_IN_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";

	/** The mes. */
	private String mes;

	/** The n firmas. */
	private Integer nFirmas;

	/** The list mes. */
	private List<TcMes> listMes;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The polien repository. */
	@ManagedProperty("#{polienRepository}")
	private PolienRepository polienRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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

	/** The out. */
	Map<String, Object> out;

	/** The stream. */
	InputStream stream = null;

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
		parameter.addValue("i_firmas", nFirmas);
		parameter.addValue("i_id_sector", this.getUserDetails().getIdSector());

		return parameter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		stream = null;
		fileTxt = null;
		if (this.validatePolicyService.isOpenMonth(Integer.valueOf(mes), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(Integer.valueOf(mes), null, this.getUserDetails().getIdSector());

			out = executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
			if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
				try {
					stream = new FileInputStream(
							new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
				this.monthAfected(Integer.valueOf(mes));
				generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());

			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
			}
		}
	}

	/**
	 * Send message.
	 */
	public void sendMessage() {
		this.monthAfected(Integer.valueOf(mes));
	}

	/**
	 * Month afected.
	 *
	 * @param month
	 *            the month
	 */
	public void monthAfected(Integer month) {
		Integer isAffectedMonth = this.polienRepository.isMonthAfect(Integer.valueOf(mes),
				this.getUserDetails().getIdSector(), AFFECTED_POLICY_ST);
		if (isAffectedMonth > 0) {
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", "El mes esta afectado");
		}
	}

	/**
	 * Pre view.
	 */
	public void preView() {
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
			this.monthAfected(Integer.valueOf(mes));
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
	 * @param mes
	 *            the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the n firmas.
	 *
	 * @return the n firmas
	 */
	public Integer getnFirmas() {
		return nFirmas;
	}

	/**
	 * Sets the n firmas.
	 *
	 * @param nFirmas
	 *            the new n firmas
	 */
	public void setnFirmas(Integer nFirmas) {
		this.nFirmas = nFirmas;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<TcMes> getListMes() {
		return listMes;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes
	 *            the new list mes
	 */
	public void setListMes(List<TcMes> listMes) {
		this.listMes = listMes;
	}

	/**
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository
	 *            the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
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
	 * @param tcMesRepository
	 *            the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
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
	 * @param fileTxt
	 *            the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/**
	 * Gets the txt preview.
	 *
	 * @return the txt preview
	 */
	public StringBuilder getTxtPreview() {
		return txtPreview;
	}

	/**
	 * Sets the txt preview.
	 *
	 * @param txtPreview
	 *            the new txt preview
	 */
	public void setTxtPreview(StringBuilder txtPreview) {
		this.txtPreview = txtPreview;
	}

	/**
	 * Gets the b pre view.
	 *
	 * @return the b pre view
	 */
	public Boolean getbPreView() {
		return bPreView;
	}

	/**
	 * Sets the b pre view.
	 *
	 * @param bPreView
	 *            the new b pre view
	 */
	public void setbPreView(Boolean bPreView) {
		this.bPreView = bPreView;
	}

	/**
	 * Gets the generate preview service.
	 *
	 * @return the generate preview service
	 */
	public GeneratePreviewService getGeneratePreviewService() {
		return generatePreviewService;
	}

	/**
	 * Sets the generate preview service.
	 *
	 * @param generatePreviewService
	 *            the new generate preview service
	 */
	public void setGeneratePreviewService(GeneratePreviewService generatePreviewService) {
		this.generatePreviewService = generatePreviewService;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
