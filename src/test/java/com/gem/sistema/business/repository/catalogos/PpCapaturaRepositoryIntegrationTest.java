package com.gem.sistema.business.repository.catalogos;

import static org.junit.Assert.fail;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.domain.PpCaptura;
import com.gem.sistema.business.repository.base.AbstractIntegrationTest;
import com.gem.sistema.business.repository.catalogs.PpCapturaRepository;

public class PpCapaturaRepositoryIntegrationTest extends AbstractIntegrationTest {

	@Autowired
	PpCapturaRepository ppcapturaRepository;
	
	@Test
	public void test() {
		PpCaptura ppCaptura = new PpCaptura();
		ppCaptura.setAnioCap(2016);
		ppCaptura.setCampo0(StringUtils.EMPTY);
		ppCaptura.setCampo1(StringUtils.EMPTY);
		ppCaptura.setCampo2(StringUtils.EMPTY);
		ppCaptura.setCampo3(StringUtils.EMPTY);
		ppCaptura.setCampo4(StringUtils.EMPTY);
		ppCaptura.setCampo5(StringUtils.EMPTY);
		ppCaptura.setClavedep(1);
		ppCaptura.setClvdep("1");
		ppCaptura.setClvnep("010702010308");
		ppCaptura.setClvfuen("Q00105");
		ppCaptura.setClvreg(StringUtils.EMPTY);
		ppCaptura.setNumVer(0);
		ppCaptura = this.ppcapturaRepository.save(ppCaptura);
		System.out.println(ToStringBuilder.reflectionToString(ppCaptura));
	}

}
