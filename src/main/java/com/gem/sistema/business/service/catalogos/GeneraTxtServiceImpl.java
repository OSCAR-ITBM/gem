package com.gem.sistema.business.service.catalogos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.bs.GeneraTxtBS;

@Service(value = "generaTxtService")
public class GeneraTxtServiceImpl implements GeneraTxtService {

	@Autowired
	private GeneraTxtBS generaTxtBs;

	@Override
	public String generatArchivoTxt(long idReporte, Integer idSector, Integer trimestre) {

		return this.generaTxtBs.generatArchivoTxt(idReporte, idSector, trimestre);
	}

	public GeneraTxtBS getGeneraTxtBs() {
		return generaTxtBs;
	}

	public void setGeneraTxtBs(GeneraTxtBS generaTxtBs) {
		this.generaTxtBs = generaTxtBs;
	}

	@Override
	public String generaArchivoEaepecaldf(Integer idSector, Integer trimestre) {
		return this.generaTxtBs.generaArchivoEaepecaldf(idSector, trimestre);
	}

}
