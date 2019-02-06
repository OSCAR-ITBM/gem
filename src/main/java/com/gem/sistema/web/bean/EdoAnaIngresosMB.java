package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ReporteGenericoService;

@ManagedBean(name = "edoAnaIngresosMB")
@ViewScoped
public class EdoAnaIngresosMB extends AbstractMB {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	private List<TcPeriodo> listMeses;
	private Integer mes;
	private String nameFile;
	private String pathName;
	private InputStream stream = null;
	private StreamedContent file;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepository;

	@ManagedProperty("#{reporteGenericoService}")
	private ReporteGenericoService reporteGenericoService;
	
	@PostConstruct
	public void init() {
		listMeses  = tcPeriodoRepository.findByTipoPeriodo(1);
		
		if(!listMeses.isEmpty()) {
			mes =  listMeses.get(0).getPeriodo();
		}
	}

	public void downloadTxt() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		nameFile = "EAI" + conctb.getClave() + conctb.getAnoemp()
				+ org.apache.commons.lang3.StringUtils.leftPad(mes.toString(), 2, "0") + ".txt";

		try {
			this.generateFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateFile() {
		pathName = this.reporteGenericoService
				.getFileTxtWithSql(this.generateSql(this.getUserDetails().getIdSector(), mes), nameFile);

		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = new DefaultStreamedContent(stream, "application/txt", pathName.substring(13));

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Generó el Archivo: " + pathName.substring(13));
	}

	private String generateSql(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();

		String cargos = "(";
		String abonos = "(";
		String ingreso = "SUM(";

		for (int y = 1; y <= mes; y++) {
			cargos = cargos + " CU.CARGOS_" + y + " +";
			abonos = abonos + " CU.ABONOS_" + y + " +";
			ingreso = ingreso + " IG.AUTOI_" + y + " +";
		}

		cargos = cargos.substring(0, cargos.length() - 2) + " ) ";
		abonos = abonos.substring(0, abonos.length() - 2) + " ) ";
		ingreso = ingreso.substring(0, ingreso.length() - 2) + " ) ESTIMADO, ";

		sSql.append("SELECT '\"'||CONS.CUENTA||'\"|\"'||TRIM(LEADING '0' FROM CONS.SCTA) ||'\"|\"'||TRIM(BOTH '0' FROM CONS.SSCTA) ||'\"|\"'||")
				.append("TRIM(BOTH '0' FROM CONS.SSSCTA) || '\"|\"'||CONS.NOM_CUENTA|| '\"|\"'||")
				.append("FN_GET_FORMAT_NUMBER(CONS.ESTIMADO) || '\"|\"' ||")
				.append("FN_GET_FORMAT_NUMBER(CONS.AMP_REDU) || '\"|\"' ||")
				.append("FN_GET_FORMAT_NUMBER(CONS.ESTIMADO+CONS.AMP_REDU) || '\"|\"'||")
				.append("FN_GET_FORMAT_NUMBER(CONS.DEVENGADO )|| '\"|\"' ||")
				.append("FN_GET_FORMAT_NUMBER(CONS.RECAUDADO) || '\"|\"' ||")
				.append("FN_GET_FORMAT_NUMBER(CONS.RECAUDADO-(CONS.ESTIMADO+CONS.AMP_REDU))|| '\"' ")
				.append("FROM (SELECT CU.CUENTA CUENTA, CU.SCTA SCTA, CU.SSCTA SSCTA, CU.SSSCTA SSSCTA, ")
				.append("CU.NOMCTA NOM_CUENTA, ").append(ingreso).append("SUM(DECODE(CU.CUENTA, '8110', ")
				.append(cargos).append(" - ").append(abonos).append(", 0)) AMP_REDU, SUM(DECODE(CU.CUENTA, '8140', ")
				.append(cargos).append(" - ").append(abonos).append(", 0)) DEVENGADO, ")
				.append("SUM(DECODE(CU.CUENTA, '8150', ").append(cargos).append(" - ").append(abonos)
				.append(", 0)) RECAUDADO ")
				.append("FROM GEMUSER.CUENTA CU, GEMUSER.INGRESO IG WHERE CU.CUENTA  = IG.CUENTA ")
				.append("AND CU.IDSECTOR = IG.IDSECTOR AND CU.IDSECTOR = ").append(idSector)
				.append(" GROUP BY CU.CUENTA, CU.SCTA, CU.SSCTA, CU.SSSCTA, CU.NOMCTA ORDER BY 1, 2, 3, 4)CONS");

		return sSql.toString();
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getNameFile() {
		return nameFile;
	}

	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getTcPeriodoRepository() {
		return tcPeriodoRepository;
	}

	public void setTcPeriodoRepository(TcPeriodoRepositoy tcPeriodoRepository) {
		this.tcPeriodoRepository = tcPeriodoRepository;
	}

	public ReporteGenericoService getReporteGenericoService() {
		return reporteGenericoService;
	}

	public void setReporteGenericoService(ReporteGenericoService reporteGenericoService) {
		this.reporteGenericoService = reporteGenericoService;
	}

}
