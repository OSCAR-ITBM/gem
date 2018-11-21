package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4611 is a Querydsl query type for Pm4611
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4611 extends EntityPathBase<Pm4611> {

    private static final long serialVersionUID = -1909770797L;

    public static final QPm4611 pm4611 = new QPm4611("pm4611");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> anual = createNumber("anual", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> nrcifp = createNumber("nrcifp", Integer.class);

    public final NumberPath<Integer> nrciip = createNumber("nrciip", Integer.class);

    public final StringPath obsnrcifp = createString("obsnrcifp");

    public final StringPath obsnrciip = createString("obsnrciip");

    public final StringPath userid = createString("userid");

    public QPm4611(String variable) {
        super(Pm4611.class, forVariable(variable));
    }

    public QPm4611(Path<? extends Pm4611> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4611(PathMetadata<?> metadata) {
        super(Pm4611.class, metadata);
    }

}

