package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4411 is a Querydsl query type for Pm4411
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4411 extends EntityPathBase<Pm4411> {

    private static final long serialVersionUID = -1909772719L;

    public static final QPm4411 pm4411 = new QPm4411("pm4411");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mes = createNumber("mes", Integer.class);

    public final StringPath obs1 = createString("obs1");

    public final StringPath obs2 = createString("obs2");

    public final StringPath obs3 = createString("obs3");

    public final StringPath obs4 = createString("obs4");

    public final StringPath obs5 = createString("obs5");

    public final StringPath obs6 = createString("obs6");

    public final StringPath userid = createString("userid");

    public QPm4411(String variable) {
        super(Pm4411.class, forVariable(variable));
    }

    public QPm4411(Path<? extends Pm4411> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4411(PathMetadata<?> metadata) {
        super(Pm4411.class, metadata);
    }

}

