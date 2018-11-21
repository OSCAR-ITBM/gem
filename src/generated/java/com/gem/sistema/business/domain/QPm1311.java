package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1311 is a Querydsl query type for Pm1311
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1311 extends EntityPathBase<Pm1311> {

    private static final long serialVersionUID = -1909863053L;

    public static final QPm1311 pm1311 = new QPm1311("pm1311");

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DateTimePath<java.util.Date> feccap = createDateTime("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> idRef = createNumber("idRef", Integer.class);

    public final NumberPath<Integer> idSector = createNumber("idSector", Integer.class);

    public final StringPath obspttm = createString("obspttm");

    public final StringPath obstco = createString("obstco");

    public final NumberPath<Integer> pttm = createNumber("pttm", Integer.class);

    public final NumberPath<Integer> tco = createNumber("tco", Integer.class);

    public final StringPath userId = createString("userId");

    public QPm1311(String variable) {
        super(Pm1311.class, forVariable(variable));
    }

    public QPm1311(Path<? extends Pm1311> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1311(PathMetadata<?> metadata) {
        super(Pm1311.class, metadata);
    }

}

