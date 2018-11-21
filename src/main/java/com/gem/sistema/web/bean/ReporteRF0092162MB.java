package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.CatdgmRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0092162MB.
 */
@ManagedBean(name = "reporteRF0092162MB")
@ViewScoped
public class ReporteRF0092162MB extends BaseDirectReport{
	
	/** The clv dep. */
	private String clvDep;
	
	/** The nombre. */
	private String nombre;
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/** The list catdgm. */
	private List<Catdgm> listCatdgm;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/**
	 * Gets the catdgm repository.
	 *
	 * @return the catdgm repository
	 */
	public CatdgmRepository getCatdgmRepository() {
		return catdgmRepository;
	}

	/**
	 * Sets the catdgm repository.
	 *
	 * @param catdgmRepository the new catdgm repository
	 */
	public void setCatdgmRepository(CatdgmRepository catdgmRepository) {
		this.catdgmRepository = catdgmRepository;
	}

	/** The catdgm repository. */
	@ManagedProperty("#{catdgmRepository}")
	private CatdgmRepository catdgmRepository;

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
	 * Gets the list catdgm.
	 *
	 * @return the list catdgm
	 */
	public List<Catdgm> getListCatdgm() {
		return listCatdgm;
	}

	/**
	 * Sets the list catdgm.
	 *
	 * @param listCatdgm the new list catdgm
	 */
	public void setListCatdgm(List<Catdgm> listCatdgm) {
		this.listCatdgm = listCatdgm;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init(){      
		jasperReporteName = "RF009_2_1_6_2";
		endFilename = jasperReporteName + ".pdf";
		listCatdgm = catdgmRepository.findAll();
		if(!CollectionUtils.isEmpty(listCatdgm)){
			clvDep = listCatdgm.get(0).getClave();
			
		}
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector()); 
		
		parameters.put("anio", firmas.getCampo3());
		parameters.put("municipio", firmas.getCampo1());
		parameters.put("pathImg", this.getUserDetails().getPathImgCab2());
		parameters.put("idSector", this.getUserDetails().getIdSector());
		parameters.put("Dependencia", this.clvDep.substring(0,3) );
		parameters.put("DependenciaName", this.clvDep );
		parameters.put("firmaP1", firmas.getL27());
		parameters.put("firmaP2", firmas.getL5());
		parameters.put("firmaP3", firmas.getL3());
		parameters.put("firmaN1", firmas.getN27());
		parameters.put("firmaN2", firmas.getN5());
		parameters.put("firmaN3", firmas.getN3());
		
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

}