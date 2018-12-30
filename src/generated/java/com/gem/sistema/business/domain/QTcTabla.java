package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcTabla is a Querydsl query type for TcTabla
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcTabla extends EntityPathBase<TcTabla> {

    private static final long serialVersionUID = -73713945L;

    public static final QTcTabla tcTabla = new QTcTabla("tcTabla");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath descripcion = createString("descripcion");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nombre = createString("nombre");

    public QTcTabla(String variable) {
        super(TcTabla.class, forVariable(variable));
    }

    public QTcTabla(Path<? extends TcTabla> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcTabla(PathMetadata<?> metadata) {
        super(TcTabla.class, metadata);
    }

}

