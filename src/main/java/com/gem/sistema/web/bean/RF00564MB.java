package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import static com.roonin.utils.UtilDate.getDateOfSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class RF00564MB.
 */
@ManagedBean(name = "rF00564MB")
public class RF00564MB extends BaseDirectReport{
	
	/** The firmas. */
	private Firmas firmas;
	
	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
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
	public void init(){
		jasperReporteName = "RF00564";
		endFilename = jasperReporteName +  ".pdf";
	}
	
	/** The parameters. */
	Map<String, Object> parameters;
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		parameters = new HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		parameters.put("municipio", firmas.getCampo1());
		parameters.put("idSector", this.getUserDetails().getIdSector() );
		parameters.put("l1", firmas.getL27());
		parameters.put("l2", firmas.getL5());
		parameters.put("l3", firmas.getL3());
		parameters.put("n1", firmas.getN27());
		parameters.put("n2", firmas.getN5());
		parameters.put("n3", firmas.getN3());
		parameters.put("pathImg", this.getUserDetails().getPathImgCab1());
		parameters.put("fecha", getDateOfSystem());
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
