package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTcReporte is a Querydsl query type for TcReporte
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcReporte extends EntityPathBase<TcReporte> {

    private static final long serialVersionUID = 527890830L;

    public static final QTcReporte tcReporte = new QTcReporte("tcReporte");

    public final StringPath botonLabel = createString("botonLabel");

    public final NumberPath<Long> idReporte = createNumber("idReporte", Long.class);

    public final StringPath nombreArchivo = createString("nombreArchivo");

    public final StringPath nombreReporte = createString("nombreReporte");

    public final StringPath qry1 = createString("qry1");

    public final StringPath qry2 = createString("qry2");

    public final StringPath qry3 = createString("qry3");

    public final StringPath rutaArchivo = createString("rutaArchivo");

    public final ListPath<TrReportesLog, QTrReportesLog> trReportesLogs = this.<TrReportesLog, QTrReportesLog>createList("trReportesLogs", TrReportesLog.class, QTrReportesLog.class, PathInits.DIRECT2);

    public QTcReporte(String variable) {
        super(TcReporte.class, forVariable(variable));
    }

    public QTcReporte(Path<? extends TcReporte> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcReporte(PathMetadata<?> metadata) {
        super(TcReporte.class, metadata);
    }

}

