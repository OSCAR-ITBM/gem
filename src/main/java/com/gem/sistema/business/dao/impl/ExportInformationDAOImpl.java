package com.gem.sistema.business.dao.impl;

import static com.roonin.utils.UtilDate.getLastDayOfMonth;
import static com.roonin.utils.UtilDate.getYear;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.CallSpDAO;
import com.gem.sistema.business.dao.ExportInformationDAO;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;

@Repository
public class ExportInformationDAOImpl implements ExportInformationDAO {

	private static final String NAME_PROCEDURE = "SP_EXPORTA_CUENTAS";
	private static final String FILE_CUETA = "cuenta.txt";
	private static final String FILE_PASO = "egrgas.txt";
	private static final String FILE_POLIZA = "p";
	private static final String CUENTA_EXPORT = "c";
	private static final String TXT = ".txt";

	@Autowired
	private CallSpDAO callSpDAO;

	@Autowired
	private ConctbRepository conctbRepository;
	SqlParameterSource parameters;
	Map<String, Object> out;

	@Override
	public String exportCuentas(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T1.CUENTA||'|'||T1.SCTA||'|'||\r\n")
				.append("             T1.SSCTA ||'|'||T1.SSSCTA||'|'||\r\n")
				.append("             T1.SSSSCTA||'|'||TRIM(TO_CHAR(T1.CARGOS, '999999999990.99'))||'|'||\r\n")
				.append("             TRIM(TO_CHAR(T1.ABONOS, '999999999990.99'))\r\n")
				.append("      FROM (SELECT CU.CUENTA   ,\r\n").append("       			   CU.SCTA     ,\r\n")
				.append("			       CU.SSCTA    ,\r\n").append("			       CU.SSSCTA   ,\r\n")
				.append("			       CU.SSSSCTA  ,\r\n").append("			       SUM(CU.CARGOS_").append(mes)
				.append(") CARGOS,\r\n").append("			       SUM(CU.ABONOS_").append(mes).append(") ABONOS\r\n")
				.append("			     FROM GEMUSER.CUENTA CU\r\n").append("			WHERE CU.IDSECTOR = ")
				.append(idSector).append("\r\n").append("			GROUP BY  CU.CUENTA   ,\r\n")
				.append("			      	  CU.SCTA     ,\r\n").append("				      CU.SSCTA    ,\r\n")
				.append("				      CU.SSSCTA   ,\r\n").append("				      CU.SSSSCTA \r\n")
				.append("		    ORDER BY CU.CUENTA    ,\r\n").append("			      	  CU.SCTA     ,\r\n")
				.append("				      CU.SSCTA    ,\r\n").append("				      CU.SSSCTA   ,\r\n")
				.append("				      CU.SSSSCTA\r\n").append("				      ASC\r\n")
				.append("		    ) T1");
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", FILE_CUETA);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	@Override
	public String exportPaso(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT '\"'||T1.CLAVE||'\" \"'||T1.PROGRAMA||'\" \"'||\r\n").append(
				"       '\"'||T1.PARTIDA||'\" \"'||TRIM(TO_CHAR(T1.APROBADO, '999999999999990.99'))||'\" \"'||\r\n")
				.append("       '\"'||TRIM(TO_CHAR(T1.APLIACION, '999999999999990.99'))||'\" \"'||TRIM(TO_CHAR(T1.REDUCCION, '999999999999990.99'))||'\" \"'||\r\n")
				.append("       '\"'||TRIM(TO_CHAR(T1.EJPA, '999999999999990.99'))||'\" \"'||TRIM(TO_CHAR(T1.JEXPA, '999999999999990.99'))||'\" \"'||\r\n")
				.append("       '\"'||TRIM(TO_CHAR(T1.POR_EJERCER, '999999999999990.99'))||'\" \"'||TRIM(TO_CHAR(POR_APROBAR, '999999999999990.99'))||'\"'\r\n")
				.append("     FROM (SELECT PA.CLAVE   ,\r\n").append("                  PA.PROGRAMA,\r\n")
				.append("                  PA.PARTIDA ,\r\n").append("                  SUM(PA.AUTO1_").append(mes)
				.append(") APROBADO,\r\n").append("                  SUM(PA.AMPLI1_").append(mes)
				.append(") APLIACION,\r\n").append("                  SUM(PA.REDU1_").append(mes)
				.append(") REDUCCION,\r\n").append("                  SUM(PA.EJPA1_").append(mes).append(") EJPA,\r\n")
				.append("                  SUM(PA.EJXPA1_").append(mes).append(") JEXPA,\r\n")
				.append("                  SUM(PA.TOEJE1_").append(mes).append(") POR_EJERCER,\r\n")
				.append("                  SUM(PA.PORPA1_").append(mes).append(") POR_APROBAR\r\n")
				.append("                FROM GEMUSER.PASO PA\r\n").append("			WHERE PA.IDSECTOR = ")
				.append(idSector).append("\r\n").append("			GROUP BY PA.CLAVE   ,\r\n")
				.append("			         PA.PROGRAMA,\r\n").append("			         PA.PARTIDA\r\n")
				.append("			ORDER BY PA.CLAVE   ,\r\n").append("			       	 PA.PROGRAMA,\r\n")
				.append("				     PA.PARTIDA\r\n").append("				     ASC\r\n")
				.append("		   ) T1");
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", FILE_PASO);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	@Override
	public String exportPoliza(Integer idSector, Integer mes) {
		Conctb conctb = this.conctbRepository.findByIdsector(idSector);
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT '\"'||T1.TIPPOL||'\"|\"'||T1.CONPOL||'\"|\"'||\r\n")
				.append("            T1.DIA||'\"|\"'||T1.MES||'\"|\"'||T1.ANO||'\"|\"'||\r\n")
				.append("            T1.CUENTA||'\"|\"'||T1.SCTA||'\"|\"'||T1.SSCTA||'\"|\"'||\r\n")
				.append("            T1.SSSCTA||'\"|\"'||T1.SSSSCTA||'\"|\"'||T1.REFPOL||'\"|\"'||\r\n")
				.append("            TRIM(TO_CHAR(T1.CARGOS, '999999999999990.99'))||'\"|\"'||\r\n")
				.append("            TRIM(TO_CHAR(T1.ABONOS, '999999999999990.99'))||'\"'\r\n")
				.append("     FROM (SELECT PD.TIPPOL,\r\n").append("       		      PD.CONPOL,\r\n")
				.append("		          TO_CHAR(PD.FECPOL, 'DD') DIA,\r\n")
				.append("		          TO_CHAR(PD.FECPOL, 'MM')MES ,\r\n")
				.append("		          TO_CHAR(PD.FECPOL, 'YY')ANO ,\r\n").append("		          PD.CUENTA ,\r\n")
				.append("		          PD.SCTA   ,\r\n").append("		          PD.SSCTA  ,\r\n")
				.append("		          PD.SSSCTA ,\r\n").append("		          PD.SSSSCTA,\r\n")
				.append("		          PD.REFPOL ,\r\n").append("		          PD.CONCEP ,\r\n")
				.append("		          SUM(PD.CANPOL) CARGOS ,\r\n")
				.append("		          SUM(PD.CANPOLH) ABONOS\r\n")
				.append("		        FROM GEMUSER.POLIEN PE,\r\n").append("		            GEMUSER.POLIDE PD\r\n")
				.append("           WHERE 1 = 1\r\n").append("             AND PE.MESPOL   = PD.MESPOL\r\n")
				.append("             AND PE.CONPOL   = PD.CONPOL\r\n")
				.append("             AND PE.TIPPOL   = PD.TIPPOL\r\n")
				.append("             AND PE.ANOPOL   = PD.ANOPOL\r\n")
				.append("             AND PE.IDSECTOR = PD.IDSECTOR\r\n").append("             AND PE.IDSECTOR = ")
				.append(idSector).append(" \r\n").append("             AND PE.MESPOL   =  ").append(mes).append(" \r\n")
				.append("             AND PE.STAAFE   = 'A'\r\n").append("             AND PE.ANOPOL   = ")
				.append(conctb.getAnoemp()).append("\r\n").append("          GROUP BY PD.TIPPOL,\r\n")
				.append("      	           PD.CONPOL,\r\n").append("	     		   TO_CHAR(PD.FECPOL, 'DD'),\r\n")
				.append("				   TO_CHAR(PD.FECPOL, 'MM'),\r\n")
				.append("				   TO_CHAR(PD.FECPOL, 'YY'),\r\n").append("				   PD.CUENTA ,\r\n")
				.append("				   PD.SCTA   ,\r\n").append("				   PD.SSCTA  ,\r\n")
				.append("				   PD.SSSCTA ,\r\n").append("				   PD.SSSSCTA,\r\n")
				.append("				   PD.REFPOL ,\r\n").append("				   PD.CONCEP\r\n")
				.append("          ORDER BY 1, 2\r\n").append("          ) T1\r\n").append("");
		String fileName = FILE_POLIZA
				+ StringUtils.leftPad(String.valueOf(getLastDayOfMonth(Calendar.getInstance().getTime())), 2, '0') + ""
				+ getYear() + TXT;
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", fileName);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	public CallSpDAO getCallSpDAO() {
		return callSpDAO;
	}

	public void setCallSpDAO(CallSpDAO callSpDAO) {
		this.callSpDAO = callSpDAO;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	@Override
	public String exportCuentaMes(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT  '\"'||CUENTA||\r\n").append("		SCTA||\r\n").append("		SSCTA||\r\n")
				.append("		SSSCTA||\r\n").append("		SSSSCTA||'\"|\"'||\r\n")
				.append("		NOMCTA||'\"|\"'||\r\n").append("		DECODE(XNICTA,1,CUENTA,'')||\r\n")
				.append("		DECODE(XNICTA,2,CUENTA,'')||\r\n").append("		DECODE(XNICTA,3,CUENTA||SCTA,'')||\r\n")
				.append("		DECODE(XNICTA,4,CUENTA||SCTA||SSCTA,'')||\r\n")
				.append("		DECODE(XNICTA,5,CUENTA||SCTA||SSCTA||SSSCTA,'')||'\"|\"'||\r\n")
				.append("		XNICTA||'\"|\"'||\r\n").append("		STACTA||'\"|\"'||\r\n")
				.append("		DECODE(XNICTA,4,'R','A')||'\"|\"'||\r\n")
				.append("		DECODE(SUBSTR(CUENTA,1,1),5,'E','A')||'\"|\"'||\r\n")
				.append("		FN_GET_FORMAT_NUMBER(CARGOS_1 - ABONOS_1)||'\"'\r\n").append("	FROM CUENTA\r\n")
				.append("WHERE IDSECTOR = ").append(idSector).append("\r\n")
				.append("ORDER BY CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA");

		String fileName = CUENTA_EXPORT
				+ StringUtils.leftPad(String.valueOf(getLastDayOfMonth(Calendar.getInstance().getTime())), 2, '0') + ""
				+ getYear() + TXT;
		parameters = new MapSqlParameterSource().addValue("i_header", StringUtils.EMPTY)
				.addValue("i_query", sSql.toString()).addValue("i_file_name", fileName);
		out = this.callSpDAO.call(NAME_PROCEDURE, parameters);
		return out.get("O_FULL_FILE_PATH").toString();
	}

	@Override
	public String zipFile(File[] inputFile, String zipFilePath) {

		try {

			// Wrap a FileOutputStream around a ZipOutputStream
			// to store the zip stream to a file. Note that this is
			// not absolutely necessary
			FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

			// a ZipEntry represents a file entry in the zip archive
			// We name the ZipEntry after the original file's name
			for (int i = 0; i < inputFile.length; i++) {
				ZipEntry zipEntry = new ZipEntry(inputFile[i].getName());
				zipOutputStream.putNextEntry(zipEntry);

				FileInputStream fileInputStream = new FileInputStream(inputFile[i]);
				byte[] buf = new byte[1024];
				int bytesRead;

				// Read the input file by chucks of 1024 bytes
				// and write the read bytes to the zip stream
				while ((bytesRead = fileInputStream.read(buf)) > 0) {
					zipOutputStream.write(buf, 0, bytesRead);
				}

				// close ZipEntry to store the stream to the file
				if (i == inputFile.length) {
					zipOutputStream.closeEntry();

					zipOutputStream.close();
					fileOutputStream.close();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return zipFilePath;
	}

}
