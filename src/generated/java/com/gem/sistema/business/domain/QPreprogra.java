package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPreprogra is a Querydsl query type for Preprogra
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPreprogra extends EntityPathBase<Preprogra> {

    private static final long serialVersionUID = -460416840L;

    public static final QPreprogra preprogra = new QPreprogra("preprogra");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<java.math.BigDecimal> cantidad = createNumber("cantidad", java.math.BigDecimal.class);

    public final StringPath clvfin = createString("clvfin");

    public final StringPath clvfun = createString("clvfun");

    public final StringPath cvedep = createString("cvedep");

    public final StringPath cveprog = createString("cveprog");

    public final DateTimePath<java.util.Date> fecini = createDateTime("fecini", java.util.Date.class);

    public final DateTimePath<java.util.Date> fecter = createDateTime("fecter", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath locben = createString("locben");

    public final NumberPath<Integer> pobben = createNumber("pobben", Integer.class);

    public final StringPath userid = createString("userid");

    public QPreprogra(String variable) {
        super(Preprogra.class, forVariable(variable));
    }

    public QPreprogra(Path<? extends Preprogra> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPreprogra(PathMetadata<?> metadata) {
        super(Preprogra.class, metadata);
    }

}

