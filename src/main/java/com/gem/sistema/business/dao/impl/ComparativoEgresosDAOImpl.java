package com.gem.sistema.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.gem.sistema.business.dao.ComparativoEgresosDAO;

@Repository
public class ComparativoEgresosDAOImpl implements ComparativoEgresosDAO {

	@Override
	public String generaQueryCompartivo(Integer idSector, Integer mes) {
		StringBuilder sSql = new StringBuilder();
		sSql.append("SELECT T1.CLAVE,\n").append("       T1.NOMBRE,\n").append("       T1.PARTIDA,\n")
				.append("       T1.NOMGAS,\n").append("       T1.APROBADO,\n").append("       T1.MODIFICADO,\n")
				.append("       T1.EJERCIDO,\n")
				.append("       (T1.APROBADO ).append(T1.AMPLIACION - T1.REDUCCION) MODIFICADO_ACU,\n")
				.append("       T1.EJERCICIO_ACU,\n").append("       T1.MODIFICADO - EJERCIDO VARIACION,\n")
				.append("       ((T1.MODIFICADO - EJERCIDO)/ (T1.APROBADO ).append(T1.AMPLIACION - T1.REDUCCION))*100  PORCENTAJE \n")
				.append("     FROM (SELECT PA.PARTIDA,\n").append("                  SUBSTR(PA.CLAVE,4,3) CLAVE ,\n")
				.append("                  CD.NOMBRE,\n").append("                  NG.NOMGAS ,\n")
				.append("                  SUM(PA.AUTO1_1 ).append(PA.AUTO1_2 ).append(PA.AUTO1_3 ).append(PA.AUTO1_4 ).append( \n")
				.append("                      PA.AUTO1_5 ).append(PA.AUTO1_6 ).append(PA.AUTO1_7 ).append(PA.AUTO1_8 ).append(\n")
				.append("                      PA.AUTO1_9 ).append(PA.AUTO1_10 ).append(PA.AUTO1_11 ).append(PA.AUTO1_12) APROBADO,\n")
				.append("                  SUM(PA.AUTO1_1 ).append(PA.AMPLI1_1 ).append(PA.REDU1_1) MODIFICADO,\n")
				.append("                  SUM(PA.TOEJE1_").append(mes).append(") EJERCIDO,\n")
				.append("                  SUM(PA.AMPLI1_1 ).append(PA.AMPLI1_2 ).append(PA.AMPLI1_3 ).append(PA.AMPLI1_4 +\n")
				.append("                      PA.AMPLI1_5 ).append(PA.AMPLI1_6 ).append(PA.AMPLI1_7 ).append(PA.AMPLI1_8 +\n")
				.append("                      PA.AMPLI1_9 ).append(PA.AMPLI1_10 ).append(PA.AMPLI1_11 ).append(PA.AMPLI1_12) AMPLIACION,\n")
				.append("                  SUM(PA.REDU1_1 ).append(PA.REDU1_2 ).append(PA.REDU1_3 ).append(PA.REDU1_4 +\n")
				.append("                      PA.REDU1_5 ).append(PA.REDU1_6 ).append(PA.REDU1_7 ).append(PA.REDU1_8 +\n")
				.append("                      PA.REDU1_9 ).append(PA.REDU1_10 ).append(PA.REDU1_11 ).append(PA.REDU1_12) REDUCCION,\n")
				.append("                  SUM(PA.TOEJE1_1 ).append(PA.TOEJE1_2 ).append(PA.TOEJE1_3 ).append(PA.TOEJE1_4 +\n")
				.append("                      PA.TOEJE1_5 ).append(PA.TOEJE1_6 ).append(PA.TOEJE1_7 ).append(PA.TOEJE1_8 +\n")
				.append("                      PA.TOEJE1_9 ).append(PA.TOEJE1_10 ).append(PA.TOEJE1_11 ).append(PA.TOEJE1_12\n")
				.append("                      ) EJERCICIO_ACU \n").append("                FROM GEMUSER.PASO PA  ,\n")
				.append("                     GEMUSER.NATGAS NG, GEMUSER.CATDAA CD\n")
				.append("            WHERE PA.PARTIDA  = NG.CLVGAS\n")
				.append("              AND PA.IDSECTOR = NG.IDSECTOR\n")
				.append("              AND SUBSTR(PA.CLAVE,4,3)=CD.CLAVE\n").append("              AND PA.IDSECTOR = ")
				.append(idSector).append("\n")
				.append("            GROUP BY PA.PARTIDA,SUBSTR(PA.CLAVE,4,3), CD.NOMBRE,\n")
				.append("                     NG.NOMGAS\n").append("         ) T1\n")
				.append("         ORDER BY T1.CLAVE, T1.PARTIDA");
		return sSql.toString();
	}

}
