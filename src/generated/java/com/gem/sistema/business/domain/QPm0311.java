package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0311 is a Querydsl query type for Pm0311
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0311 extends EntityPathBase<Pm0311> {

    private static final long serialVersionUID = -1909892844L;

    public static final QPm0311 pm0311 = new QPm0311("pm0311");

    public final NumberPath<Integer> acuden = createNumber("acuden", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> denuncias = createNumber("denuncias", Integer.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsden = createString("obsden");

    public final StringPath obspob = createString("obspob");

    public final NumberPath<Integer> poblacion = createNumber("poblacion", Integer.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm0311(String variable) {
        super(Pm0311.class, forVariable(variable));
    }

    public QPm0311(Path<? extends Pm0311> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0311(PathMetadata<?> metadata) {
        super(Pm0311.class, metadata);
    }

}

