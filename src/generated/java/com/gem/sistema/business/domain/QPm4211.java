package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm4211 is a Querydsl query type for Pm4211
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm4211 extends EntityPathBase<Pm4211> {

    private static final long serialVersionUID = -1909774641L;

    public static final QPm4211 pm4211 = new QPm4211("pm4211");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final StringPath capturo = createString("capturo");

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final NumberPath<Integer> mes = createNumber("mes", Integer.class);

    public final StringPath obs1 = createString("obs1");

    public final StringPath obs10 = createString("obs10");

    public final StringPath obs2 = createString("obs2");

    public final StringPath obs3 = createString("obs3");

    public final StringPath obs4 = createString("obs4");

    public final StringPath obs5 = createString("obs5");

    public final StringPath obs6 = createString("obs6");

    public final StringPath obs7 = createString("obs7");

    public final StringPath obs8 = createString("obs8");

    public final StringPath obs9 = createString("obs9");

    public final StringPath userid = createString("userid");

    public QPm4211(String variable) {
        super(Pm4211.class, forVariable(variable));
    }

    public QPm4211(Path<? extends Pm4211> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm4211(PathMetadata<?> metadata) {
        super(Pm4211.class, metadata);
    }

}

