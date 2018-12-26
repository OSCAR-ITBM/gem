package com.gem.sistema.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.PuestosFirmasDAO;
import com.gem.sistema.business.dto.PuestosFirmasDTO;

@Repository
public class PuestosFirmasDAOImpl implements PuestosFirmasDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<PuestosFirmasDTO> listPuestosFirmas(Integer idSector) {

		String sSql = "SELECT  TP.ID,\n" + 
				"		TP.PUESTO, \n" + 
				"		NVL(TF.NOMBRE,'') NOMBRE\n" + 
				"	FROM TC_PUESTOS TP\n" + 
				"		LEFT JOIN TR_PUESTO_FIRMA TF ON TF.ID_PUESTOS = TP.ID\n" + 
				"WHERE 1 = 1\n" + 
				"	AND TP.ID_SECTOR=? ";
		
		List<PuestosFirmasDTO> lista = this.jdbcTemplate.query(sSql, new Object[] {idSector}, new RowMapper<PuestosFirmasDTO>() {

			@Override
			public PuestosFirmasDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PuestosFirmasDTO firmasDTO = new PuestosFirmasDTO();
				firmasDTO.setId(rs.getLong("ID"));
				firmasDTO.setNombre(rs.getString("NOMBRE"));
				firmasDTO.setPuesto(rs.getString("PUESTO"));
				return firmasDTO;
			}
			
		});
		
		return lista;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
