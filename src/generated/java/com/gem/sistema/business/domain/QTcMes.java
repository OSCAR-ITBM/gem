package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcMes is a Querydsl query type for TcMes
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcMes extends EntityPathBase<TcMes> {

    private static final long serialVersionUID = 1188742200L;

    public static final QTcMes tcMes = new QTcMes("tcMes");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath mes = createString("mes");

    public QTcMes(String variable) {
        super(TcMes.class, forVariable(variable));
    }

    public QTcMes(Path<? extends TcMes> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcMes(PathMetadata<?> metadata) {
        super(TcMes.class, metadata);
    }

}

