package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1611 is a Querydsl query type for Pm1611
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1611 extends EntityPathBase<Pm1611> {

    private static final long serialVersionUID = -1909860170L;

    public static final QPm1611 pm1611 = new QPm1611("pm1611");

    public final NumberPath<Integer> acuajp = createNumber("acuajp", Integer.class);

    public final NumberPath<Integer> acuconca = createNumber("acuconca", Integer.class);

    public final NumberPath<Integer> ajp = createNumber("ajp", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> conca = createNumber("conca", Integer.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsajp = createString("obsajp");

    public final StringPath obsconca = createString("obsconca");

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm1611(String variable) {
        super(Pm1611.class, forVariable(variable));
    }

    public QPm1611(Path<? extends Pm1611> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1611(PathMetadata<?> metadata) {
        super(Pm1611.class, metadata);
    }

}

