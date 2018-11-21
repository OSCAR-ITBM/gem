package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMuninep is a Querydsl query type for Muninep
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMuninep extends EntityPathBase<Muninep> {

    private static final long serialVersionUID = -1451686494L;

    public static final QMuninep muninep = new QMuninep("muninep");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath campo0 = createString("campo0");

    public final StringPath campo1 = createString("campo1");

    public final StringPath campo2 = createString("campo2");

    public final StringPath campo3 = createString("campo3");

    public final StringPath campo4 = createString("campo4");

    public final StringPath campo5 = createString("campo5");

    public final StringPath campo6 = createString("campo6");

    public final StringPath campo7 = createString("campo7");

    public final StringPath campo8 = createString("campo8");

    public final StringPath campo9 = createString("campo9");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath userid = createString("userid");

    public QMuninep(String variable) {
        super(Muninep.class, forVariable(variable));
    }

    public QMuninep(Path<? extends Muninep> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMuninep(PathMetadata<?> metadata) {
        super(Muninep.class, metadata);
    }

}

