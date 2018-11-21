package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcRole is a Querydsl query type for TcRole
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcRole extends EntityPathBase<TcRole> {

    private static final long serialVersionUID = -1803539015L;

    public static final QTcRole tcRole = new QTcRole("tcRole");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath clave = createString("clave");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombre = createString("nombre");

    public QTcRole(String variable) {
        super(TcRole.class, forVariable(variable));
    }

    public QTcRole(Path<? extends TcRole> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcRole(PathMetadata<?> metadata) {
        super(TcRole.class, metadata);
    }

}

