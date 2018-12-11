package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTrPuestoFirma is a Querydsl query type for TrPuestoFirma
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTrPuestoFirma extends EntityPathBase<TrPuestoFirma> {

    private static final long serialVersionUID = 1605281091L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTrPuestoFirma trPuestoFirma = new QTrPuestoFirma("trPuestoFirma");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idAnio = createNumber("idAnio", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final StringPath nombre = createString("nombre");

    public final QTcPuesto tcPuesto;

    public QTrPuestoFirma(String variable) {
        this(TrPuestoFirma.class, forVariable(variable), INITS);
    }

    public QTrPuestoFirma(Path<? extends TrPuestoFirma> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTrPuestoFirma(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTrPuestoFirma(PathMetadata<?> metadata, PathInits inits) {
        this(TrPuestoFirma.class, metadata, inits);
    }

    public QTrPuestoFirma(Class<? extends TrPuestoFirma> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tcPuesto = inits.isInitialized("tcPuesto") ? new QTcPuesto(forProperty("tcPuesto")) : null;
    }

}

