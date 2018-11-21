package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static java.util.stream.Collectors.toList;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.gem.sistema.business.domain.Bienesm;
import com.gem.sistema.business.repository.catalogs.BienesmRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;

import javassist.expr.NewArray;

// TODO: Auto-generated Javadoc
/**
 * The Class InventarioGeneralBienesMueblesCargaAutomaticaMB.
 */
@ManagedBean
@ViewScoped
public class InventarioGeneralBienesMueblesCargaAutomaticaMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The repository. */
	@ManagedProperty("#{bienesmRepository}")
	private BienesmRepository repository;

	/** The al mes. */
	private Integer alMes;
	
	/** The archivo. */
	private UploadedFile archivo;
	
	/** The lista from CSV. */
	private List<Bienesm> listaFromCSV;
	
	/** The log. */
	private static String log;
	
	/** The upload file. */
	private Boolean uploadFile;
	
	/** The b valida csv. */
	private static Boolean bValidaCsv = Boolean.FALSE;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct InventarioGeneralBienesMueblesCargaAutomaticaMB ");
		jasperReporteName = "";
		endFilename = jasperReporteName + ".pdf";
		uploadFile = Boolean.FALSE;
		bValidaCsv = Boolean.FALSE;
		alMes = 1;
		log = "";

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Handle file upload.
	 *
	 * @param event the event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		this.archivo = event.getFile();
		if (archivo.getSize() != Long.valueOf(0).longValue()) {
			BufferedInputStream bis;
			try {
				bis = new BufferedInputStream(archivo.getInputstream());
				listaFromCSV = csvReadingAndParse(bis);
				if (bValidaCsv == Boolean.TRUE) {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
							"El achivo contiene errores.");
					FacesContext.getCurrentInstance().addMessage(null, message);
					bValidaCsv = Boolean.FALSE;
					uploadFile = Boolean.FALSE;
				} else {
					uploadFile = Boolean.TRUE;

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY,
					"El achivo esta vacio.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			bValidaCsv = Boolean.FALSE;
			uploadFile = Boolean.FALSE;
		}
	}

	/**
	 * Csv reading and parse.
	 *
	 * @param is the is
	 * @return the list
	 */
	public List<Bienesm> csvReadingAndParse(InputStream is) {
		List<Bienesm> retorno = new ArrayList<Bienesm>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"));
			retorno = br.lines()/* .skip(1) */.map(mapToObject).filter(p -> {
				// p.setMes(alMes);
				p.setIdsector(getUserDetails().getIdSector());
				p.setCapturo(getUserDetails().getUsername());
				p.setFeccap(new Date());
				p.setIdRef(getUserDetails().getIdSector() - 1l);
				p.setUserid(getUserDetails().getUsername());
				return Objects.nonNull(p.getConsec()) && p.getConsec() > 0;
			}).collect(toList());
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	/** The map to object. */
	private static Function<String, Bienesm> mapToObject = (line) -> {
		Bienesm retorno = new Bienesm();
		String[] p = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		if (p.length == 29) {
			retorno.setMes(toInteger(p[0]));
			retorno.setConsec(toInteger(p[1]));
			retorno.setCuenta(toString(p[2]));
			retorno.setScta(toString(p[3]));
			retorno.setInventario(toString(p[4]));
			retorno.setResguardo(toString(p[5]));
			retorno.setResguardatario(toString(p[6]));
			retorno.setNommueble(toString(p[7]));
			retorno.setMarca(toString(p[8]));
			retorno.setModelo(toString(p[9]));
			retorno.setMotor(toString(p[10]));
			retorno.setSerie(toString(p[11]));
			retorno.setEstado(toString(p[12]));
			retorno.setFactura(toString(p[13]));
			retorno.setFecfactura(toDate(p[14]));
			retorno.setProveedor(toString(p[15]));
			retorno.setCosto(toBigDecimal(p[16]));
			retorno.setTippol(toString(p[17]));
			retorno.setConpol(toInteger(p[18]));
			retorno.setFecpol(toDate(p[19]));
			retorno.setRecurso(toString(p[20]));
			retorno.setFecalta(toDate(p[21]));
			retorno.setFecbaja(toDate(p[22]));
			retorno.setArea(toString(p[23]));
			retorno.setLocalidad(toString(p[24]));
			retorno.setObs(toString(p[25]));
			retorno.setTvidautil(toString(p[26]));
			retorno.setDepreperiodo(toBigDecimal(p[27]));
			retorno.setDepreacum(toBigDecimal(p[28]));
		} else {
			bValidaCsv = Boolean.TRUE;
		}
		// Si son nulls asigna valores default
		retorno = checkNullsAndSetDefaultValues(retorno);
		retorno = leftPad(retorno);

		return retorno;
	};

	/**
	 * To string.
	 *
	 * @param value the value
	 * @return the string
	 */
	private static String toString(String value) {
		String retorno = "";
		try {
			if (Objects.nonNull(value)) {
				retorno = value.trim().replaceAll("\"", "");
			}
		} catch (NumberFormatException e) {
			log += " Campo entero: " + value + " error en formato.\n";
			e.printStackTrace();
			bValidaCsv = Boolean.TRUE;
		}
		return retorno;
	}

	/**
	 * To integer.
	 *
	 * @param value the value
	 * @return the integer
	 */
	private static Integer toInteger(String value) {
		Integer retorno = 0;
		try {
			if (Objects.nonNull(value)) {
				retorno = Integer.parseInt(value.replaceAll(" ", ""));
			}
		} catch (NumberFormatException e) {
			log += " Campo entero: " + value + " error en formato.\n";
			e.printStackTrace();
			bValidaCsv = Boolean.TRUE;
		}
		return retorno;
	}

	/**
	 * To big decimal.
	 *
	 * @param value the value
	 * @return the big decimal
	 */
	private static BigDecimal toBigDecimal(String value) {
		BigDecimal retorno = BigDecimal.ZERO;
		try {
			if (Objects.nonNull(value)) {
				retorno = new BigDecimal(value.replaceAll(" ", ""));
			}
		} catch (Exception e) {
			log += " Campo decimal: " + value + " error en formato.\n";
			e.printStackTrace();
			bValidaCsv = Boolean.TRUE;
		}
		return retorno;
	}

	/**
	 * To date.
	 *
	 * @param value the value
	 * @return the date
	 */
	private static Date toDate(String value) {
		Date retorno = new Date();
		try {
			if (Objects.nonNull(value)) {
				value = value.replaceAll(" ", "").replaceAll("\"", "");
				String separator = value.indexOf("-") != -1 ? "-" : "/";
				String[] strDate = value.split(separator);
				LocalDate dateTime = LocalDate.of(toInteger(strDate[2]), toInteger(strDate[1]), toInteger(strDate[0]));
				retorno = java.sql.Date.valueOf(dateTime);
			}
		} catch (Exception e) {
			log += " Campo fecha: " + value + " error en formato.\n";
			e.printStackTrace();
			bValidaCsv = Boolean.TRUE;
		}
		return retorno;
	}

	/**
	 * Left pad.
	 *
	 * @param object the object
	 * @return the bienesm
	 */
	private static Bienesm leftPad(Bienesm object) {
		String value = object.getScta();
		if (StringUtils.isNotEmpty(value)) {
			object.setScta(StringUtils.leftPad(value, 10, StringUtils.EMPTY + ZERO));
		}

		value = object.getSscta();
		if (StringUtils.isNotEmpty(value)) {
			object.setSscta(StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO));
		}

		value = object.getSsscta();
		if (StringUtils.isNotEmpty(value)) {
			object.setSsscta(StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO));
		}

		value = object.getSssscta();
		if (StringUtils.isNotEmpty(value)) {
			object.setSssscta(StringUtils.leftPad(value, 3, StringUtils.EMPTY + ZERO));
		}
		return object;
	}

	/**
	 * Check nulls and set default values.
	 *
	 * @param <T> the generic type
	 * @param object the object
	 * @return the t
	 */
	/*
	 * Si son nulls asigna valores default
	 */
	private static <T> T checkNullsAndSetDefaultValues(T object) {
		try {
			Class<?> clazz = object.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method : methods) {
				boolean isGetterMhetodType = method.getName().startsWith("get") || method.getName().startsWith("is");
				if (isGetterMhetodType) {
					Object fieldValue = method.invoke(object);
					if (Objects.isNull(fieldValue)) {
						String setMethodName = method.getName().replace("get", "");
						setMethodName = setMethodName.substring(0, 1).toLowerCase()
								+ setMethodName.substring(1, setMethodName.length());
						Field field = clazz.getDeclaredField(setMethodName);
						field.setAccessible(true);
						if (method.getReturnType().equals(Integer.class)) {
							field.set(object, 0);
						} else if (method.getReturnType().equals(String.class)) {
							field.set(object, "");
						} else if (method.getReturnType().equals(BigDecimal.class)) {
							field.set(object, BigDecimal.ZERO);
						} else if (method.getReturnType().equals(Long.class)) {
							field.set(object, 0l);
						} else if (method.getReturnType().equals(Date.class)) {
							field.set(object, new Date());
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	/** The msg. */
	String msg = "Los registros se han guardado con éxito!";

	/**
	 * Salvar.
	 */
	public void salvar() {

		try {
			List<Bienesm> toRemove = new ArrayList<Bienesm>();
			toRemove = repository.findByIdsectorAndMes(this.getUserDetails().getIdSector(), alMes);
			repository.delete(toRemove);
			listaFromCSV.stream().forEach(p -> {
				// Actualiza mes por si fue cambiado
				// p.setMes(alMes);

				Bienesm bienesm = repository.findByIdsectorAndMesAndConsec(getUserDetails().getIdSector(), p.getMes(),
						p.getConsec());
				// Verifica si registro ya existe
				if (Objects.nonNull(bienesm)) {
					log += " Eliminando registro con id: " + bienesm.getId();
					repository.delete(bienesm);
				}
			});
			System.out.println(listaFromCSV);
			repository.save(listaFromCSV);
			uploadFile = Boolean.FALSE;
			archivo = null;
			msg = "Registros guardados correctamente.";
		} catch (Exception e) {
			msg = "Hubo errores al intentar guardar los registros, revisar combinación del mes con el consecutivo.";
			e.printStackTrace();
			archivo = null;

		}
		System.out.println(log);
		RequestContext.getCurrentInstance().execute("unblock();");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Go to message.
	 */
	public void goToMessage() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, msg);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	// getters and setters

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
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
	 * Gets the repository.
	 *
	 * @return the repository
	 */
	public BienesmRepository getRepository() {
		return repository;
	}

	/**
	 * Sets the repository.
	 *
	 * @param repository the new repository
	 */
	public void setRepository(BienesmRepository repository) {
		this.repository = repository;
	}

	/**
	 * Gets the archivo.
	 *
	 * @return the archivo
	 */
	public UploadedFile getArchivo() {
		return archivo;
	}

	/**
	 * Sets the archivo.
	 *
	 * @param archivo the new archivo
	 */
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	/**
	 * Gets the al mes.
	 *
	 * @return the al mes
	 */
	public Integer getAlMes() {
		return alMes;
	}

	/**
	 * Sets the al mes.
	 *
	 * @param alMes the new al mes
	 */
	public void setAlMes(Integer alMes) {
		this.alMes = alMes;
	}

	/**
	 * Gets the upload file.
	 *
	 * @return the upload file
	 */
	public Boolean getUploadFile() {
		return uploadFile;
	}

	/**
	 * Sets the upload file.
	 *
	 * @param uploadFile the new upload file
	 */
	public void setUploadFile(Boolean uploadFile) {
		this.uploadFile = uploadFile;
	}

	/**
	 * Gets the b valida csv.
	 *
	 * @return the b valida csv
	 */
	public static Boolean getbValidaCsv() {
		return bValidaCsv;
	}

	/**
	 * Sets the b valida csv.
	 *
	 * @param bValidaCsv the new b valida csv
	 */
	public static void setbValidaCsv(Boolean bValidaCsv) {
		InventarioGeneralBienesMueblesCargaAutomaticaMB.bValidaCsv = bValidaCsv;
	}

	/**
	 * Btn save.
	 *
	 * @return the boolean
	 */
	public Boolean btnSave() {
		Boolean bln = new Boolean(Boolean.TRUE);
		if (archivo != null && uploadFile)
			bln = Boolean.FALSE;

		return bln;
	}
}
