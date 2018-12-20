package com.gem.sistema.business.dao.impl;

import static com.roonin.utils.UtilDate.convertStringToDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.Pm3711DAO;
import com.gem.sistema.business.dto.Pm3711DTO;

@Repository(value = "pm3711DAO")
public class Pm3711DAOImpl implements Pm3711DAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Pm3711DTO> findAll() {

		String sSql = "SELECT  TV.ID_ROW,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'SEMESTRE', TV.VALOR, '')) SEMESTRE,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'ID_REF', TV.VALOR, '')) ID_REF,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'ID_SECTOR', TV.VALOR, '')) ID_SECTOR,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'ID_ANIO', TV.VALOR, '')) ID_ANIO,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'CERTIFICACION', TV.VALOR, '')) CERTIFICACION,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'EXPERIENCIA', TV.VALOR, '')) EXPERIENCIA,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'TITULO', TV.VALOR,'')) TITULO,\n"
				+ "				MAX(DECODE(TE.NOMBRE, 'FECHAING', TV.VALOR,'')) FECHAING\n"
				+ "				FROM TR_ETIQ_TABLAS AS ET \n"
				+ "					INNER JOIN TC_ETIQUETAS AS TE ON ET.ID_ETIQUETA = TE.ID\n"
				+ "					INNER JOIN TC_TABLAS AS TT ON ET.ID_TABLA = TT.ID\n"
				+ "					INNER JOIN TC_VALORES AS TV ON ET.ID = TV.ID_ETIQ_TABLA WHERE 1=1\n"
				+ "				AND ET.STATUS = 1 AND TT.NOMBRE = 'PM3711' GROUP BY TV.ID_ROW";
		
		
		List<Pm3711DTO> lista = this.jdbcTemplate.query(sSql, new Object[]{}, new RowMapper<Pm3711DTO>() {

			@Override
			public Pm3711DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				Pm3711DTO pm3711 = new Pm3711DTO();
				pm3711.setIdRow(Integer.valueOf(rs.getString("ID_ROW")));
				pm3711.setSemestre(Integer.valueOf(rs.getString("SEMESTRE")));
				pm3711.setIdRef(Long.valueOf(rs.getString("ID_REF")));
				pm3711.setIdSector(Integer.valueOf(rs.getInt("ID_SECTOR")));
				pm3711.setIdAnio(Long.valueOf(rs.getString("ID_ANIO")));
				pm3711.setCertificacion(Integer.valueOf(rs.getString("CERTIFICACION")));
				pm3711.setExperiencia(Integer.valueOf(rs.getString("EXPERIENCIA")));
				pm3711.setTitulo(Integer.valueOf(rs.getString("TITULO")));
				pm3711.setFechaIng(convertStringToDate("dd/MM/yyyy", rs.getString("FECHAING")));
				
				return pm3711;
			}
			
		});
		return lista;
	}
	
	

	@Override
	public void deletePm3711(Integer idRow) {

		String sSql = "DELETE TC_VALORES WHERE ID_ROW = ? ";
		this.jdbcTemplate.update(sSql, idRow);
	}



	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
