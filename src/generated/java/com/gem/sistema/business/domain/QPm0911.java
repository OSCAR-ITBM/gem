package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0911 is a Querydsl query type for Pm0911
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0911 extends EntityPathBase<Pm0911> {

    private static final long serialVersionUID = -1909887078L;

    public static final QPm0911 pm0911 = new QPm0911("pm0911");

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obstotzon = createString("obstotzon");

    public final StringPath obszonas = createString("obszonas");

    public final NumberPath<Integer> semes = createNumber("semes", Integer.class);

    public final NumberPath<Integer> totzonas = createNumber("totzonas", Integer.class);

    public final StringPath userid = createString("userid");

    public final NumberPath<Integer> zonas = createNumber("zonas", Integer.class);

    public QPm0911(String variable) {
        super(Pm0911.class, forVariable(variable));
    }

    public QPm0911(Path<? extends Pm0911> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0911(PathMetadata<?> metadata) {
        super(Pm0911.class, metadata);
    }

}

