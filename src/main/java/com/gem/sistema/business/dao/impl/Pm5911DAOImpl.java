package com.gem.sistema.business.dao.impl;

import static com.roonin.utils.UtilDate.converStringToDate;
import static com.roonin.utils.UtilDate.getFormatDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.Pm5911DAO;
import com.gem.sistema.business.domain.TcValores;
import com.gem.sistema.business.dto.Pm5911DTO;
import com.gem.sistema.business.repository.catalogs.TcValoresRepository;
import com.gem.sistema.business.repository.catalogs.TrEtqTablasRepository;
import com.gem.sistema.business.utils.GetValuesClassUtils;

@Repository
public class Pm5911DAOImpl implements Pm5911DAO {

	private static final String TABLE_NAME = "pm5911";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TcValoresRepository tcValoresRepository;

	@Autowired
	private TrEtqTablasRepository trEtqTablasRepository;

	TcValores tcValores;
	Integer max;
	Map<String, Object> map;

	@Override
	public List<Pm5911DTO> save(Pm5911DTO pm5911dto) {

		tcValores = new TcValores();
		max = this.tcValoresRepository.findByidTable(1);

		if (null != max && max > 0L) {
			max = max + 1;
		} else {
			max = 1;
		}

		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(pm5911dto, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

				Long idEt = this.trEtqTablasRepository.findByEtiqueta(entry.getKey().toUpperCase(), 1);

				tcValores.setIdRow(max.intValue());
				tcValores.setValor(entry.getValue().toString());
				tcValores.setIdEtiqTabla(idEt);
				if (entry.getKey().equals("fechaIng")) {
					tcValores.setValor(getFormatDate(entry.getValue().toString()));
				}

				tcValoresRepository.save(tcValores);

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.findByTableName(TABLE_NAME);
	}

	@Override
	public List<Pm5911DTO> findByTableName(String tableName) {
		Pm5911DTO pm5911dto = new Pm5911DTO();

		String sSql = "SELECT TV.ID_ROW,\n" + "       MAX(DECODE(TE.NOMBRE, 'SEMESTRE', TV.VALOR, NULL))SEMESTRE,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'FECHAING', TV.VALOR, NULL))FECHAING,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'TITULO', TV.VALOR, NULL))TITULO,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'EXPERIENCIA', TV.VALOR, NULL))EXPERIENCIA,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'CERTIFICACION', TV.VALOR, NULL))CERTIFICACION,\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'CAPTURO', TV.VALOR, '')),CAPTURO\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'IDANIO', TV.VALOR, NULL)),IDANIO\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'IDREF', TV.VALOR, NULL)),IDREF\n"
				+ "       MAX(DECODE(TE.NOMBRE, 'IDSECTOR', TV.VALOR, NULL))IDSECTOR\n"
				+ "     FROM GEMUSER.TC_VALORES TV    ,\n" + "          GEMUSER.TR_ETIQ_TABLAS TR,\n"
				+ "          GEMUSER.TC_ETIQUETAS TE  ,\n" + "          GEMUSER.TC_TABLAS TC\n"
				+ "WHERE TV.ID_ETIQ_TABLA = TR.ID\n" + "  AND TR.ID_ETIQUETA   = TE.ID\n"
				+ "  AND TR.ID_TABLA      = TC.ID\n" + "  AND TE.STATUS        = 1\n" + "  AND TC.NOMBRE        = '"
				+ tableName + "'\n" + "GROUP BY TV.ID_ROW";
		List<Pm5911DTO> listValores = this.jdbcTemplate.query(sSql, new RowMapper<Pm5911DTO>() {

			@Override
			public Pm5911DTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				Pm5911DTO pm5911DTO = new Pm5911DTO();
				pm5911DTO.setIdRow(rs.getInt("ID_ROW"));
				pm5911DTO.setFechaIng(rs.getString("FECHAING"));
				pm5911dto.setTitulo(rs.getString("TITULO"));
				pm5911DTO.setExperencia(rs.getString("EXPERIENCIA"));
				pm5911DTO.setCertificacion(rs.getString("CERTIFICACION"));
				pm5911dto.setCapturo(rs.getString("CAPTURO"));
				pm5911dto.setIdAnio(rs.getInt("IDANIO"));
				pm5911dto.setIdRef(rs.getInt("IDREF"));
				pm5911DTO.setIdSector(rs.getInt("IDSECTOR"));
				return pm5911DTO;
			}
		});

		return listValores;
	}

	@Override
	public List<Pm5911DTO> modify(Pm5911DTO pm5911dto) {
		tcValores = new TcValores();
		String sSql;
		Integer idRow = trEtqTablasRepository.getIdRow(pm5911dto.getSemestre().toString());
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(pm5911dto, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

				Long idEt = this.trEtqTablasRepository.findByEtiqueta(entry.getKey().toUpperCase(), 1);

				tcValores.setValor(entry.getValue().toString());
				tcValores.setIdEtiqTabla(idEt);
				if (entry.getKey().equals("fechaIng")) {
					tcValores.setValor(getFormatDate(entry.getValue().toString()));
				}

				tcValores.setIdRow(idRow);

				sSql = " UPDATE TC_VALORES \n" + "      SET VALOR = '" + tcValores.getValor() + "'\n"
						+ "    WHERE ID_ETIQ_TABLA = " + tcValores.getIdEtiqTabla() + "\n" + "      AND ID_ROW = "
						+ tcValores.getIdRow() + +tcValores.getIdRow();
				jdbcTemplate.update(sSql);

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.findByTableName(TABLE_NAME);
	}

	@Override
	public List<Pm5911DTO> delete(Pm5911DTO pm5911dto) {
		String sSql;
		Integer idRow = trEtqTablasRepository.getIdRow(pm5911dto.getSemestre().toString());
		try {
			map = GetValuesClassUtils.getFieldNamesAndValues(pm5911dto, false);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

				Long idEt = this.trEtqTablasRepository.findByEtiqueta(entry.getKey().toUpperCase(), 1);
				tcValores.setValor(entry.getValue().toString());
				tcValores.setIdEtiqTabla(idEt);
				if (entry.getKey().equals("fechaIng")) {
					tcValores.setValor(getFormatDate(entry.getValue().toString()));
				}

				tcValores.setIdRow(idRow);
				sSql = "DELETE FROM GEMUSER.TC_VALORES TV WHERE TV.VALOR ='" + tcValores.getValor() + "' AND TV.ID_ROW = " + tcValores.getIdRow()
						+ " AND TV.ID_ETIQ_TABLA = " + tcValores.getIdEtiqTabla();
				jdbcTemplate.update(sSql);

			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.findByTableName(TABLE_NAME);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public TcValoresRepository getTcValoresRepository() {
		return tcValoresRepository;
	}

	public void setTcValoresRepository(TcValoresRepository tcValoresRepository) {
		this.tcValoresRepository = tcValoresRepository;
	}

	public TrEtqTablasRepository getTrEtqTablasRepository() {
		return trEtqTablasRepository;
	}

	public void setTrEtqTablasRepository(TrEtqTablasRepository trEtqTablasRepository) {
		this.trEtqTablasRepository = trEtqTablasRepository;
	}

}
