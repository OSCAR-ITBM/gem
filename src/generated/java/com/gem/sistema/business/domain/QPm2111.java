package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm2111 is a Querydsl query type for Pm2111
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm2111 extends EntityPathBase<Pm2111> {

    private static final long serialVersionUID = -1909835184L;

    public static final QPm2111 pm2111 = new QPm2111("pm2111");

    public final NumberPath<Integer> atendidas = createNumber("atendidas", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsaten = createString("obsaten");

    public final StringPath obsprog = createString("obsprog");

    public final NumberPath<Integer> programadas = createNumber("programadas", Integer.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm2111(String variable) {
        super(Pm2111.class, forVariable(variable));
    }

    public QPm2111(Path<? extends Pm2111> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm2111(PathMetadata<?> metadata) {
        super(Pm2111.class, metadata);
    }

}

