package com.gem.sistema.business.bs;

public interface GeneraTxtBS {

	String generatArchivoTxt(long idReporte, Integer idSector, Integer trimestre);
	
	String generaArchivoEaepecaldf(Integer idSector, Integer trimestre);

}
