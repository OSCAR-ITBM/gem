package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2211 is a Querydsl query type for Pm2211
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2211 extends EntityPathBase<Pm2211> {

    private static final long serialVersionUID = -1909834223L;

    public static final QPm2211 pm2211 = new QPm2211("pm2211");

    public final NumberPath<Integer> acuajb = createNumber("acuajb", Integer.class);

    public final NumberPath<Integer> acutaj = createNumber("acutaj", Integer.class);

    public final NumberPath<Integer> ajb = createNumber("ajb", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsajb = createString("obsajb");

    public final StringPath obstaj = createString("obstaj");

    public final NumberPath<Integer> taj = createNumber("taj", Integer.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm2211(String variable) {
        super(Pm2211.class, forVariable(variable));
    }

    public QPm2211(Path<? extends Pm2211> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2211(PathMetadata<?> metadata) {
        super(Pm2211.class, metadata);
    }

}

