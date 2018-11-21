package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm5311 is a Querydsl query type for Pm5311
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm5311 extends EntityPathBase<Pm5311> {

    private static final long serialVersionUID = -1909743889L;

    public static final QPm5311 pm5311 = new QPm5311("pm5311");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mes = createNumber("mes", Integer.class);

    public final StringPath obs1 = createString("obs1");

    public final StringPath obs2 = createString("obs2");

    public final StringPath obs3 = createString("obs3");

    public final StringPath obs4 = createString("obs4");

    public final StringPath obs5 = createString("obs5");

    public final StringPath obs6 = createString("obs6");

    public final StringPath userid = createString("userid");

    public QPm5311(String variable) {
        super(Pm5311.class, forVariable(variable));
    }

    public QPm5311(Path<? extends Pm5311> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm5311(PathMetadata<?> metadata) {
        super(Pm5311.class, metadata);
    }

}

