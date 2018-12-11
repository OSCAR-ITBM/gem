package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTcPuesto is a Querydsl query type for TcPuesto
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcPuesto extends EntityPathBase<TcPuesto> {

    private static final long serialVersionUID = 1913885617L;

    public static final QTcPuesto tcPuesto = new QTcPuesto("tcPuesto");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> estatus = createNumber("estatus", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final StringPath puesto = createString("puesto");

    public final ListPath<TrPuestoFirma, QTrPuestoFirma> trPuestoFirmas = this.<TrPuestoFirma, QTrPuestoFirma>createList("trPuestoFirmas", TrPuestoFirma.class, QTrPuestoFirma.class, PathInits.DIRECT2);

    public QTcPuesto(String variable) {
        super(TcPuesto.class, forVariable(variable));
    }

    public QTcPuesto(Path<? extends TcPuesto> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcPuesto(PathMetadata<?> metadata) {
        super(TcPuesto.class, metadata);
    }

}

