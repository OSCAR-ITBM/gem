package com.gem.sistema.business.service.reportador;

import java.util.Map;
import java.util.function.Consumer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExportaInformacionService.
 */
public interface ExportaInformacionService {
	
	/**
	 * Find records for month.
	 *
	 * @param month the month
	 * @param idSector the id sector
	 * @param consumer the consumer
	 */
	public void findRecordsForMonth(Integer month, Long idSector, Consumer<Map<String, String>> consumer);

	/**
	 * Not decimal.
	 *
	 * @param value the value
	 * @return the string
	 */
	String notDecimal(Double value);
}
