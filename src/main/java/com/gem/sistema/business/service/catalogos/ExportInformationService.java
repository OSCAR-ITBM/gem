package com.gem.sistema.business.service.catalogos;

public interface ExportInformationService {

	String exportCuentas(Integer idSector, Integer mes);

	String exportPaso(Integer idSector, Integer mes);

	String exportPoliza(Integer idSecotr, Integer mes);

}
