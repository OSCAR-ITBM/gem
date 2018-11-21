package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4111 is a Querydsl query type for Pm4111
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4111 extends EntityPathBase<Pm4111> {

    private static final long serialVersionUID = -1909775602L;

    public static final QPm4111 pm4111 = new QPm4111("pm4111");

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> nac = createNumber("nac", Integer.class);

    public final NumberPath<Integer> nap = createNumber("nap", Integer.class);

    public final StringPath obsnac = createString("obsnac");

    public final StringPath obsnap = createString("obsnap");

    public final StringPath userid = createString("userid");

    public QPm4111(String variable) {
        super(Pm4111.class, forVariable(variable));
    }

    public QPm4111(Path<? extends Pm4111> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4111(PathMetadata<?> metadata) {
        super(Pm4111.class, metadata);
    }

}

