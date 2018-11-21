package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTcCuentasAdicionales is a Querydsl query type for TcCuentasAdicionales
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTcCuentasAdicionales extends EntityPathBase<TcCuentasAdicionales> {

    private static final long serialVersionUID = 1825797340L;

    public static final QTcCuentasAdicionales tcCuentasAdicionales = new QTcCuentasAdicionales("tcCuentasAdicionales");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath adicional = createString("adicional");

    public final StringPath ctaAdicional = createString("ctaAdicional");

    public final StringPath cuenta = createString("cuenta");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Short> invertir = createNumber("invertir", Short.class);

    public QTcCuentasAdicionales(String variable) {
        super(TcCuentasAdicionales.class, forVariable(variable));
    }

    public QTcCuentasAdicionales(Path<? extends TcCuentasAdicionales> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcCuentasAdicionales(PathMetadata<?> metadata) {
        super(TcCuentasAdicionales.class, metadata);
    }

}

