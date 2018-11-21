package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gem.sistema.business.domain.Catdaa;
import com.gem.sistema.business.domain.Firmas;


import com.gem.sistema.business.repository.catalogs.CatdaaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import org.primefaces.model.StreamedContent;


// TODO: Auto-generated Javadoc
/**
 * The Class RF0092163MB.
 */
@ManagedBean(name="rF0092163MB")
@ViewScoped
public class RF0092163MB extends BaseDirectReport {
	
	/** The clave dep aux. */
	private String claveDepAux;
	
	/** The list catdaa. */
	private List<Catdaa> listCatdaa;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	/** The catdaa repository. */
	@ManagedProperty("#{catdaaRepository}")
	private CatdaaRepository catdaaRepository;
	

	/**
	 * Gets the clave dep aux.
	 *
	 * @return the clave dep aux
	 */
	public String getClaveDepAux() {
		return claveDepAux;
	}

	/**
	 * Sets the clave dep aux.
	 *
	 * @param claveDepAux the new clave dep aux
	 */
	public void setClaveDepAux(String claveDepAux) {
		this.claveDepAux = claveDepAux;
	}

	/**
	 * Gets the list catdaa.
	 *
	 * @return the list catdaa
	 */
	public List<Catdaa> getListCatdaa() {
		return listCatdaa;
	}

	/**
	 * Sets the list catdaa.
	 *
	 * @param listCatdaa the new list catdaa
	 */
	public void setListCatdaa(List<Catdaa> listCatdaa) {
		this.listCatdaa = listCatdaa;
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
	 * Gets the catdaa repository.
	 *
	 * @return the catdaa repository
	 */
	public CatdaaRepository getCatdaaRepository() {
		return catdaaRepository;
	}

	/**
	 * Sets the catdaa repository.
	 *
	 * @param catdaaRepository the new catdaa repository
	 */
	public void setCatdaaRepository(CatdaaRepository catdaaRepository) {
		this.catdaaRepository = catdaaRepository;
	}

	

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		 Map<String, Object> parameters= new HashMap<String, Object>();
		 Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		 parameters.put("anio", firmas.getCampo3().toString());
		 parameters.put("idSector",this.getUserDetails().getIdSector() );
		 parameters.put("dependencia", claveDepAux);
		 parameters.put("municipio", this.getUserDetails().getMunicipio());
		 parameters.put("imagePath", this.getUserDetails().getPathImgCab1());
		 
		 parameters.put("firmaP1", firmas.getL27()) ;
		 parameters.put("firmaN1", firmas.getN27()) ;
		 parameters.put("firmaP2", firmas.getL5()) ;
		 parameters.put("firmaN2", firmas.getN5()) ;
		 parameters.put("firmaP3", firmas.getL3());
		 parameters.put("firmaN3", firmas.getN3());
		 
		 
		 
		 
		 
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		
		return null;
	}
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "RF009_2_1_6_3" ;
		endFilename = jasperReporteName + ".pdf";
		
		listCatdaa= catdaaRepository.findAllByOrderByClave();
		
		if (!CollectionUtils.isEmpty(listCatdaa)) {
			claveDepAux=listCatdaa.get(0).getClave();
		}
		
		

	}

}


