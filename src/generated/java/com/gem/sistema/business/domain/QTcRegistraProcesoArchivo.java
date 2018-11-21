package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcRegistraProcesoArchivo is a Querydsl query type for TcRegistraProcesoArchivo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcRegistraProcesoArchivo extends EntityPathBase<TcRegistraProcesoArchivo> {

    private static final long serialVersionUID = -2029341207L;

    public static final QTcRegistraProcesoArchivo tcRegistraProcesoArchivo = new QTcRegistraProcesoArchivo("tcRegistraProcesoArchivo");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final DatePath<java.util.Date> fechaProceso = createDate("fechaProceso", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombreArchivoOrginal = createString("nombreArchivoOrginal");

    public final StringPath nombreArchivoProcesado = createString("nombreArchivoProcesado");

    public final StringPath nombreArchivoRespuesta = createString("nombreArchivoRespuesta");

    public final NumberPath<java.math.BigDecimal> registrosCorrectos = createNumber("registrosCorrectos", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> registrosIncorrectos = createNumber("registrosIncorrectos", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> registrosProcesados = createNumber("registrosProcesados", java.math.BigDecimal.class);

    public final StringPath rutaArchivo = createString("rutaArchivo");

    public final StringPath usuario = createString("usuario");

    public QTcRegistraProcesoArchivo(String variable) {
        super(TcRegistraProcesoArchivo.class, forVariable(variable));
    }

    public QTcRegistraProcesoArchivo(Path<? extends TcRegistraProcesoArchivo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcRegistraProcesoArchivo(PathMetadata<?> metadata) {
        super(TcRegistraProcesoArchivo.class, metadata);
    }

}

