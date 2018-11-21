package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0611 is a Querydsl query type for Pm0611
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0611 extends EntityPathBase<Pm0611> {

    private static final long serialVersionUID = -1909889961L;

    public static final QPm0611 pm0611 = new QPm0611("pm0611");

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Integer> habi = createNumber("habi", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obshabi = createString("obshabi");

    public final StringPath obsparve = createString("obsparve");

    public final StringPath obspatru = createString("obspatru");

    public final NumberPath<Integer> parve = createNumber("parve", Integer.class);

    public final NumberPath<Integer> patru = createNumber("patru", Integer.class);

    public final NumberPath<Integer> semestral = createNumber("semestral", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm0611(String variable) {
        super(Pm0611.class, forVariable(variable));
    }

    public QPm0611(Path<? extends Pm0611> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0611(PathMetadata<?> metadata) {
        super(Pm0611.class, metadata);
    }

}

