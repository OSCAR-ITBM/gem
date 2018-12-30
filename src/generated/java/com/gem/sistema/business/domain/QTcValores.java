package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcValores is a Querydsl query type for TcValores
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcValores extends EntityPathBase<TcValores> {

    private static final long serialVersionUID = -335272881L;

    public static final QTcValores tcValores = new QTcValores("tcValores");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idEtiqTabla = createNumber("idEtiqTabla", Long.class);

    public final NumberPath<Integer> idRow = createNumber("idRow", Integer.class);

    public final StringPath valor = createString("valor");

    public QTcValores(String variable) {
        super(TcValores.class, forVariable(variable));
    }

    public QTcValores(Path<? extends TcValores> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcValores(PathMetadata<?> metadata) {
        super(TcValores.class, metadata);
    }

}

