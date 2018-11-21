package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.apache.commons.collections4.CollectionUtils;
import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcMes;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ImprimirPolizaMB.
 */
@ManagedBean(name = "imprimirPolizaMB")
@ViewScoped
public class ImprimirPolizaMB extends BaseDirectReport {

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_IMPRIME_POLIZAS";
	
	/** The tipo. */
	private String tipo;
	
	/** The mes. */
	private String mes;
	
	/** The list cattpo. */
	private List<Cattpo> listCattpo;
	
	/** The list tc mes. */
	private List<TcMes> listTcMes;
	
	/** The pol ini. */
	private Integer polIni;
	
	/** The polfin. */
	private Integer polfin;
	
	/** The txt. */
	private StreamedContent txt;
	
	/** The firmas. */
	private Firmas firmas;



	/** The cattpo repositry. */
	@ManagedProperty("#{cattpoRepository}")
	private CattpoRepository cattpoRepositry;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	
	/**
	 * Gets the firmas.
	 *
	 * @return the firmas
	 */
	public Firmas getFirmas() {
		return firmas;
	}

	/**
	 * Sets the firmas.
	 *
	 * @param firmas the new firmas
	 */
	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

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
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "print_policy";
		endFilename = jasperReporteName + ".pdf";

		listTcMes = tcMesRepository.findAll();
		listCattpo = cattpoRepositry.findAll();
		if (!CollectionUtils.isEmpty(listTcMes)) {
			mes = listTcMes.get(0).getMes();
		}
		if (!CollectionUtils.isEmpty(listCattpo)) {
			tipo = listCattpo.get(0).getTippol();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		parameters.put("pTipoPoliza", tipo);
		parameters.put("pMesPoliza", Integer.parseInt(mes));
		parameters.put("pConsecutivoPolizaMin", polIni);
		parameters.put("pConsecutivoPolizaMax", polfin);
		parameters.put("pSector", this.getUserDetails().getIdSector());
		parameters.put("pImg", this.getUserDetails().getPathImgCab1());
		parameters.put("pImg2", this.getUserDetails().getPathImgCab2());
		parameters.put("pUser", this.getUserDetails().getUsername());
			
		parameters.put("L1", this.getUserDetails().getIdSector()==1? firmas.getL4():firmas.getL4());
		parameters.put("N1", this.getUserDetails().getIdSector()==1? firmas.getN4():firmas.getN4());
		parameters.put("L2", this.getUserDetails().getIdSector()==1? firmas.getL5():firmas.getL5());
		parameters.put("N2", this.getUserDetails().getIdSector()==1? firmas.getN5():firmas.getN5());
		parameters.put("L3", this.getUserDetails().getIdSector()==1? firmas.getL3():firmas.getL27());
		parameters.put("N3", this.getUserDetails().getIdSector()==1? firmas.getN3():firmas.getN27());

		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/** The out. */
	Map<String, Object> out;
	
	/** The stream. */
	InputStream stream = null;

	/**
	 * Gets the txt.
	 *
	 * @return the txt
	 */
	public StreamedContent getTxt() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", Integer.valueOf(mes));
		parameter.addValue("i_tipo", tipo);
		parameter.addValue("i_polini", polIni);
		parameter.addValue("i_polfin", polfin);
		parameter.addValue("i_id_sector", this.getUserDetails().getIdSector());

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		return txt;
	}

	/**
	 * Sets the txt.
	 *
	 * @param txt the new txt
	 */
	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}

	/**
	 * Gets the cattpo repositry.
	 *
	 * @return the cattpo repositry
	 */
	public CattpoRepository getCattpoRepositry() {
		return cattpoRepositry;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	 * Gets the list cattpo.
	 *
	 * @return the list cattpo
	 */
	public List<Cattpo> getListCattpo() {
		return listCattpo;
	}

	/**
	 * Sets the list cattpo.
	 *
	 * @param listCattpo the new list cattpo
	 */
	public void setListCattpo(List<Cattpo> listCattpo) {
		this.listCattpo = listCattpo;
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
	 * Gets the pol ini.
	 *
	 * @return the pol ini
	 */
	public Integer getPolIni() {
		return polIni;
	}

	/**
	 * Sets the pol ini.
	 *
	 * @param polIni the new pol ini
	 */
	public void setPolIni(Integer polIni) {
		this.polIni = polIni;
	}

	/**
	 * Gets the polfin.
	 *
	 * @return the polfin
	 */
	public Integer getPolfin() {
		return polfin;
	}

	/**
	 * Sets the polfin.
	 *
	 * @param polfin the new polfin
	 */
	public void setPolfin(Integer polfin) {
		this.polfin = polfin;
	}

	/**
	 * Sets the cattpo repositry.
	 *
	 * @param cattpoRepositry the new cattpo repositry
	 */
	public void setCattpoRepositry(CattpoRepository cattpoRepositry) {
		this.cattpoRepositry = cattpoRepositry;
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

	/**
	 * Gets the execute pl service.
	 *
	 * @return the execute pl service
	 */
	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	/**
	 * Sets the execute pl service.
	 *
	 * @param executePlService the new execute pl service
	 */
	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

}
