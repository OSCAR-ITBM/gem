package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.roonin.utils.UtilDate.getLastDay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.ClasificacionObjGastoService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "clasificacionObjGastoMB")
@ViewScoped
public class ClasificacionObjGastoMB extends BaseDirectReport {
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	private List<TcPeriodo> listTrimestres;
	private Integer trimestre;
	private Firmas firmas;

	private String pathName;
	InputStream stream = null;
	private StreamedContent file;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{clasificacionObjGastoService}")
	private ClasificacionObjGastoService clasificacionObjGastoService;

	@PostConstruct
	public void init() {
		listTrimestres = tcPeriodoRepositoy.findByTipoPeriodo(3);
		jasperReporteName = "ClasificacionObjGasto";
		endFilename = jasperReporteName + ".pdf";

		if (!listTrimestres.isEmpty()) {
			trimestre = listTrimestres.get(0).getPeriodo();
		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Object[] meses = getMonthsByTrimestre(trimestre);

		parameters.put("municipio", firmas.getCampo1());
		parameters.put("imagen", this.getUserDetails().getPathImgCab1());
		parameters.put("firstMonth", meses[0]);
		parameters.put("lastMonth", meses[1]);
		parameters.put("lastDay", meses[2]);
		parameters.put("anio", firmas.getCampo3());
		parameters.put("firmaL1", firmas.getL1());
		parameters.put("firmaN1", firmas.getN1());
		parameters.put("firmaL2", firmas.getL3());
		parameters.put("firmaN2", firmas.getN3());
		parameters.put("sql", this.generateSQL(trimestre, this.getUserDetails().getIdSector()));

		return parameters;
	}

	public void downloadCuenta() {
		pathName = this.clasificacionObjGastoService.generaTxtClasificacionObjGasto(this.getUserDetails().getIdSector(),
				trimestre);
		try {
			stream = new FileInputStream(new File(pathName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		file = new DefaultStreamedContent(stream, "application/txt", pathName.substring(13));
		generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
				"Se Generó el Archivo: " + pathName.substring(13));

	}

	public void downloadCuentaTxt() {
		try {
			this.downloadCuenta();
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer trimestre, Integer sector) {

		StringBuilder sql = new StringBuilder();
		int mesFinal = trimestre * 3;

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = 1; y <= mesFinal; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.EJXPA1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION, ";

		sql.append("SELECT 1 GRUP, T1.CLVGAS, T1.NOMGAS, T1.APROBADO, (T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, T1.DEVENGADO, T1.PAGADO, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO ")
				.append("FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ").append(auto).append(ampli).append(redu).append(ejxpa)
				.append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR AND SUBSTR(PA.PARTIDA, 3, 2) = '00' ")
				.append("WHERE  PA.IDSECTOR = ").append(sector).append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='101' AND SUBSTR(PA.PROGRAMA,13,3)<='113' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='201' AND SUBSTR(PA.PROGRAMA,13,3)<='202' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC ) T1 UNION ALL ")
				.append("SELECT 2 GRUP, T1.CLVGAS, T1.NOMGAS, T1.APROBADO, (T1.AMPLIACION -T1.REDUCCIONES) AMPL_REDU, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) MODIFICADO, T1.DEVENGADO, T1.PAGADO, ")
				.append("(T1.APROBADO + T1.AMPLIACION -T1.REDUCCIONES) - T1.DEVENGADO SUBEJERCICIO "
						+ "FROM (SELECT NAT.CLVGAS, NAT.NOMGAS, ")
				.append(auto).append(ampli).append(redu).append(ejxpa).append(ejpa)
				.append("FROM PASO PA INNER JOIN NATGAS NAT ON NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR AND SUBSTR(PA.PARTIDA, 3, 2) = '00' ")
				.append("WHERE  PA.IDSECTOR = ").append(sector).append(" AND SUBSTR(PA.PARTIDA,4,1)<>'0' AND ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='203' AND SUBSTR(PA.PROGRAMA,13,3)<='225' OR ")
				.append("SUBSTR(PA.PROGRAMA,13,3)>='114' AND SUBSTR(PA.PROGRAMA,13,3)<='115' ")
				.append("GROUP BY NAT.CLVGAS,NAT.NOMGAS ORDER BY NAT.CLVGAS ASC) T1 ORDER BY GRUP, CLVGAS");

		return sql.toString();
	}

	public Object[] getMonthsByTrimestre(Integer trimestre) {
		Integer mesFinal = trimestre * 3;
		Integer mesInicial = mesFinal - 2;
		Object[] meses = {
				tcMesRepository.findByMes(org.apache.commons.lang3.StringUtils.leftPad(mesInicial.toString(), 2, "0"))
						.getDescripcion(),
				tcMesRepository.findByMes(org.apache.commons.lang3.StringUtils.leftPad(mesFinal.toString(), 2, "0"))
						.getDescripcion(),
				getLastDay(mesFinal) };

		return meses;
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

	public Firmas getFirmas() {
		return firmas;
	}

	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	public ClasificacionObjGastoService getClasificacionObjGastoService() {
		return clasificacionObjGastoService;
	}

	public void setClasificacionObjGastoService(ClasificacionObjGastoService clasificacionObjGastoService) {
		this.clasificacionObjGastoService = clasificacionObjGastoService;
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

}
