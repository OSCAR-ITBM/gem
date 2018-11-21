package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0211 is a Querydsl query type for Pm0211
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0211 extends EntityPathBase<Pm0211> {

    private static final long serialVersionUID = -1909893805L;

    public static final QPm0211 pm0211 = new QPm0211("pm0211");

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mensual = createNumber("mensual", Integer.class);

    public final NumberPath<Integer> np = createNumber("np", Integer.class);

    public final StringPath obsnp = createString("obsnp");

    public final StringPath obspt = createString("obspt");

    public final NumberPath<Integer> pt = createNumber("pt", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm0211(String variable) {
        super(Pm0211.class, forVariable(variable));
    }

    public QPm0211(Path<? extends Pm0211> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0211(PathMetadata<?> metadata) {
        super(Pm0211.class, metadata);
    }

}

