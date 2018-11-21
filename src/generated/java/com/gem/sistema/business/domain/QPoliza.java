package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPoliza is a Querydsl query type for Poliza
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPoliza extends EntityPathBase<Poliza> {

    private static final long serialVersionUID = -1906204137L;

    public static final QPoliza poliza = new QPoliza("poliza");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anopol = createNumber("anopol", Integer.class);

    public final StringPath clvcto = createString("clvcto");

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    public final StringPath cuenta = createString("cuenta");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Integer> mespol = createNumber("mespol", Integer.class);

    public final NumberPath<java.math.BigDecimal> renpol = createNumber("renpol", java.math.BigDecimal.class);

    public final StringPath scuenta = createString("scuenta");

    public final StringPath sscuenta = createString("sscuenta");

    public final StringPath ssscuenta = createString("ssscuenta");

    public final StringPath sssscuenta = createString("sssscuenta");

    public final StringPath tippol = createString("tippol");

    public QPoliza(String variable) {
        super(Poliza.class, forVariable(variable));
    }

    public QPoliza(Path<? extends Poliza> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoliza(PathMetadata<?> metadata) {
        super(Poliza.class, metadata);
    }

}

