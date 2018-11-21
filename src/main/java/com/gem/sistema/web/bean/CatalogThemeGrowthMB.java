package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.EMPTY;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.repository.catalogs.CpdRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogThemeGrowthMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogThemeGrowthMB extends CommonCatalogMB<Cpd> implements Serializable {

	/** Serial default. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogThemeGrowthMB.class);

	/** Componente de servicio. */
	@ManagedProperty(value = "#{cpdRepository}")
	private CpdRepository cpdRepository;

	/** Campo requerido Clave de Contrato. */
	// @Value("${catalog.message.field.required.pe.nope.tema}")
	private static final String FIELD_REQUIRED_CONCAT_PE_NOPE_TEMA = FrontProperty
			.getPropertyValue("catalog.message.field.required.pe.nope.tema");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.temasDesarrollo}")
	protected String REPORT_NAME_PLAIN_TEXT = FrontProperty.getPropertyValue("file.name.report.txt.temasDesarrollo");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.theme.growth}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.theme.growth");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_theme_growth}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_theme_growth");

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");

	/** The csv by pl. */
	private StreamedContent csvByPl;

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_CSV_PLAIN = "Cve Temas,Descripción,Pilar/Eje,No. Pilar/Eje,Tema";

	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_CSV = "catalogThemeGrowth.csv";

	/** Query del reporte en csv by PL-sql. */
	protected static final String QUERY_STRUCTURA = "select cvetemas || ',\"' || descripcion || '\",' || pe || ', '''|| nope || CASE WHEN NVL(tema,'') <> '' THEN ',''' || tema ELSE ',' END from cpd";

	/**
	 * Sets the cpd repository.
	 *
	 * @param cpdRepository the new cpd repository
	 */
	public void setCpdRepository(CpdRepository cpdRepository) {
		this.cpdRepository = cpdRepository;
	}

	/** Campo por el que se ordenará la consulta de datos. */
	private static final String ORDER_BY_FIELD_CVETEMAS = "cvetemas";

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogThemeGrowthMB " + this + "::" + getBeanFind() + "::" + getList());
		this.setBeanFind(new Cpd());
		setList(new ArrayList<Cpd>());
		setListNew(new ArrayList<Cpd>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		sSqlCsv = QUERY_STRUCTURA;
		AND_ID_SECTOR= "";
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogThemeGrowthMB  ");
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogThemeGrowthMB  " + event.getObject());
		final Cpd catalog = (Cpd) event.getObject();
		final StringBuilder errorMsg = new StringBuilder();
		if (isValidFieldNoPeAndTema(catalog.getNope(), catalog.getTema(), errorMsg) && validateSaveOrUpdate(catalog)) {

			catalog.setCvetemas((StringUtils.isEmpty(catalog.getPe()) ? EMPTY : catalog.getPe()) + catalog.getNope()
					+ (StringUtils.isEmpty(catalog.getTema()) ? EMPTY : catalog.getTema()));

			executeOperationSaveOrUpdate(catalog, cpdRepository);
		} else {
			if (errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
						.concat(FIELD_REQUIRED_CONCAT_PE_NOPE_TEMA).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
			}
			activateRowEdit(catalog.getIndex());
		}
	}

	/**
	 * Checks if is valid field no pe and tema.
	 *
	 * @param nope the nope
	 * @param tema the tema
	 * @param errorMsg the error msg
	 * @return true, if is valid field no pe and tema
	 */
	private boolean isValidFieldNoPeAndTema(final String nope, final String tema, final StringBuilder errorMsg) {
		final boolean result;
		if (StringUtils.isNotEmpty(nope) && BooleanUtils.negate(nope.length() == 2)) {
			result = Boolean.FALSE;
			errorMsg.append("El campo No. Pilar/Eje debe ser vacio o con una longitud de 2");
		} else if (StringUtils.isNotEmpty(tema) && BooleanUtils.negate(tema.length() == 2)) {
			result = Boolean.FALSE;
			errorMsg.append("El campo Tema debe ser vacio o con una longitud de 2");
		} else {
			result = Boolean.TRUE;
		}
		return result;
	}

	/**
	 * Validate save or update.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateSaveOrUpdate(final Cpd catalog) {
		final List<Cpd> validateUnique = cpdRepository.findByPeAndNopeAndTema(catalog.getPe(), catalog.getNope(),
				catalog.getTema());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogThemeGrowthMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			cpdRepository.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		restartDataOperationDelete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(cpdRepository.findAll());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogMaintenanceCatalogFlowMB: {} ", this);
		addList((List<Cpd>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), ORDER_BY_FIELD_CVETEMAS));
		LOGGER.info(":: Resultado de busqueda catalogMaintenanceCatalogFlowMB {} ", getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#isValidRequest()
	 */
	@Override
	protected boolean isValidRequest() {
		return Boolean.TRUE;
	}

	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		LOGGER.info(":: Generar reporte de PDF ");
		return super.getFilePdf(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		return super.getFileXls(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#addNewOriginalList()
	 */
	@Override
	protected void addNewOriginalList() {

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		restartData();
		super.onRowCancel(event);
	}

	/**
	 * Gets the csv by pl.
	 *
	 * @return the csv by pl
	 */
	public StreamedContent getCsvByPl() {
		return csvByPl;
	}

	/**
	 * Sets the csv by pl.
	 *
	 * @param csvByPl the new csv by pl
	 */
	public void setCsvByPl(StreamedContent csvByPl) {
		this.csvByPl = csvByPl;
	}

	/** The stream. */
	InputStream stream = null;
	
	/** The out. */
	Map<String, Object> out;

	/**
	 * Gets the file csv pl.
	 *
	 * @return the file csv pl
	 */
	public void getFileCsvPl() {

		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_header", HEADERS_REPORT_CSV_PLAIN).addValue("i_query", this.getFileCsvByPl())
				.addValue("i_file_name", REPORT_NAME_PLAIN_CSV);

		out = new HashMap<String, Object>();
		out = this.getExecutePlService().callProcedure(PROCEDURE_NAME, parametros);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(new File(out.get("O_FULL_FILE_PATH").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			csvByPl = new DefaultStreamedContent(stream, "application/csv", out.get("O_FULL_FILE_PATH").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSJ_ERROR").toString());
		}

	}

}
