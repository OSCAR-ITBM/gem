package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTrEtiqTabla is a Querydsl query type for TrEtiqTabla
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTrEtiqTabla extends EntityPathBase<TrEtiqTabla> {

    private static final long serialVersionUID = -1944904351L;

    public static final QTrEtiqTabla trEtiqTabla = new QTrEtiqTabla("trEtiqTabla");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idEtiqueta = createNumber("idEtiqueta", Long.class);

    public final NumberPath<Long> idTabla = createNumber("idTabla", Long.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QTrEtiqTabla(String variable) {
        super(TrEtiqTabla.class, forVariable(variable));
    }

    public QTrEtiqTabla(Path<? extends TrEtiqTabla> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTrEtiqTabla(PathMetadata<?> metadata) {
        super(TrEtiqTabla.class, metadata);
    }

}

