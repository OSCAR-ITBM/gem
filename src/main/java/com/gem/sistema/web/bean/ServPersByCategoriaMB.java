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

@ManagedBean(name = "servPersByCategoriaMB")
@ViewScoped
public class ServPersByCategoriaMB extends AbstractMB {

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
		nameFile = "EAPECSPLDF" + conctb.getClave() + conctb.getAnoemp() + "0" + trimestre + ".txt";

		try {
			this.generateFile();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateFile() {
		pathName = this.reporteGenericoService
				.getFileTxtWithSql(this.generateSql(this.getUserDetails().getIdSector(), trimestre), nameFile);

		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = new DefaultStreamedContent(stream, "application/txt", pathName.substring(13));

		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Generó el Archivo: " + pathName.substring(13));
	}

	private String generateSql(Integer idSector, Integer trimestre) {
		int mesFinal = trimestre * 3;
		StringBuilder sSql = new StringBuilder();

		String aprobado = "SUM(";
		String pagado = "SUM(";
		String reduccion = "SUM(";
		String devengado = "SUM(";
		String ampliacion = "SUM(";

		for (int y = 1; y <= mesFinal; y++) {
			aprobado = aprobado + " PA.AUTO1_" + y + " +";
			pagado = pagado + " PA.EJPA1_" + y + " +";
			reduccion = reduccion + " PA.REDU1_" + y + " +";
			devengado = devengado + " PA.EJXPA1_" + y + " +";
			ampliacion = ampliacion + " PA.AMPLI1_" + y + " +";
		}

		aprobado = aprobado.substring(0, aprobado.length() - 2) + " ) APROBADO, ";
		pagado = pagado.substring(0, pagado.length() - 2) + " ) PAGADO ";
		reduccion = reduccion.substring(0, reduccion.length() - 2) + " ) REDUCCIONES, ";
		devengado = devengado.substring(0, devengado.length() - 2) + " ) DEVENGADO, ";
		ampliacion = ampliacion.substring(0, ampliacion.length() - 2) + " ) AMPLIACION, ";

		sSql.append("SELECT '\"'||'Gasto Etiquetado'||'\"|\"'||")
				.append("TRIM(TO_CHAR(SUM(T1.APROBADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T1.AMPLIACION -T1.REDUCCIONES)),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES)),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM(T1.DEVENGADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM(T1.PAGADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO),'999999999999990.99'))||'\"' ")
				.append("FROM (SELECT NAT.CLVGAS,NAT.NOMGAS, ").append(aprobado).append(ampliacion).append(reduccion)
				.append(devengado).append(pagado)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR ")
				.append("INNER JOIN FUENTEF FU ON FU.IDSECTOR = PA.IDSECTOR WHERE  PA.IDSECTOR = ").append(idSector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND SUBSTR(FU.CLVFTE,1,1)='1' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC	) T1 UNION ALL ")
				.append("SELECT '\"'||T1.CLVGAS|| ' ' ||T1.NOMGAS||'\"|\"'|| ")
				.append("TRIM( TO_CHAR(T1.APROBADO,'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR((T1.AMPLIACION -T1.REDUCCIONES),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR((T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(T1.DEVENGADO,'999999999999990.99')) ||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(T1.PAGADO,'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(((T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO),'999999999999990.99')) ||'\"'")
				.append("FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ").append(aprobado).append(ampliacion).append(reduccion)
				.append(devengado).append(pagado)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR ")
				.append("INNER JOIN FUENTEF FU ON FU.IDSECTOR = PA.IDSECTOR WHERE  PA.IDSECTOR = ").append(idSector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND SUBSTR(FU.CLVFTE,1,1)='1' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC	) T1 UNION ALL ")
				.append("SELECT '\"'||'Gasto No Etiquetado'||'\"|\"'||")
				.append("TRIM(TO_CHAR(SUM(T2.APROBADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T2.AMPLIACION -T2.REDUCCIONES)),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T2.APROBADO + T2.AMPLIACION -T2.REDUCCIONES)),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM(T2.DEVENGADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM(T2.PAGADO),'999999999999990.99'))||'\"|\"'|| ")
				.append("TRIM(TO_CHAR(SUM((T2.APROBADO + T2.AMPLIACION -T2.REDUCCIONES) - T2.DEVENGADO),'999999999999990.99'))||'\"' ")
				.append("FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ").append(aprobado).append(ampliacion).append(reduccion)
				.append(devengado).append(pagado)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR ")
				.append("INNER JOIN FUENTEF FU ON FU.IDSECTOR = PA.IDSECTOR WHERE  PA.IDSECTOR = ").append(idSector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND SUBSTR(FU.CLVFTE,1,1)='2' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='115' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS	ORDER BY NAT.CLVGAS ASC	) T2 UNION ALL ")
				.append("SELECT '\"'||T2.CLVGAS|| ' ' ||T2.NOMGAS||'\"|\"'||")
				.append("TRIM( TO_CHAR(T2.APROBADO,'999999999999990.99'))||'\"|\"'||")
				.append("TRIM(TO_CHAR((T2.AMPLIACION -T2.REDUCCIONES),'999999999999990.99'))||'\"|\"'||")
				.append("TRIM(TO_CHAR((T2.APROBADO + T2.AMPLIACION -T2.REDUCCIONES),'999999999999990.99'))||'\"|\"'||")
				.append("TRIM(TO_CHAR(T2.DEVENGADO,'999999999999990.99')) ||'\"|\"'||")
				.append("TRIM(TO_CHAR(T2.PAGADO,'999999999999990.99'))||'\"|\"'||")
				.append("TRIM(TO_CHAR(((T2.APROBADO + T2.AMPLIACION -T2.REDUCCIONES) - T2.DEVENGADO),'999999999999990.99')) ||'\"' ")
				.append(" FROM (SELECT	NAT.CLVGAS, NAT.NOMGAS, ").append(aprobado).append(ampliacion).append(reduccion)
				.append(devengado).append(pagado)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR ")
				.append("INNER JOIN FUENTEF FU ON FU.IDSECTOR = PA.IDSECTOR WHERE  PA.IDSECTOR = ").append(idSector)
				.append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND SUBSTR(FU.CLVFTE,1,1)='2' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='115' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC ) T2");

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
