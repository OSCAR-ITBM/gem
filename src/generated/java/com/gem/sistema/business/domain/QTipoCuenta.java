package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTipoCuenta is a Querydsl query type for TipoCuenta
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTipoCuenta extends EntityPathBase<TipoCuenta> {

    private static final long serialVersionUID = 971563216L;

    public static final QTipoCuenta tipoCuenta = new QTipoCuenta("tipoCuenta");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> idTipoCuenta = createNumber("idTipoCuenta", Integer.class);

    public final StringPath nombre = createString("nombre");

    public final NumberPath<Integer> sTipo = createNumber("sTipo", Integer.class);

    public QTipoCuenta(String variable) {
        super(TipoCuenta.class, forVariable(variable));
    }

    public QTipoCuenta(Path<? extends TipoCuenta> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTipoCuenta(PathMetadata<?> metadata) {
        super(TipoCuenta.class, metadata);
    }

}

