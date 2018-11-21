package com.gem.sistema.business.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QPm0411 is a Querydsl query type for Pm0411
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPm0411 extends EntityPathBase<Pm0411> {

    private static final long serialVersionUID = -1909891883L;

    public static final QPm0411 pm0411 = new QPm0411("pm0411");

    public final QAbstractEntity _super = new QAbstractEntity(this);

    public final NumberPath<Integer> acudc = createNumber("acudc", Integer.class);

    public final NumberPath<Integer> acudi = createNumber("acudi", Integer.class);

    public final StringPath capturo = createString("capturo");

    public final NumberPath<Integer> dc = createNumber("dc", Integer.class);

    public final NumberPath<Integer> di = createNumber("di", Integer.class);

    public final DatePath<java.util.Date> feccap = createDate("feccap", java.util.Date.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> idRef = createNumber("idRef", Long.class);

    public final NumberPath<Integer> idsector = createNumber("idsector", Integer.class);

    public final StringPath obsdc = createString("obsdc");

    public final StringPath obsdi = createString("obsdi");

    public final NumberPath<Integer> trimestre = createNumber("trimestre", Integer.class);

    public final StringPath userid = createString("userid");

    public QPm0411(String variable) {
        super(Pm0411.class, forVariable(variable));
    }

    public QPm0411(Path<? extends Pm0411> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPm0411(PathMetadata<?> metadata) {
        super(Pm0411.class, metadata);
    }

}

