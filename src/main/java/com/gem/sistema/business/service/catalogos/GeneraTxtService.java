package com.gem.sistema.business.service.catalogos;

public interface GeneraTxtService {

	String generatArchivoTxt(long idReporte, Integer idSector, Integer trimestre);

	String generaArchivoEaepecaldf(Integer idSector, Integer trimestre);
	
	String generaArchivoEaepecfldf(Integer idSector, Integer trimestre);

}
