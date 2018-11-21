package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1711 is a Querydsl query type for Pm1711
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1711 extends EntityPathBase<Pm1711> {

    private static final long serialVersionUID = -1909859209L;

    public static final QPm1711 pm1711 = new QPm1711("pm1711");

    public final NumberPath<Integer> acucm = createNumber("acucm", Integer.class);

    public final NumberPath<Integer> acudias = createNumber("acudias", Integer.class);

    public final NumberPath<Integer> acume = createNumber("acume", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> cm = createNumber("cm", Integer.class);

    public final NumberPath<Integer> dias = createNumber("dias", Integer.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> me = createNumber("me", Integer.class);

    public final StringPath obscm = createString("obscm");

    public final StringPath obsdias = createString("obsdias");

    public final StringPath obsme = createString("obsme");

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm1711(String variable) {
        super(Pm1711.class, forVariable(variable));
    }

    public QPm1711(Path<? extends Pm1711> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1711(PathMetadata<?> metadata) {
        super(Pm1711.class, metadata);
    }

}

