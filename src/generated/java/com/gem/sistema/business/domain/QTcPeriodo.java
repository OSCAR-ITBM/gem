package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcPeriodo is a Querydsl query type for TcPeriodo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcPeriodo extends EntityPathBase<TcPeriodo> {

    private static final long serialVersionUID = -1245451605L;

    public static final QTcPeriodo tcPeriodo = new QTcPeriodo("tcPeriodo");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> periodo = createNumber("periodo", Integer.class);

    public final NumberPath<Integer> tipoPeriodo = createNumber("tipoPeriodo", Integer.class);

    public QTcPeriodo(String variable) {
        super(TcPeriodo.class, forVariable(variable));
    }

    public QTcPeriodo(Path<? extends TcPeriodo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcPeriodo(PathMetadata<?> metadata) {
        super(TcPeriodo.class, metadata);
    }

}

