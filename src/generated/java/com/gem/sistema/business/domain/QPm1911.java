package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1911 is a Querydsl query type for Pm1911
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1911 extends EntityPathBase<Pm1911> {

    private static final long serialVersionUID = -1909857287L;

    public static final QPm1911 pm1911 = new QPm1911("pm1911");

    public final NumberPath<Integer> acumperdis = createNumber("acumperdis", Integer.class);

    public final NumberPath<Integer> acumtot = createNumber("acumtot", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsperdis = createString("obsperdis");

    public final StringPath obstot = createString("obstot");

    public final NumberPath<Integer> perdis = createNumber("perdis", Integer.class);

    public final NumberPath<Integer> totperdis = createNumber("totperdis", Integer.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm1911(String variable) {
        super(Pm1911.class, forVariable(variable));
    }

    public QPm1911(Path<? extends Pm1911> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1911(PathMetadata<?> metadata) {
        super(Pm1911.class, metadata);
    }

}

