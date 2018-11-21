package com.gem.sistema.web.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.TotalMesesDTO;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.GenerateTotaleService;

import static com.roonin.utils.UtilDate.getYear;
import static com.roonin.utils.UtilDate.getDateOfSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0092165MB.
 */
@ManagedBean(name = "reporteRF0092165MB")
@ViewScoped
public class ReporteRF0092165MB extends BaseDirectReport {
	
	/** The catdep. */
	private Catdep catdep;

	/** The x catpro. */
	private Xcatpro xCatpro;

	/** The clv dep. */
	private String clvDep;
	
	/** The clv pro. */
	private String clvPro;
	
	/** The firmas. */
	private Firmas firmas;

	/** The conctb. */
	private Conctb conctb;

	/**
	 * Gets the clv dep.
	 *
	 * @return the clv dep
	 */
	public String getClvDep() {
		return clvDep;
	}

	/**
	 * Sets the clv dep.
	 *
	 * @param clvDep the new clv dep
	 */
	public void setClvDep(String clvDep) {
		this.clvDep = clvDep;
	}

	/**
	 * Gets the clv pro.
	 *
	 * @return the clv pro
	 */
	public String getClvPro() {
		return clvPro;
	}

	/**
	 * Sets the clv pro.
	 *
	 * @param clvPro the new clv pro
	 */
	public void setClvPro(String clvPro) {
		this.clvPro = clvPro;
	}

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

	/** The list catdep. */
	private List<Catdep> listCatdep;

	/** The list xcatpro. */
	private List<Xcatpro> listXcatpro;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The xcatpro repository. */
	@ManagedProperty("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The generate totale service. */
	@ManagedProperty("#{generateTotaleService}")
	private GenerateTotaleService generateTotaleService;

	/**
	 * Gets the catdep.
	 *
	 * @return the catdep
	 */
	public Catdep getCatdep() {
		return catdep;
	}

	/**
	 * Sets the catdep.
	 *
	 * @param catdep the new catdep
	 */
	public void setCatdep(Catdep catdep) {
		this.catdep = catdep;
	}

	/**
	 * Gets the x catpro.
	 *
	 * @return the x catpro
	 */
	public Xcatpro getxCatpro() {
		return xCatpro;
	}

	/**
	 * Sets the x catpro.
	 *
	 * @param xCatpro the new x catpro
	 */
	public void setxCatpro(Xcatpro xCatpro) {
		this.xCatpro = xCatpro;
	}

	/**
	 * Gets the list catdep.
	 *
	 * @return the list catdep
	 */
	public List<Catdep> getListCatdep() {
		return listCatdep;
	}

	/**
	 * Sets the list catdep.
	 *
	 * @param listCatdep the new list catdep
	 */
	public void setListCatdep(List<Catdep> listCatdep) {
		this.listCatdep = listCatdep;
	}

	/**
	 * Gets the list xcatpro.
	 *
	 * @return the list xcatpro
	 */
	public List<Xcatpro> getListXcatpro() {
		return listXcatpro;
	}

	/**
	 * Sets the list xcatpro.
	 *
	 * @param listXcatpro the new list xcatpro
	 */
	public void setListXcatpro(List<Xcatpro> listXcatpro) {
		this.listXcatpro = listXcatpro;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
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
	 * Gets the generate totale service.
	 *
	 * @return the generate totale service
	 */
	public GenerateTotaleService getGenerateTotaleService() {
		return generateTotaleService;
	}

	/**
	 * Sets the generate totale service.
	 *
	 * @param generateTotaleService the new generate totale service
	 */
	public void setGenerateTotaleService(GenerateTotaleService generateTotaleService) {
		this.generateTotaleService = generateTotaleService;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.jasperReporteName = "reporte_RF0092165";
		this.endFilename = jasperReporteName + ".pdf";
		listCatdep = catdepRepository.findAllByIdSectorCrossXcatpro(this.getUserDetails().getIdSector());

		if (!listCatdep.isEmpty()) {
			clvDep = this.listCatdep.get(0).getClvdep();
		}
		listXcatpro = xcatproRepository.getClave(this.listCatdep.get(0).getClvdep(),
				this.getUserDetails().getIdSector());
		if (!listXcatpro.isEmpty()) {
			clvPro = this.listXcatpro.get(0).getClvpro();
		}
	}

	/** The totales. */
	TotalMesesDTO totales;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() {
		Map<String, Object> parameters = new HashMap<String, Object>();
	    firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		totales = generateTotaleService.generateTotales(this.getUserDetails().getIdSector(),	
				clvDep.subSequence(0, 10).toString(), clvPro.substring(0, 15));
		parameters.put("dependencia", clvDep.subSequence(0, 10));
		parameters.put("programa", clvPro.substring(0, 15));
		parameters.put("presidente", firmas.getN27());
		parameters.put("secretario", firmas.getN5());
		parameters.put("tesorero", firmas.getN3());
		parameters.put("pathImg", this.getUserDetails().getPathImgCab1());
		parameters.put("idSector", this.getUserDetails().getIdSector());
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("descripcion", clvPro);
		parameters.put("municipio", firmas.getCampo1());
		parameters.put("dependenciaDescripcion", clvDep);
		parameters.put("enero", totales.getEnero());
		parameters.put("febrero", totales.getFebrero());
		parameters.put("marzo", totales.getMarzo());
		parameters.put("abril", totales.getAbril());
		parameters.put("mayo", totales.getMayo());
		parameters.put("junio", totales.getJunio());
		parameters.put("julio", totales.getJulio());
		parameters.put("agosto", totales.getAgosto());
		parameters.put("septiembre", totales.getSeptiembre());
		parameters.put("octubre", totales.getOctubre());
		parameters.put("noviembre", totales.getNoviembre());
		parameters.put("diciembre", totales.getDiciembre());
		parameters.put("tTotal", totales.getTotal());
		parameters.put("fecha", getDateOfSystem());
		parameters.put("L1", firmas.getL27());
		parameters.put("L2", firmas.getL5());
		parameters.put("L3", firmas.getL3());
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * On change by clve dep.
	 */
	public void onChangeByClveDep() {
		listXcatpro = xcatproRepository.getClave(clvDep.substring(0, 10), this.getUserDetails().getIdSector());
	}

}
