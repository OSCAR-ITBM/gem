package com.gem.sistema.web.bean;


import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name="pbrm06MB")
@ViewScoped
public class Pbrm06MB extends BaseDirectReport{

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;
	
	@PostConstruct
	public void init() {
		jasperReporteName = "pbrm06";
		endFilename = jasperReporteName + ".pdf";
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		
		paramsReport.put("pImage", this.getUserDetails().getPathImgCab1());
		paramsReport.put("pImage2", this.getUserDetails().getPathImgCab2());
		paramsReport.put("pYear", firmas.getCampo3());
		paramsReport.put("pEntePublico", firmas.getCampo1());
		paramsReport.put("pNumero", this.getUserDetails().getIdMunicipio().toString());
		paramsReport.put("pIdSector", this.getUserDetails().getIdSector());
		paramsReport.put("pL1", firmas.getL1());
		paramsReport.put("pL2", firmas.getL2());
		paramsReport.put("pL3", firmas.getL3());
		paramsReport.put("pN1", firmas.getN1());
		paramsReport.put("pN2", firmas.getN2());
		paramsReport.put("pN3", firmas.getN3());

		return paramsReport;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}
	
}
