package com.gem.sistema.business.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.EdoSF3211DAO;
import com.gem.sistema.business.dto.EdoSF3211DTO;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoSF3211DAOImpl.
 *
 * @author Dalia Tovar
 */
@Repository
public class EdoSF3211DAOImpl implements EdoSF3211DAO {
	
 /** The jdbc template. */
 @Autowired
	private JdbcTemplate  jdbcTemplate;
 

	/**
	 * Gets the jdbc template.
	 *
	 * @return the jdbc template
	 */
	public JdbcTemplate getJdbcTemplate() {
	return jdbcTemplate;
}


/**
 * Sets the jdbc template.
 *
 * @param jdbcTemplate the new jdbc template
 */
public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.EdoSF3211DAO#executeQueryCuenta(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String createQueryCuenta(Integer idsector, Integer mes) {
		
		StringBuilder cargonant = new StringBuilder();
		StringBuilder abonosant = new StringBuilder();
		StringBuilder cargosact = new StringBuilder();
		StringBuilder abonosact = new StringBuilder();
		StringBuilder sSql = new StringBuilder();

		if (mes == 1) {
			cargonant.append(" 0.00");
			abonosant.append(" 0.00");
		} else {

			for (int i = 1; i < mes; i++) {
				cargonant.append(" C.CARGOS_" + i + "+");
				abonosant.append(" C.ABONOS_" + i + "+");
			}
		}

		for (int i = 1; i <= mes; i++) {
			cargosact.append(" C.CARGOS_" + i + "+");
			abonosact.append(" C.ABONOS_" + i + "+");
		}

		sSql.append("SELECT (SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT,").append(" (SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT")
				.append(" FROM (SELECT ").append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5,")
				.append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5")
				.append(" FROM(SELECT  T1.GRUPO, ").append(" T1.SALINI,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT)")
				.append(" ELSE (T1.SALINI-ABONOS_ANT+CARGOS_ANT)").append(" END VMANT,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT)")
				.append(" ELSE (T1.SALINI-ABONOS_ACT+CARGOS_ACT)").append(" END VMACT")
				.append(" FROM (SELECT SUBSTR(C.CUENTA,1,1) GRUPO, ").append(" C.CUENTA CUENTA ,")
				.append(" C.SALINI SALINI,").append(" SUM(").append(cargonant.substring(1, cargonant.length() - 1))
				.append(") CARGOS_ANT,").append(" SUM(").append(abonosant.substring(1, abonosant.length() - 1))
				.append(") ABONOS_ANT,").append(" SUM(").append(cargosact.substring(1, cargosact.length() - 1))
				.append(") CARGOS_ACT,").append(" SUM(").append(abonosact.substring(1, abonosact.length() - 1))
				.append(") ABONOS_ACT").append(" FROM CUENTA C").append(" WHERE C.IDSECTOR=" + idsector)
				.append(" AND (((C.CUENTA >='4100' AND C.CUENTA<='4399' AND SUBSTR(C.CUENTA,4,1)<>'0')")
				.append(" OR (C.CUENTA IN ('5100','5200','5300','5400','5600','5700')))").append(" AND C.SCTA ='')")
				.append(" OR (C.CUENTA ='5500' AND C.SCTA<>'' AND C.SSCTA ='' AND C.NOTCTA=0)")
				.append(" GROUP BY C.CUENTA,").append(" C.SALINI").append(" ORDER BY C.CUENTA)T1)T2")
				.append(" GROUP BY T2.GRUPO )T3");

		return sSql.toString();

	}


	/* (non-Javadoc)
	 * @see com.gem.sistema.business.dao.EdoSF3211DAO#executeQuery(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public EdoSF3211DTO executeQuery(Integer idsector, Integer mes) {
		
		return jdbcTemplate.queryForObject(this.createQueryCuenta(idsector, mes), new EdoSF3211RowMapper());
	}
	

}
class EdoSF3211RowMapper implements RowMapper<EdoSF3211DTO> {

	@Override
	public EdoSF3211DTO mapRow(ResultSet rs, int arg1) throws SQLException {
		EdoSF3211DTO edoSF3211DTO = new EdoSF3211DTO();
		
		edoSF3211DTO.setTotalAct(rs.getBigDecimal("TOTAL_ACT"));
		edoSF3211DTO.setTotalAnt(rs.getBigDecimal("TOTAL_ANT"));
		return edoSF3211DTO;
	}
	
}
