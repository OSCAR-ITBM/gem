package com.gem.sistema.business.bs.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.bs.GeneraTxtBS;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReporte;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.ReportesRepository;

@Repository
public class GeneraTxtBSImpl implements GeneraTxtBS {

	@Autowired
	private ReportesRepository reportesRepository;

	@Autowired
	ConctbRepository conctbRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String generatArchivoTxt(long idReporte, Integer idSector, Integer trimestre) {

		TcReporte tcReporte = this.reportesRepository.findOne(idReporte);
		Conctb conctb = this.conctbRepository.findByIdsector(idSector);
		String fileSystem = tcReporte.getRutaArchivo() + "/" + tcReporte.getNombreArchivo() + conctb.getClave()
				+ conctb.getAnoemp() + StringUtils.leftPad(trimestre.toString(), 2, "0") + ".txt";
		String sSql = String.format(tcReporte.getQry1(), idSector, trimestre);
		List<String> listDatos = this.jdbcTemplate.query(sSql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				return rs.getString(1);
			}
		});
		FileWriter fw = null; // la extension al archivo
		try {
			fw = new FileWriter(fileSystem);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			listDatos.forEach(datos -> out.print(datos + "\n"));

			out.close();
			return fileSystem;
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public ReportesRepository getReportesRepository() {
		return reportesRepository;
	}

	public void setReportesRepository(ReportesRepository reportesRepository) {
		this.reportesRepository = reportesRepository;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

}
