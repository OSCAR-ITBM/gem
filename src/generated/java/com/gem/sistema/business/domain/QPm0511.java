package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0511 is a Querydsl query type for Pm0511
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0511 extends EntityPathBase<Pm0511> {

    private static final long serialVersionUID = -1909890922L;

    public static final QPm0511 pm0511 = new QPm0511("pm0511");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final StringPath conse = createString("conse");

    public final NumberPath<Integer> conta = createNumber("conta", Integer.class);

    public final StringPath emergencia = createString("emergencia");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath observaciones = createString("observaciones");

    public final NumberPath<Integer> tiempo = createNumber("tiempo", Integer.class);

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm0511(String variable) {
        super(Pm0511.class, forVariable(variable));
    }

    public QPm0511(Path<? extends Pm0511> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0511(PathMetadata<?> metadata) {
        super(Pm0511.class, metadata);
    }

}

