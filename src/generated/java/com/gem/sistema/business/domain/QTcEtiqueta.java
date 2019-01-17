package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcEtiqueta is a Querydsl query type for TcEtiqueta
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcEtiqueta extends EntityPathBase<TcEtiqueta> {

    private static final long serialVersionUID = -1767779497L;

    public static final QTcEtiqueta tcEtiqueta = new QTcEtiqueta("tcEtiqueta");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombre = createString("nombre");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QTcEtiqueta(String variable) {
        super(TcEtiqueta.class, forVariable(variable));
    }

    public QTcEtiqueta(Path<? extends TcEtiqueta> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcEtiqueta(PathMetadata<?> metadata) {
        super(TcEtiqueta.class, metadata);
    }

}

