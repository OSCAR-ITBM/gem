package com.gem.sistema.business.dao;

public interface ExportInformationDAO {

	String exportCuentas(Integer idSector, Integer mes);

	String exportPaso(Integer idSector, Integer mes);

	String exportPoliza(Integer idSecotr, Integer mes);

}
