package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcParametro is a Querydsl query type for TcParametro
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcParametro extends EntityPathBase<TcParametro> {

    private static final long serialVersionUID = -1490232874L;

    public static final QTcParametro tcParametro = new QTcParametro("tcParametro");

    public final StringPath cveParametro = createString("cveParametro");

    public final StringPath dataType = createString("dataType");

    public final StringPath descripcion = createString("descripcion");

    public final StringPath valor = createString("valor");

    public QTcParametro(String variable) {
        super(TcParametro.class, forVariable(variable));
    }

    public QTcParametro(Path<? extends TcParametro> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcParametro(PathMetadata<?> metadata) {
        super(TcParametro.class, metadata);
    }

}

