package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPoliin is a Querydsl query type for Poliin
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPoliin extends EntityPathBase<Poliin> {

    private static final long serialVersionUID = -1906204651L;

    public static final QPoliin poliin = new QPoliin("poliin");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anopol = createNumber("anopol", Integer.class);

    public final NumberPath<Integer> clave = createNumber("clave", Integer.class);

    public final NumberPath<java.math.BigDecimal> clvben = createNumber("clvben", java.math.BigDecimal.class);

    public final NumberPath<Integer> conpol = createNumber("conpol", Integer.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mespol = createNumber("mespol", Integer.class);

    public final NumberPath<java.math.BigDecimal> moncor = createNumber("moncor", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> monlar = createNumber("monlar", java.math.BigDecimal.class);

    public final NumberPath<Integer> renpol = createNumber("renpol", Integer.class);

    public final StringPath tippol = createString("tippol");

    public final StringPath userid = createString("userid");

    public QPoliin(String variable) {
        super(Poliin.class, forVariable(variable));
    }

    public QPoliin(Path<? extends Poliin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoliin(PathMetadata<?> metadata) {
        super(Poliin.class, metadata);
    }

}

