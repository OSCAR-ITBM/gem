package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTrReportesLog is a Querydsl query type for TrReportesLog
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTrReportesLog extends EntityPathBase<TrReportesLog> {

    private static final long serialVersionUID = -1530813200L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrReportesLog trReportesLog = new QTrReportesLog("trReportesLog");

    public final NumberPath<Long> cantRegistros = createNumber("cantRegistros", Long.class);

    public final DateTimePath<java.sql.Timestamp> fechaFin = createDateTime("fechaFin", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fechaIni = createDateTime("fechaIni", java.sql.Timestamp.class);

    public final NumberPath<Long> idProceso = createNumber("idProceso", Long.class);

    public final QTcReporte tcReporte;

    public final StringPath usuario = createString("usuario");

    public QTrReportesLog(String variable) {
        this(TrReportesLog.class, forVariable(variable), INITS);
    }

    public QTrReportesLog(Path<? extends TrReportesLog> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTrReportesLog(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTrReportesLog(PathMetadata<?> metadata, PathInits inits) {
        this(TrReportesLog.class, metadata, inits);
    }

    public QTrReportesLog(Class<? extends TrReportesLog> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tcReporte = inits.isInitialized("tcReporte") ? new QTcReporte(forProperty("tcReporte")) : null;
    }

}

