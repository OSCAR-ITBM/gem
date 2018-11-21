package com.gem.sistema.business.service.reportador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.function.Consumer;

// TODO: Auto-generated Javadoc
/**
 * The Class ExportaInformacionServiceImpl.
 */
@Service("exportaInformacionService")
@Transactional
public class ExportaInformacionServiceImpl implements ExportaInformacionService {

	/** The conctb repository. */
	@Autowired
	ConctbRepository conctbRepository;

	/** The polide repository. */
	@Autowired
	PolideRepository polideRepository;

	/** The polien repository. */
	@Autowired
	PolienRepository polienRepository;

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.reportador.ExportaInformacionService#findRecordsForMonth(java.lang.Integer, java.lang.Long, java.util.function.Consumer)
	 */
	public void findRecordsForMonth(Integer month, Long idSector, Consumer<Map<String, String>> consumer) {
		Conctb conctb = conctbRepository.findFirstByIdsectorOrderByIdAsc(idSector.intValue());

		for (int pagenum = 0; true; pagenum++) {
			Page<Polide> page = polideRepository.getByYearAndMonth(conctb.getAnoemp(), month, idSector.intValue(),
					new PageRequest(pagenum, 10));
			page.forEach((p) -> {
				Map<String, String> results = new HashMap<String, String>();
				Polien polien = polienRepository.findFirstByMespolAndAnopolAndConpolAndTippolAndIdsectorOrderById(
						p.getMespol(), p.getAnopol(), p.getConpol(), p.getTippol(), idSector.intValue());
				results.put("xfecha", blankIfNull(polien == null ? "" : polien.getFecpol()));
				results.put("clave", blankIfNull(conctb.getClave()));
				results.put("renpol", blankIfNull(p.getRenpol()));
				results.put("cuenta", blankIfNull(p.getCuenta()));
				results.put("scta", blankIfNull(p.getScta()));
				results.put("sscta", blankIfNull(p.getSscta()));
				results.put("ssscta", blankIfNull(p.getSsscta()));
				results.put("sssscta", blankIfNull(p.getSssscta()));
				results.put("tippol", blankIfNull(p.getTippol()));
				results.put("conpol", blankIfNull(p.getConpol()));
				results.put("concep", blankIfNull(p.getConcep()));
				results.put("canpol", blankIfNull(p.getCanpol()));
				results.put("canpolh", blankIfNull(p.getCanpolh()));
				consumer.accept(results);
			});
			if (page.isLast())
				break;
		}
	}

	/**
	 * Blank if null.
	 *
	 * @param t the t
	 * @return the string
	 */
	private String blankIfNull(Object t) {
		return t == null ? "" : t.toString();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.service.reportador.ExportaInformacionService#notDecimal(java.lang.Double)
	 */
	@Override
	public String notDecimal(Double value) {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat formateador1 = new DecimalFormat("####.##", simbolos);
		return formateador1.format(formateador1);
	}
}
