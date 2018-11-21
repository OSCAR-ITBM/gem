package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm1811 is a Querydsl query type for Pm1811
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm1811 extends EntityPathBase<Pm1811> {

    private static final long serialVersionUID = -1909858248L;

    public static final QPm1811 pm1811 = new QPm1811("pm1811");

    public final NumberPath<Integer> acumnumni = createNumber("acumnumni", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> numdes = createNumber("numdes", Integer.class);

    public final NumberPath<Integer> numni = createNumber("numni", Integer.class);

    public final StringPath obsnumdes = createString("obsnumdes");

    public final StringPath obsnumni = createString("obsnumni");

    public final NumberPath<Integer> semes = createNumber("semes", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm1811(String variable) {
        super(Pm1811.class, forVariable(variable));
    }

    public QPm1811(Path<? extends Pm1811> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm1811(PathMetadata<?> metadata) {
        super(Pm1811.class, metadata);
    }

}

