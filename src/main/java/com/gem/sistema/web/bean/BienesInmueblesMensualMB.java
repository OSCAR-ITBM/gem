package com.gem.sistema.web.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "bienesInmueblesMensualMB")
@ViewScoped
public class BienesInmueblesMensualMB extends BaseDirectReport {

	private Firmas firmas;

	private Conctb conctb;

	private Integer mes;

	private List<TcPeriodo> listMeses;

	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy tcPeriodoRepositoy;

	@PostConstruct
	public void init() {

		jasperReporteName = "bienesInmuebles";
		endFilename = jasperReporteName + ".pdf";

		listMeses = tcPeriodoRepositoy.findByTipoPeriodo(1);

		if (!listMeses.isEmpty()) {
			mes = listMeses.get(0).getPeriodo();
		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parametrs = new java.util.HashMap<String, Object>();
		firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		TcPeriodo mesSelected = tcPeriodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);

		parametrs.put("pSector", this.getUserDetails().getIdSector());
		parametrs.put("pMesIni", mesSelected.getPeriodo());
		parametrs.put("pNomMunicipio", firmas.getCampo1());
		parametrs.put("pNumMunicipio", conctb.getClave());
		parametrs.put("pL1", firmas.getL1());
		parametrs.put("pL2", firmas.getL2());
		parametrs.put("pL3", firmas.getL3());
		parametrs.put("pL4", firmas.getL16());
		parametrs.put("pL5", firmas.getL28());
		parametrs.put("pN1", firmas.getN1());
		parametrs.put("pN2", firmas.getN2());
		parametrs.put("pN3", firmas.getN3());
		parametrs.put("pN4", firmas.getN16());
		parametrs.put("pN5", firmas.getN28());
		parametrs.put("pImgMuni", this.getUserDetails().getPathImgCab1());

		return parametrs;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public Firmas getFirmas() {
		return firmas;
	}

	public void setFirmas(Firmas firmas) {
		this.firmas = firmas;
	}

	public Conctb getConctb() {
		return conctb;
	}

	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public List<TcPeriodo> getListMeses() {
		return listMeses;
	}

	public void setListMeses(List<TcPeriodo> listMeses) {
		this.listMeses = listMeses;
	}

	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getTcPeriodoRepositoy() {
		return tcPeriodoRepositoy;
	}

	public void setTcPeriodoRepositoy(TcPeriodoRepositoy tcPeriodoRepositoy) {
		this.tcPeriodoRepositoy = tcPeriodoRepositoy;
	}

}
