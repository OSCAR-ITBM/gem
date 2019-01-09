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

@ManagedBean(name = "aTMActividadProyecoMB")
@ViewScoped
public class ATMActividadProyecoMB extends AbstractMB {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";
	
	private List<TcPeriodo> listTrimestres;
	private Integer trimestre;
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
		listTrimestres = tcPeriodoRepository.findByTipoPeriodo(3);

		if (!listTrimestres.isEmpty()) {
			trimestre = listTrimestres.get(0).getPeriodo();
		}

	}

	public void downloadTxt() {
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		nameFile = "AM" + conctb.getClave() + conctb.getAnoemp() + "0" + trimestre + ".txt";

		try {
			this.generateFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateFile() {
		pathName = this.reporteGenericoService
				.getFileTxtWithSql(this.generateSql(trimestre, this.getUserDetails().getIdSector()), nameFile);

		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = new DefaultStreamedContent(stream, "application/txt", pathName.substring(13));

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Generó el Archivo: " + pathName.substring(13));
	}

	private String generateSql(Integer trimestre, Integer idSector) {

		StringBuilder sSql = new StringBuilder();
		String canAvan = "(";

		for (int y = 1; y <= trimestre; y++) {
			canAvan = canAvan + " RES.CANT_AVAN_" + y + " +";
		}

		canAvan = canAvan.substring(0, canAvan.length() - 2) + " ) ACUMULADO ";

		sSql.append("SELECT RES2.UNO|| RES2.PORCENTAJE|| '\"|\"'|| RES2.ACUMULADO|| '\"|\"'||")
				.append("(RES2.PORCENTAJE-RES2.ACUMULADO)|| '\"|\"'||")
				.append("FN_GET_FORMAT_NUMBER(DECODE(RES2.PORCENTAJE,'0','0',(RES2.PORCENTAJE/RES2.ACUMULADO)*100))|| '\"' ")
				.append("FROM (SELECT  '\"'|| RES.LOCBEN || '\"|\"'|| RES.POBBEN || '\"|\"'|| RES.CD1 || '\"|\"'|| ")
				.append("RES.CD2|| '\"|\"'|| RES.CN1|| '\"|\"'|| RES.CN2|| '\"|\"'|| RES.CN3|| '\"|\"'|| ")
				.append("RES.CN4|| '\"|\"'|| RES.CN5|| '\"|\"'|| RES.CN6|| '\"|\"'|| RES.CLVMET|| '\"|\"'|| ")
				.append("RES.NOM_IND|| '\"|\"'|| RES.UNI_MED|| '\"|\"'|| RES.CAN_METI|| '\"|\"'|| ")
				.append("RES.CAN_METIC|| '\"|\"'|| RES.CANT_AVAN_").append(trimestre).append("|| '\"|\"'|| ")
				.append("FN_GET_FORMAT_NUMBER(RES.CAN_METIC-RES.CANT_AVAN_").append(trimestre)
				.append(")|| '\"|\"' UNO, ")
				.append("FN_GET_FORMAT_NUMBER(DECODE(RES.CAN_METIC,'0','0',((RES.CANT_AVAN_").append(trimestre)
				.append(" *100)/ (RES.CAN_METIC)))) PORCENTAJE,").append(canAvan)
				.append(" FROM (SELECT NVL(PN.LOCBEN,'')LOCBEN, PN.POBBEN , SUBSTR(PM.CLVDEP,1,3)CD1, ")
				.append("SUBSTR(PM.CLVDEP,4,3)CD2, SUBSTR(PM.CLVNEP,1,2)CN1, SUBSTR(PM.CLVNEP,3,2)CN2, ")
				.append("SUBSTR(PM.CLVNEP,5,2)CN3, SUBSTR(PM.CLVNEP,7,2)CN4, SUBSTR(PM.CLVNEP,9,2)CN5, ")
				.append("SUBSTR(PM.CLVNEP,11,2)CN6, PM.CLVMET, PM.NOM_IND, NVL(PM.UNI_MED,'')UNI_MED, ")
				.append("FN_GET_FORMAT_NUMBER(PM.CAN_METI) CAN_METI, FN_GET_FORMAT_NUMBER(PM.CAN_METIC_")
				.append(trimestre).append(") CAN_METIC,  FN_GET_FORMAT_NUMBER(PM.CANT_AVAN_1) CANT_AVAN_1, ")
				.append("FN_GET_FORMAT_NUMBER(PM.CANT_AVAN_2) CANT_AVAN_2, ")
				.append("FN_GET_FORMAT_NUMBER(PM.CANT_AVAN_3) CANT_AVAN_3, ")
				.append("FN_GET_FORMAT_NUMBER(PM.CANT_AVAN_4) CANT_AVAN_4 ")
				.append("FROM PP_METT PM,PROGRAMAMUN PN WHERE PN.IDSECTOR = PM.IDSECTOR AND ")
				.append("SUBSTR(PM.CLVDEP,1,3) = PN.CVEDEPG AND SUBSTR(PM.CLVDEP,4,3) = PN.CVEDEPA AND ")
				.append("PM.CLVNEP = PN.PROGRAMA AND PN.IDSECTOR= ").append(idSector)
				.append(" ORDER BY 1,2,3,4 ) RES )RES2");

		return sSql.toString();
	}

	public List<TcPeriodo> getListTrimestres() {
		return listTrimestres;
	}

	public void setListTrimestres(List<TcPeriodo> listTrimestres) {
		this.listTrimestres = listTrimestres;
	}

	public Integer getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
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
